package com.incarcloud.sb2.test.controller;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.sb2.test.api.TestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试框架模块控制器
 *
 * @author Aaric, created on 2019-07-03T12:40.
 * @since 0.3.0-SNAPSHOT
 */
@RestController
@RequestMapping("/api/app/test")
public class TestController implements TestApi {

    @Autowired
    private MessageSource messageSource;

    @Override
    @GetMapping("/i18n")
    public ResponseData<Object> i18n() {
        String data = messageSource.getMessage("tips.default.success", null, LocaleContextHolder.getLocale());
        return ResponseData.ok(data);
    }
}
