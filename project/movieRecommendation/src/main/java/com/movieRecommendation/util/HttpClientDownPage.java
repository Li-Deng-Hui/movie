package com.movieRecommendation.util;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * User: LiDenghui
 * Date: 2021/4/17
 * Time: 17:00
 **/
public class HttpClientDownPage {
    //设置代理，模范浏览器
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36";
    private static String cookie="bid=xpD356i3FFM; ll=\"108296\"; __utmc=30149280; __utmc=223695111; _vwo_uuid_v2=D7C792EF6E0156B052AAD0B964926D42E|2e1ef8b5d395fbd1352af5a636e0c51a; ct=y; push_noty_num=0; push_doumail_num=0; dbcl2=\"235182064:KuMLHgUcNok\"; ck=4gGE; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1618724442%2C%22https%3A%2F%2Faccounts.douban.com%2F%22%5D; _pk_ses.100001.4cf6=*; __utma=30149280.1633302421.1618655660.1618715521.1618724442.6; __utmb=30149280.0.10.1618724442; __utmz=30149280.1618724442.6.5.utmcsr=accounts.douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/; __utma=223695111.1864498126.1618655660.1618715522.1618724442.5; __utmb=223695111.0.10.1618724442; __utmz=223695111.1618724442.5.4.utmcsr=accounts.douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/; _pk_id.100001.4cf6=8692e0b0969fdce4.1618655660.5.1618724472.1618718499.";
    public static String sendGet(String url){
        //1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
        CloseableHttpResponse response = null;
        String html = null;
        //2.创建get请求，相当于在浏览器地址栏输入 网址
        HttpGet request = new HttpGet(url);
        try {
            request.setHeader("User-Agent",USER_AGENT);
            request.setHeader("Cookie",cookie);
            request.setConfig(requestConfig);
            //3.执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);
            //4.判断响应状态为200，进行处理
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                html = EntityUtils.toString(httpEntity, "GBK");
            } else {
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
        return html;
    }
}
