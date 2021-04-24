package com.movieRecommendation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 代码生成器
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @NotBlank(message = "昵称不能为空")
    private String username;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 用户角色 0普通用户，1管理员
     */
    private Integer role;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;

    /**
     * 最后一次登录时间
     */
    private LocalDateTime lastlogin;


}
