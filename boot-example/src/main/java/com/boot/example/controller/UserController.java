package com.boot.example.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.boot.example.enums.ResultCode;
import com.boot.example.service.UserService;
import com.boot.example.utils.CommonResult;
import com.boot.example.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关controller
 * @author wuhongbin
 * @ClassName ChatController
 * @description: TODO
 * @date 2024年03月20日
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 登录
     * @return
     */
    @SaIgnore
    @PostMapping("/login")
    public CommonResult login(@Validated(UserVO.login.class) @RequestBody UserVO user) {
        return CommonResult.success(userService.login(user));
    }


    /**
     * 注册
     * @param user
     * @return
     */
    @SaIgnore
    @PostMapping("/register")
    public CommonResult register(@Validated(UserVO.register.class) @RequestBody UserVO user) {
        userService.register(user);
        return CommonResult.success();
    }

    /**
     * 发送验证码
     * @param email
     * @param type 2 注册 3 忘记密码
     * @return
     */
    @SaIgnore
    @PostMapping("/sendCode")
    public CommonResult sendCode(String email, Integer type) {
        if (StringUtils.isEmpty(email) || ObjectUtils.isEmpty(type)) {
            return CommonResult.failed(ResultCode.VERBOSE);
        }
        userService.sendCode(email, type);
        return CommonResult.success();
    }


}
