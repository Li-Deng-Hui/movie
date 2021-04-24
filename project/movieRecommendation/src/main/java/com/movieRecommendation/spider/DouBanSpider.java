package com.movieRecommendation.spider;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.movieRecommendation.entity.Movie;
import com.movieRecommendation.entity.TMovieinformation;
import com.movieRecommendation.service.MovieService;
import com.movieRecommendation.service.TMovieinformationService;
import com.movieRecommendation.util.HttpClientDownPage;
import com.movieRecommendation.util.LocalDateTimeStringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: LiDenghui
 * Date: 2021/4/17
 * Time: 17:10
 **/
@Component
public class DouBanSpider {
    @Autowired
    private TMovieinformationService tMovieinformationService;

    @Autowired
    private MovieService movieService;
    private int id=433;

    public void spiderDouban() {
        // 分类标签
        String tag[] = {"热门", "最新", "经典", "可播放", "豆瓣高分", "冷门佳片", "华语", "欧美", "韩国", "日本", "动作", "喜剧", "爱情", "科幻", "悬疑", "恐怖", "动画"};
        //排行榜 热度、时间、评分
        String sort[] = {"recommend", "time", "rank"};
        String url = "https://movie.douban.com/j/search_subjects?type=movie&tag=:tag&sort=recommend&page_limit=100&page_start=:start";
        for (String tagItem:tag){
            for (int i=3;i<10;i++){
                String urls=url.replaceAll(":tag",tagItem).replaceAll(":start",i*100+"");
                sendUrl(urls);
            }
        }

        //Document jsDocument=Jsoup.parse(document);
        //System.out.printf(document);
    }
    private void sendUrl(String url){
        try {
            String document = HttpClientDownPage.sendGet(url);
            JSONObject jsonObject = JSONUtil.parseObj(document);
            JSONArray jsonArray = JSONUtil.parseArray(jsonObject.get("subjects"));
            for (Object o : jsonArray) {
                JSONObject jsonObject1 = new JSONObject(o);
                String movieId = jsonObject1.get("id").toString();
                String movieTitle = jsonObject1.get("title").toString();
                String movieUrl = jsonObject1.get("url").toString();
                String moviePic = jsonObject1.get("cover").toString();
                String movieRate = jsonObject1.get("rate").toString();
                TMovieinformation tMovieinformation = new TMovieinformation();
                tMovieinformation.setMovieId(movieId);
                Date date=new Date();
                tMovieinformation.setCreateTime(LocalDateTimeStringUtil.convertDate2LocalDateTime(date));
                TMovieinformation tMovieinformations=tMovieinformationService.getById(tMovieinformation.getMovieId());
                if (tMovieinformations!=null){
                    tMovieinformation=tMovieinformations;
                }
                tMovieinformation.setUpdateTime(LocalDateTimeStringUtil.convertDate2LocalDateTime(date));
                tMovieinformation.setMovieName(movieTitle);
                tMovieinformation.setMovieUrl(movieUrl);
                tMovieinformation.setMoviePoster(moviePic);
                tMovieinformation.setMovieScore(Double.parseDouble(movieRate));
                try {
                    String documentItem = HttpClientDownPage.sendGet(tMovieinformation.getMovieUrl());
                    //System.out.println(tMovieinformation.toString());
                    Document jsDocument = Jsoup.parse(documentItem);
                    //System.out.println(jsDocument.outerHtml());
                    paraseList(jsDocument, tMovieinformation);
                    //break;
                }catch (Exception e){
                    continue;
                }

            }
        }catch (Exception e){

        }

    }

    private void paraseList(Document document, TMovieinformation tMovieinformation) {
        //根据网页标签解析源码

        Elements elements = document.select("#content > div.grid-16-8.clearfix > div.article");
        //去除表头
        // elements.remove(0);
        Element element = elements.get(0);
        //System.out.println(element.html());

        // 获取基础信息
        Element element1 = element.select("#info").get(0);
        // 获取导演
        String director = element1.select("span:nth-child(1) > span.attrs > a").text();
        // 获取编剧
        String screenwriter=element1.select("span:nth-child(3) > span.attrs").text();
        // 获取主演
        String starring=element1.select("span.actor > span.attrs").text();
        // 获取类型
        String type="";
        Elements elements1=element1.select("span[property=\"v:genre\"]");
        for (Element etype:elements1){
            type+=","+etype.text();
        }
        // 获取地区和语言
        String text=element1.text();
        String p="国家/地区:(.*)语言:(.*)上映日期:(.*)片长";
        Pattern pattern=Pattern.compile(p);
        Matcher matcher=pattern.matcher(text);
        String area="";
        String language="";
        String releaseTime="";
        if (matcher.find()){
            area=matcher.group(1);
            language=matcher.group(2);
            releaseTime=matcher.group(3);
        }
        // 获取上映日期
        //String releaseTime=element1.select("#info > span:nth-child(17)").text();
        // 获取Imdb链接
        String imdb=element1.select("#info > a").text();

        // 获取评分信息
        Element element2 = element.select("#interest_sectl").get(0);
        // 获取评价人数
        String evaluator=element2.select("#interest_sectl > div.rating_wrap.clearbox > div.rating_self.clearfix > div > div.rating_sum > a > span").text();

        // 获取简介
        String briefIntroduction=element.select("#link-report > span").text();

        tMovieinformation.setMovieDirector(director);
        tMovieinformation.setMovieActor(starring);
        tMovieinformation.setMovieType(type);
        tMovieinformation.setMovieTime(releaseTime);
        tMovieinformation.setMovieLanage(language);
        tMovieinformation.setMovieTag(area);
        tMovieinformation.setMovieHot(Integer.parseInt(evaluator));
        tMovieinformation.setMovieInfor(briefIntroduction);
        try {
            tMovieinformationService.saveOrUpdate(tMovieinformation);
        }catch (Exception e){
            System.out.println("数据插入失败");
        }


        Movie movie=new Movie();
        pattern=Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        matcher=pattern.matcher(tMovieinformation.getMovieTime());

        //movie.setMovieid(Integer.parseInt(tMovieinformation.getMovieId()));
        movie.setMovieid(id);
        System.out.println(movie.getMovieid());
        id=id+1;
        if (matcher.find()){
            movie.setShowyear(LocalDateTimeStringUtil.parserString2LocalDate(matcher.group()));
        }
        movie.setMoviename(tMovieinformation.getMovieName());
        movie.setAverating(tMovieinformation.getMovieScore());
        movie.setDirector(tMovieinformation.getMovieDirector());
        movie.setLeadactors(tMovieinformation.getMovieActor());
        movie.setPicture(tMovieinformation.getMoviePoster());
        movie.setBackpost(tMovieinformation.getMoviePoster());
        movie.setTypelist(tMovieinformation.getMovieType());
        movie.setNumrating(tMovieinformation.getMovieHot());
        movie.setDescription(tMovieinformation.getMovieInfor());
        movieService.saveOrUpdate(movie);


        System.out.println(tMovieinformation.toString());
        System.out.println(movie.toString());
    }

    public static void main(String[] args) {
        DouBanSpider douBanSpider = new DouBanSpider();
        douBanSpider.spiderDouban();
    }
}
