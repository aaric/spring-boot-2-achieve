package io.sparrow.sb2.auth.controller;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.mvc.token.JwtHelper;
import com.incarcloud.mvc.token.entity.AuthTokenInfo;
import com.incarcloud.mvc.token.entity.LoginUserInfo;
import com.incarcloud.mvc.token.settings.AuthJwtProperties;
import io.sparrow.sb2.auth.api.AuthApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录授权模块控制器
 *
 * @author Aaric, created on 2019-08-09T14:00.
 * @version 0.7.0-SNAPSHOT
 */
@RestController
@RequestMapping("/api/app/auth")
public class AuthController implements AuthApi {

    @Autowired
    private AuthJwtProperties authJwtProperties;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    @PostMapping("/login")
    public ResponseData<AuthTokenInfo> login(@RequestBody LoginUserInfo loginUserInfo, HttpServletRequest request) {
        // 获得客户端ID字符串
        String cid = request.getHeader(authJwtProperties.getCidHeaderName());

        // TODO 验证用户登录信息

        // 创建并返回Token字符串
        return ResponseData.ok(new AuthTokenInfo(cid, jwtHelper.createToken(cid, 1))).extraMsg("请求成功");
    }

    @Override
    @PostMapping("/logout")
    public ResponseData<Object> logout() {
        return ResponseData.ok().extraMsg("清除本地存储的Token字符串即可");
    }
}
