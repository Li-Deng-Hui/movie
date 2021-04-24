package com.movieRecommendation.controller;

import com.movieRecommendation.common.lang.Result;
import com.movieRecommendation.spider.DouBanSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * User: LiDenghui
 * Date: 2021/4/17
 * Time: 23:46
 **/
@Controller
@RequestMapping("/spider")
public class SpiderController {
    @Autowired
    private DouBanSpider douBanSpider;

    @GetMapping("start")
    @ResponseBody
    public Result startSpider(HttpServletRequest request){
        douBanSpider.spiderDouban();
        return Result.succ("启动成功");
    }
}
