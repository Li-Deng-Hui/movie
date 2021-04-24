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
public class TMoviereplycomment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 被回复用户id
     */
    @TableField("replyUserId")
    private Long replyUserId;

    /**
     * 回复用户id
     */
    @TableField("userId")
    private Long userId;

    /**
     * 评论id
     */
    @TableField("commentId")
    private Long commentId;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 是否删除 0未删除，1删除
     */
    private Integer isdelete;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;


}
