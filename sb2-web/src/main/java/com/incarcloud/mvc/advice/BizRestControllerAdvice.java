package com.incarcloud.mvc.advice;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
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
@RestControllerAdvice
public class BizRestControllerAdvice {

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
        return ResponseData.error(ResponseData.ERROR_0021, errors).extraMsg("数据校验失败");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ApiException.class)
    public ResponseData<Object> handleApiException(ApiException e) {
        // 自定义请求API异常
        if (null != e.getData()) {
            return ResponseData.error(e.getCode(), e.getData()).extraMsg(e.getMessage());
        } else {
            return ResponseData.error(e.getCode()).extraMsg(e.getMessage());
        }
    }
}
