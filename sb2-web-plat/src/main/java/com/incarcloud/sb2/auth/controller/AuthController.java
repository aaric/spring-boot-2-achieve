package com.incarcloud.sb2.auth.controller;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.sb2.auth.api.AuthApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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
     * String currentUserName = authentication.getName();
     * return currentUserName;
     * }
     */

    @Override
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public ResponseData<String> current(HttpServletRequest request) {
        Optional<Principal> principal = Optional.ofNullable(request.getUserPrincipal());
        String userName = principal.map(Principal::getName).orElse(null);

        // 返回用户信息
        if (StringUtils.isNotEmpty(userName)) {
            return ResponseData.ok(userName);
        }

        // 提示：权限不足
        return ResponseData.error(ResponseData.ERROR_0031).extraMsg("权限不足");
    }
}
