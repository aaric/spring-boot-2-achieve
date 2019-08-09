package com.incarcloud.sb2.auth.controller;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.mvc.token.JwtHelper;
import com.incarcloud.mvc.token.entity.AuthTokenInfo;
import com.incarcloud.mvc.token.entity.LoginUserInfo;
import com.incarcloud.sb2.auth.api.AuthApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录授权模块控制器
 *
 * @author Aaric, created on 2019-08-09T14:00.
 * @since 0.7.0-SNAPSHOT
 */
@RestController
@RequestMapping("/api/app/auth")
public class AuthController implements AuthApi {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    @PostMapping("/login")
    public ResponseData<AuthTokenInfo> login(@RequestBody LoginUserInfo loginUserInfo, @RequestHeader("x-access-cid") String cid) {
        return ResponseData.ok(new AuthTokenInfo(cid, jwtHelper.createToken(cid, 1))).extraMsg("请求成功");
    }

    @Override
    @PostMapping("/logout")
    public ResponseData<Object> logout() {
        return ResponseData.ok().extraMsg("清除本地存储的Token字符串即可");
    }
}
