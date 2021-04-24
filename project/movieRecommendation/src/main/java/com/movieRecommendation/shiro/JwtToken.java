package com.movieRecommendation.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * User: LiDenghui
 * Date: 2021/3/20
 * Time: 22:53
 **/
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt) {
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
