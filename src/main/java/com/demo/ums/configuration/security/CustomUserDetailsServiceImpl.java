package com.demo.ums.configuration.security;

import com.demo.ums.common.type.UserStatusType;
import com.demo.ums.repository.mapper.ext.ExtUserMapper;
import com.demo.ums.repository.model.PermissionPO;
import com.demo.ums.repository.model.UserPO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vikde
 * @date 2019/05/01
 */
@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private ExtUserMapper extUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO userPO = extUserMapper.readUserByUsername(username);
        if (null == userPO) {
            throw new UsernameNotFoundException("用户不存在");
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        if (userPO.getUserStatusType() == UserStatusType.DELETED.getIndex()) {
            accountNonExpired = false;
        } else if (userPO.getUserStatusType() == UserStatusType.INACTIVE.getIndex()) {
            credentialsNonExpired = false;
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<PermissionPO> permissions = extUserMapper.readUserPermission(userPO.getUserId());
        for (PermissionPO permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getPath()));
        }

        return new User(userPO.getUsername(), userPO.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}