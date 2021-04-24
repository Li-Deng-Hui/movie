package com.movieRecommendation.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 代码生成器
 * @since 2021-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TMovieinformation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 电影id
     */
    @TableId("movieId")
    private String movieId;

    /**
     * 电影名
     */
    @TableField("movieName")
    private String movieName;

    /**
     * 电影信息
     */
    @TableField("movieInfor")
    private String movieInfor;

    /**
     * 电影导演
     */
    @TableField("movieDirector")
    private String movieDirector;

    /**
     * 电影演员
     */
    @TableField("movieActor")
    private String movieActor;

    /**
     * 电影类型
     */
    @TableField("movieType")
    private String movieType;

    /**
     * 电影标签
     */
    @TableField("movieTag")
    private String movieTag;

    /**
     * 电影评分
     */
    @TableField("movieScore")
    private double movieScore;

    /**
     * 电影热度或观影人数
     */
    @TableField("movieHot")
    private Integer movieHot;

    /**
     * 电影收藏数
     */
    @TableField("movieCollection")
    private Integer movieCollection;

    /**
     * 电影上线时间
     */
    @TableField("movieTime")
    private String movieTime;
    @TableField("movieLanage")
    private String movieLanage;
    /**
     * 电影链接
     */
    @TableField("movieUrl")
    private String movieUrl;

    /**
     * 电影海报
     */
    @TableField("moviePoster")
    private String moviePoster;

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
