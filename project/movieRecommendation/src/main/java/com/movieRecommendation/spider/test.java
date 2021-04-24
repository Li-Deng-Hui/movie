package com.movieRecommendation.spider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: LiDenghui
 * Date: 2021/4/17
 * Time: 23:22
 **/
public class test {
    public static void main(String[] args) {
        String regex   = "([0-9]{4}-[0-9]{2}-[0-9]{2})";
        Pattern p  = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        String str = "2021-04-17(中国大陆) / 2021-04-16(日本)";
        Matcher m   = p.matcher(str);

        if (m.find())  {
            String  foundStr = str.substring(m.start(),  m.end());
            System.out.println("Found string  is:" + m.group());
            System.out.println("Found string  is:" + foundStr);
        }
    }
}
