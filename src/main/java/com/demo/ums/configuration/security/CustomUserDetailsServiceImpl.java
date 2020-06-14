package com.demo.ums.configuration.security;

import com.demo.ums.common.type.UserStatusType;
import com.demo.ums.repository.mapper.ext.ExtUserMapper;
import com.demo.ums.repository.model.PermissionDO;
import com.demo.ums.repository.model.UserDO;
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
        UserDO userDO = extUserMapper.readUserByUsername(username);
        if (null == userDO) {
            throw new UsernameNotFoundException("用户不存在");
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        if (userDO.getUserStatusType() == UserStatusType.DELETED.getIndex()) {
            accountNonExpired = false;
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<PermissionDO> permissions = extUserMapper.readUserPermission(userDO.getUserId());
        for (PermissionDO permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getPath()));
        }

        return new User(userDO.getUsername(), userDO.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}