package com.code.generator.enums;

import lombok.Getter;

/**
 * @author wuhongbin
 * @ClassName MybatisPlusConfig
 * @description: 常用返回码枚举
 * @date 2024年03月21日
 * @version: 1.0.0
 */
@Getter
public enum ResultCode {

    SUCCESS(200,"请求成功"),

    VERBOSE(400,"请求参数不正确"),

    FAILED(500,"请求失败");

    private final int code;

    private final String msg;

    ResultCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
