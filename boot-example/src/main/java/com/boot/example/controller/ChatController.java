package com.boot.example.controller;

import com.boot.example.utils.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuhongbin
 * @ClassName ChatController
 * @description: TODO
 * @date 2024年03月20日
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @GetMapping("/hello")
    public CommonResult chat() {
        return CommonResult.success("hello");
    }
}
