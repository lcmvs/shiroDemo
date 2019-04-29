package com.lcm.demo.shirodemo.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Description:自定义全局异常
 *
 * @author lcm
 * @date 2019/4/3 13:56
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GlobalException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -6316805338179147883L;

    /**
     * 状态码
     */
    private int code = HttpStatus.INTERNAL_SERVER_ERROR.value();

    /**
     * 异常信息
     */
    private String msg;

    public GlobalException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public GlobalException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public GlobalException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public GlobalException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

}
