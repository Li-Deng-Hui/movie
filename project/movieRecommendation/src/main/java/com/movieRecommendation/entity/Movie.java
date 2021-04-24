package com.movieRecommendation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author 代码生成器
 * @since 2021-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "movieid",type = IdType.AUTO)
    private Integer movieid;

    private String moviename;

    private LocalDate showyear;

    private String nation;

    private String director;

    private String leadactors;

    private String screenwriter;

    private String picture;

    /**
     * 鐢靛奖鎬昏瘎鍒
     */
    private Double averating;

    /**
     * 鐑?害
     */
    private Integer numrating;

    /**
     * 鍓ф儏绠?粙
     */
    private String description;

    private String typelist;

    private String backpost;


}
