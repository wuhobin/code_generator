package com.boot.example.service.impl;


import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.example.entity.UserDO;
import com.boot.example.enums.EmailCodeType;
import com.boot.example.mapper.UserMapper;
import com.boot.example.service.UserService;
import com.boot.example.utils.FunctionUtils;
import com.boot.example.utils.MailUtil;
import com.boot.example.vo.UserVO;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuhongbin
 * @since 2024-03-21
 */
@Service
@Slf4j
public class UserServiceImp extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private Cache caffeineExpireCache;

    private final String emailAddress = "email:%s";

    @Override
    public SaTokenInfo login(UserVO user) {
        UserDO userByEmail = selectUserByEmail(user.getEmail());
        FunctionUtils.isTrue(ObjectUtils.isEmpty(userByEmail)).throwException("该用户不存在，请先注册");
        FunctionUtils.isTrue(!userByEmail.getPassword().equals(SecureUtil.md5(user.getPassword()))).throwException("密码错误");
        StpUtil.login(userByEmail.getUserId());
        return StpUtil.getTokenInfo();
    }


    @Override
    public void register(UserVO user) {
        UserDO userByEmail = selectUserByEmail(user.getEmail());
        FunctionUtils.isTrue(!ObjectUtils.isEmpty(userByEmail)).throwException("该邮箱地址已被注册");
        String cacheCode = (String) caffeineExpireCache.getIfPresent(String.format(emailAddress, user.getEmail()));
        FunctionUtils.isTrue(!user.getCode().equals(cacheCode)).throwException("验证码错误");
        UserDO userDO = BeanUtil.copyProperties(user, UserDO.class);
        userDO.setUserId(RandomStringUtils.randomNumeric(8));
        userDO.setPassword(SecureUtil.md5(user.getPassword()));
        save(userDO);
    }

    @Override
    public void sendCode(String email, Integer type) {
        UserDO user = selectUserByEmail(email);
        if (type.equals(EmailCodeType.REGISTER.getType())){
            FunctionUtils.isTrue(!ObjectUtils.isEmpty(user)).throwException("该邮箱地址已被注册");
        }else {
            FunctionUtils.isTrue(ObjectUtils.isEmpty(user)).throwException("该邮箱地址未注册");
        }
        String code = RandomStringUtils.randomNumeric(6);
        CompletableFuture.runAsync(() -> {
            mailUtil.sendMailMessage(Collections.singletonList(email), "垂直领域聊天大模型账号管理", buildText(email, code, type));
        }).thenRunAsync(() -> {
            caffeineExpireCache.asMap().put(String.format(emailAddress, email), code);
        });
    }

    @Override
    public UserDO selectUserByEmail(String email) {
        return lambdaQuery().eq(UserDO::getEmail, email).last("limit 0,1").one();
    }

    private String buildText(String email, String code, Integer type){
        String text = "";
        if (type.equals(EmailCodeType.REGISTER.getType())){
            text = "您好, 我们收到了您在 垂直领域聊天大模型 注册 %s 帐户的要求. <br> <br>下方是您注册的验证码： <strong>%s</strong><br><br>该验证码有效期为5分钟 <br><br> 如果您不打算注册或不是您注册的, 请忽略此消息.";
        }else {
            text = "您好, 我们收到了您在 垂直领域聊天大模型 修改 %s 帐户密码的要求. <br> <br>下方是您修改密码的验证码： <strong>%s</strong><br><br>该验证码有效期为5分钟 <br><br> 如果您不打算修改密码或不是您操作的, 请忽略此消息.";
        }
        return String.format(text, email, code);
    }

}
