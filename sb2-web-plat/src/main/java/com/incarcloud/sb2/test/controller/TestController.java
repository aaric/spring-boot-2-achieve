package com.incarcloud.sb2.test.controller;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.sb2.dto.user.LoginUserDto;
import com.incarcloud.sb2.test.api.TestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseData<Object> i18n() {
        String data = messageSource.getMessage("tips.default.success", null, LocaleContextHolder.getLocale());
        return ResponseData.ok(data);
    }

    @Override
    @PostMapping(value = "/validate")
    public ResponseData<Object> validate(@Valid @RequestBody LoginUserDto loginUserDto) {
        return ResponseData.ok(loginUserDto);
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public ResponseData<LoginUserDto> get(@PathVariable("id") Integer id) {
        LoginUserDto loginUserDto = new LoginUserDto("root", "root", "root@incarcloud.com");
        return ResponseData.ok(loginUserDto);
    }
}
