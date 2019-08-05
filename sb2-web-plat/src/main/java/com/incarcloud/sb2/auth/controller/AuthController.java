package com.incarcloud.sb2.auth.controller;

import com.incarcloud.sb2.auth.api.AuthApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 登录授权模块控制器
 *
 * @author Aaric, created on 2019-08-02T17:58.
 * @since 0.6.0-SNAPSHOT
 */
@RestController
@RequestMapping("/api/plat/auth")
public class AuthController implements AuthApi {

    /**
     * Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     * if (!(authentication instanceof AnonymousAuthenticationToken)) {
     *     String currentUserName = authentication.getName();
     *     return currentUserName;
     * }
     */

    @Override
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Map<String, Object> current(HttpServletRequest request) {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0000");
        returnData.put("message", "current");

        Optional<Principal> principal = Optional.ofNullable(request.getUserPrincipal());
        returnData.put("data", principal.map(Principal::getName).orElse(null));
        return returnData;
    }
}
