package com.incarcloud.sb2.auth.controller;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
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
     * <pre>
     * Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     * if (!(authentication instanceof AnonymousAuthenticationToken)) {
     *     String currentUserName = authentication.getName();
     *     return currentUserName;
     * }
     * </pre>
     */

    @Override
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public ResponseData<String> redirect(HttpServletRequest request) throws ApiException {
        // 获得当前登录用户名
        Optional<Principal> principal = Optional.ofNullable(request.getUserPrincipal());
        String userName = principal.map(Principal::getName).orElse(null);

        // 提示：用户未登录
        if (StringUtils.isEmpty(userName)) {
            throw new ApiException(ResponseData.ERROR_0031, "用户未登录");
        }

        // 返回用户信息
        return ResponseData.ok(userName);
    }
}
