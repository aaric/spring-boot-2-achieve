package com.incarcloud.mvc.advice;

import com.incarcloud.common.data.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fliedName = ((FieldError) error).getField();
            String errorMessage = ((FieldError) error).getDefaultMessage();
            errors.put(fliedName, errorMessage);
        });

        // 提示：数据校验失败
        return ResponseData.error(ResponseData.ERROR_0021, errors).extraMsg("数据校验失败");
    }
}
