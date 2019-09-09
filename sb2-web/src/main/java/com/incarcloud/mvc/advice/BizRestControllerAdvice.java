package com.incarcloud.mvc.advice;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局控制器建议
 *
 * @author Aaric, created on 2019-07-08T18:00.
 * @since 0.4.0-SNAPSHOT
 */
@Slf4j
@RestControllerAdvice
public class BizRestControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    /**
     * 获得i18n信息
     *
     * @param errorCode 错误码
     * @return
     */
    private String getExtraMsg(String errorCode) {
        // 要求开发者定义错误信息格式为：tips.default.error.{code}
        return messageSource.getMessage("tips.default.error." + errorCode, null, LocaleContextHolder.getLocale());
    }

    /**
     * 处理数据校验异常
     *
     * @param ex 数据校验异常
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fliedName = ((FieldError) error).getField();
            String errorMessage = ((FieldError) error).getDefaultMessage();
            errors.put(fliedName, errorMessage);
        });

        // 提示：数据校验失败
        String errorCode = ResponseData.ERROR_0021;
        return ResponseData.error(errorCode, errors).extraMsg(getExtraMsg(errorCode));
    }

    /**
     * 处理自定义API异常
     *
     * @param e 自定义API异常
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ApiException.class)
    public ResponseData<Object> handleApiException(ApiException e) {
        // 自定义请求API异常
        String message = getExtraMsg(e.getCode());
        if (null != e.getData()) {
            return ResponseData.error(e.getCode(), e.getData()).extraMsg(message);
        } else {
            return ResponseData.error(e.getCode()).extraMsg(message);
        }
    }
}
