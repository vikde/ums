package com.demo.ums.controller;

import com.demo.ums.common.SessionConstant;
import com.demo.ums.controller.user.model.ReadOwnVO;
import com.demo.ums.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author vikde
 * @date 2019/03/09
 */
@Controller
public class WebController {
    @Resource
    private UserService userService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ReadOwnVO readOwnVO = userService.readOwn(username);
        model.addAttribute("user", readOwnVO);
        fillAuthoritySet(model);
        return "index";
    }

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession httpSession) {
        //判断是否需要加入验证码,例如在安全环境下不需要验证码
        httpSession.setAttribute(SessionConstant.LOGIN_VERIFICATION_CODE, "123456");
        httpSession.setAttribute(SessionConstant.LOGIN_VERIFICATION_CODE_REQUIRE, false);
        return "login";
    }

    @RequestMapping(value = "/pages/user", method = RequestMethod.GET)
    public String user(Model model) {
        fillAuthoritySet(model);
        return "pages/user";
    }

    @RequestMapping(value = "/pages/permission", method = RequestMethod.GET)
    public String permission(Model model) {
        fillAuthoritySet(model);
        return "pages/permission";
    }

    @RequestMapping(value = "/pages/permissionGroup", method = RequestMethod.GET)
    public String permissionGroup(Model model) {
        fillAuthoritySet(model);
        return "pages/permissionGroup";
    }

    @RequestMapping(value = "/pages/role", method = RequestMethod.GET)
    public String role(Model model) {
        fillAuthoritySet(model);
        return "pages/role";
    }

    /**
     * 填充权限列表
     */
    private void fillAuthoritySet(Model model) {
        Set<String> authoritySet = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                                                        .stream()
                                                        .map(GrantedAuthority::getAuthority)
                                                        .collect(Collectors.toSet());
        model.addAttribute("authoritySet", authoritySet);
    }
}
