package com.movieRecommendation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class TMoviecriticism implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "commentId", type = IdType.AUTO)
    private Long commentId;

    /**
     * 电影id
     */
    @TableField("movieId")
    private String movieId;

    /**
     * 回复用户id
     */
    @TableField("userId")
    private Long userId;

    /**
     * 电影评论
     */
    private String criticism;

    /**
     * 是否是回复评论 0不是，1是
     */
    @TableField("replyComment")
    private Integer replyComment;

    /**
     * 被回复用户id
     */
    @TableField("replyUserId")
    private Long replyUserId;

    /**
     * 回复评论id
     */
    @TableField("replyCommentId")
    private String replyCommentId;

    /**
     * 回复数
     */
    @TableField("replyNumber")
    private Integer replyNumber;

    /**
     * 点赞数
     */
    private Integer thumb;

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
