package com.book.backend.common;

/**
 * 自定义业务异常类
 * @author Per
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
