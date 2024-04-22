package com.code.generator.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 数据库 创建时间 更新时间 自动插入
 * @author wuhongbin
 * @ClassName TimeInsertHandler
 * @description: TODO
 * @date 2024年04月01日
 * @version: 1.0.0
 */
@Configuration
public class TimeInsertHandler  implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
