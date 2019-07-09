package com.incarcloud.sb2.test.controller;

import com.incarcloud.sb2.test.api.TestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    @RequestMapping(value = "/i18n", method = RequestMethod.GET)
    public Map<String, Object> i18n() {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0000");
        returnData.put("message", messageSource.getMessage("tipsdefault.success", null, LocaleContextHolder.getLocale()));
        return returnData;
    }
}
