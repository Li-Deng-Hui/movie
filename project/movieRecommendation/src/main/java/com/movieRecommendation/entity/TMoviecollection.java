package com.movieRecommendation.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class TMoviecollection implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("userId")
    private Long userId;

    /**
     * 电影id
     */
    @TableField("movieId")
    private String movieId;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;


}
