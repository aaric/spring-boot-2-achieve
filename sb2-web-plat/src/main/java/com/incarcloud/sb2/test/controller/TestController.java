package com.incarcloud.sb2.test.controller;

import com.incarcloud.sb2.dto.user.LoginUserDto;
import com.incarcloud.sb2.security.LoginUserInfo;
import com.incarcloud.sb2.test.api.TestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    private MessageSource messageSource;

    @Override
    @GetMapping(value = "/i18n")
    public Map<String, Object> i18n() {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0000");
        returnData.put("message", messageSource.getMessage("tips.default.success", null, LocaleContextHolder.getLocale()));
        return returnData;
    }

    @Override
    @PostMapping(value = "/validate")
    public Map<String, Object> validate(@Valid @RequestBody LoginUserDto loginUserDto) {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0000");
        returnData.put("data", loginUserDto);
        return returnData;
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public Map<String, Object> get(@PathVariable("id") Integer id) {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0000");
        returnData.put("data", new LoginUserDto("root", "root", "root@incarcloud.com"));
        return returnData;
    }

    @Override
    @RequestMapping(value = "/authRedirect", method = RequestMethod.GET)
    public Map<String, Object> authRedirect() {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0000");
        returnData.put("message", "authRedirect");
        return returnData;
    }

    @Override
    @PostMapping(value = "/authLogin")
    public Map<String, Object> fakeAuthLogin(@RequestBody LoginUserInfo loginUserInfo) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }
}
