package com.incarcloud.sb2.test.controller;

import com.incarcloud.sb2.test.api.TestApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 框架测试控制器
 *
 * @author Aaric, created on 2019-06-26T15:37.
 * @since 0.2.1-SNAPSHOT
 */
@RestController
@RequestMapping("/test")
public class TestController implements TestApi {
}
