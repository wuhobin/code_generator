package com.boot.example.exception;

import lombok.Getter;

/**
 * @author wuhongbin
 * @ClassName CustomException
 * @description: TODO
 * @date 2024年03月29日
 * @version: 1.0.0
 */
public class CustomException extends RuntimeException{

    @Getter
    private Integer code;

    private String message;

    public CustomException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }


}
