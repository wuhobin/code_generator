package com.chat.aurorachat.service.impl;

import com.chat.aurorachat.dataobject.UserDO;
import com.chat.aurorachat.mapper.UserMapper;
import com.chat.aurorachat.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuhongbin
 * @since 2024-03-21
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, UserDO> implements UserService {

}
