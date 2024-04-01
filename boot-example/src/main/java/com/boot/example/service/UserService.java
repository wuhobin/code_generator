package com.boot.example.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.example.entity.UserDO;
import com.boot.example.vo.UserVO;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuhongbin
 * @since 2024-03-21
 */
public interface UserService extends IService<UserDO> {


    void register(UserVO user);


    void sendCode(String email, Integer type);

    UserDO selectUserByEmail(String email);


    SaTokenInfo login(UserVO user);
}
