package com.movieRecommendation.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * User: LiDenghui
 * Date: 2021/3/20
 * Time: 11:23
 **/
@Configuration
@EnableTransactionManagement
@MapperScan("com.movieRecommendation.mapper")
public class MybatisPlusConfig {
    /**
     *PaginationInterceptor是一个分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}