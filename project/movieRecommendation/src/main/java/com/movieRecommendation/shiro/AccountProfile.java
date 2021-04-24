package com.movieRecommendation.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * User: LiDenghui
 * Date: 2021/3/20
 * Time: 22:57
 **/
@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;

}
