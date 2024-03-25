package com.boot.example.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.example.dataobject.UserDO;
import com.boot.example.mapper.UserMapper;
import com.boot.example.service.UserService;
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
