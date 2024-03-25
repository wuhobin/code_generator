package com.boot.example;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.type.JdbcType;

import java.util.Collections;

/**
 * mybatis-plus代码生成器
 *
 * @author wuhongbin
 */
public class CodeGeneratorPlus {

    private static final String JDBC_URL = "jdbc:mysql://rm-bp1j8k9s2330jz5jdyo.mysql.rds.aliyuncs.com:3306/aurora-chat?useUnicode=true&characterEncoding=UTF-8";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "Whb18772916901";

    /**
     * 父级包名配置
     */
    private static final String PARENT_PACKAGE = "com.chat.aurorachat";

    /**
     * 项目业务module
     */
    private static final String MODULE_NAME = "aurora-chat";

    /**
     * 生成代码的 @author 值
     */
    private static final String AUTHOR = "wuhongbin";

    /**
     * 项目地址[改为自己本地项目位置]
     */
    private static final String PROJECT_PATH = "D:/idea_wuhongbin/自己的项目/aurora-chat-all";


    /**
     * 要生成代码的表名配置
     */
    private static final String[] TABLES = {
            "t_user"
    };


    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)
            .typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                // 兼容旧版本转换成Integer
                if (JdbcType.TINYINT == metaInfo.getJdbcType()) {
                    return DbColumnType.BOOLEAN;
                }
                if (JdbcType.TIMESTAMP == metaInfo.getJdbcType() || JdbcType.DATE == metaInfo.getJdbcType() || metaInfo.getTypeName().toLowerCase().contains("datetime")) {
                    return DbColumnType.DATE;
                }
                return typeRegistry.getColumnType(metaInfo);
            });
    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        //1、配置数据源
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                //2、全局配置
                .globalConfig(builder -> {
                    builder
                            .outputDir(PROJECT_PATH + "/" + MODULE_NAME + "/src/main/java")  //指定输出目录
                            .author(AUTHOR)    // 作者名
                            .disableOpenDir()  // 禁止打开输出目录
                            .dateType(DateType.ONLY_DATE) //时间策略
                            .commentDate("yyyy-MM-dd"); // 注释日期
                })  //3、包配置
                .packageConfig(builder -> {
                    builder
                            .parent(PARENT_PACKAGE)
                            .entity("dataobject")   // Entity 包名
                            .mapper("mapper")       // Mapper 包名
                            .service("service")    //  Service 包名
                            .serviceImpl("service.impl") //Service Impl 包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, PROJECT_PATH + "/" + MODULE_NAME + "/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder
                            .enableCapitalMode()    //开启大写命名
                            .enableSkipView()   //创建实体类的时候跳过视图
                            .addInclude(TABLES)  // 设置需要生成的数据表名
                            .addTablePrefix("t")  //设置 过滤 表的前缀
                            .entityBuilder()       // Entity 策略配置
                            .enableLombok()       //开启 lombok 模型
                            .idType(IdType.AUTO)    // 全局主键类型
                            .formatFileName("%sDO")
                            .enableRemoveIsPrefix()  //开启 Boolean 类型字段移除 is 前缀
                            .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略
                            .columnNaming(NamingStrategy.underline_to_camel)  //数据库表字段映射到实体的命名策略
                            .controllerBuilder()   // Controller 策略配置
                            .enableRestStyle()
                            .formatFileName("%sController")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImp")
                            .mapperBuilder()
                            .enableBaseColumnList()
                            .enableBaseResultMap()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper");
                })
                .templateConfig(builder -> {
                    builder
                            .controller(null);// 不生成controller
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
