package com.movieRecommendation.controller;

import com.movieRecommendation.spark.WordCount;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: LiDenghui
 * Date: 2021/4/1
 * Time: 9:30
 **/
@Controller
@RequestMapping("/spark")
public class SparkSqlController {
    @GetMapping("/start")
    @ResponseBody
    public String  startSpark(){
        new WordCount().start();
        return "spark启动成功";
    }
}
