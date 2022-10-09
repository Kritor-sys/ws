package com.study.ws.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 全局捕获运行时异常
 */
public class GlobalException extends RuntimeException
{
    @Getter
    @Setter
    private String msg;
    public GlobalException(String message){
        this.msg = message;
    }
}
