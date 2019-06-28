package com.incarcloud.sb2.controller.test.controller;

import com.incarcloud.sb2.controller.test.api.TestApi;
import com.incarcloud.sb2.dto.user.LoginUserDto;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试框架模块控制器
 *
 * @author Aaric, created on 2019-06-26T15:37.
 * @since 0.2.1-SNAPSHOT
 */
@RestController
@RequestMapping("/api/plat/test")
public class TestController implements TestApi {

    @Override
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") Integer id) {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0000");
        returnData.put("data", new LoginUserDto("root", "root"));
        return returnData;
    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody LoginUserDto loginUserDto) {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0000");
        returnData.put("data", loginUserDto);
        return returnData;
    }
}
