package com.demo.ums.controller;

import com.demo.ums.controller.user.model.ReadOwnResponse;
import com.demo.ums.repository.model.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author vikde
 * @date 2019/03/09
 */
@Controller
public class WebController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession httpSession) {
        ReadOwnResponse readOwnResponse = (ReadOwnResponse) httpSession.getAttribute("user");
        if (readOwnResponse == null) {
            return "login";
        }
        model.addAttribute("user", readOwnResponse);
        Set<String> apiSet = readOwnResponse.getPermissionList().stream().map(Permission::getPath).collect(Collectors.toSet());
        model.addAttribute("apiSet", apiSet);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/pages/user", method = RequestMethod.GET)
    public String user() {
        return "pages/user";
    }

    @RequestMapping(value = "/pages/permission", method = RequestMethod.GET)
    public String permission() {
        return "pages/permission";
    }

    @RequestMapping(value = "/pages/permissionGroup", method = RequestMethod.GET)
    public String permissionGroup() {
        return "pages/permissionGroup";
    }

    @RequestMapping(value = "/pages/role", method = RequestMethod.GET)
    public String role() {
        return "pages/role";
    }
}
