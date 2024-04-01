package com.boot.example.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuhongbin
 * @since 2024-03-21
 */
@Data
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * user_id
     */
    private String userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {register.class})
    private String username;

    /**
     * 用户邮件地址
     */
    @NotBlank(message = "邮箱不能为空", groups = {register.class, login.class})
    private String email;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {register.class, login.class})
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空", groups = {register.class})
    private String code;

    /**
     * 用户头像
     */
    private String avatar;

    private Date createTime;

    private Date updateTime;

    public interface register{}
    public interface login{}
}
