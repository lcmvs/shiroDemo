package com.lcm.demo.shirodemo.common.exception;


import com.lcm.demo.shirodemo.common.constant.Constant;
import com.lcm.demo.shirodemo.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description:全局异常处理类
 *
 * @author lcm
 * @date 2019/4/3 21:41
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Description: 处理自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(GlobalException.class)
    public Result handlerGlobalException(GlobalException e) {
        Result result = new Result();
        result.put(Constant.CODE, e.getCode());
        result.put(Constant.MSG, e.getMessage());
        return result;
    }

    /**
     * Description: 数据库主键重复
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return Result.error("数据库主键重复");
    }

    /**
     * Description: 请求方法不正确
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return Result.error("请求方法不正确");
    }

    /**
     * Description: 数据校检异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        log.error(fieldError.getDefaultMessage(), e);
        return Result.error(400,fieldError.getDefaultMessage());
    }

    /**
     * Description: 其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error();
    }

}
