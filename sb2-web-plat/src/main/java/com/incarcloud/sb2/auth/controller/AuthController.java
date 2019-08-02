package com.incarcloud.sb2.auth.controller;

import com.incarcloud.sb2.auth.api.AuthApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * 授权模块控制器
 *
 * @author Aaric, created on 2019-08-02T17:58.
 * @since 0.6.0-SNAPSHOT
 */
public class AuthController implements AuthApi {

    @Override
    @RequestMapping(value = "/failure", method = RequestMethod.GET)
    public Map<String, Object> failure() {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0000");
        returnData.put("message", "authRedirect");
        return returnData;
    }
}
