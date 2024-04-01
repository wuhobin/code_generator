package com.boot.example.enums;

import lombok.Getter;

/**
 * @author wuhongbin
 * @ClassName MybatisPlusConfig
 * @description: 常用返回码枚举
 * @date 2024年03月21日
 * @version: 1.0.0
 */
@Getter
public enum EmailCodeType {

    LOGIN(1,"登录"),

    REGISTER(2,"注册"),

    FORGET_PASSWORD(3,"忘记密码");

    private final int type;

    private final String msg;

    EmailCodeType(int type, String msg){
        this.type = type;
        this.msg = msg;
    }
}
