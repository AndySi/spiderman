package main;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.org.mozilla.javascript.internal.json.JsonParser;

import java.io.IOException;

/**
 * @author zhangSi
 * @version V1.0
 * @Title: HuabanMain
 * @Package main
 * @Description: 花瓣网爬取图片
 * Copyright: Copyright (c) 2017
 * @create 2017-02-10 上午 10:52
 **/

public class HuabanMain {
    public static HttpResponse getRawHtml(String requrl) {
        HttpClient client = new DefaultHttpClient();
        //获取响应文件，即html，采用get方法获取响应数据
        HttpGet getMethod = new HttpGet(requrl);
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                HttpStatus.SC_OK, "OK");
        try {
            response = client.execute(getMethod);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // getMethod.abort();
        }
        return response;
    }

    public static String urlParse(String reqUrl) {
        HttpResponse response = HuabanMain.getRawHtml(reqUrl);
        int statusCode = response.getStatusLine().getStatusCode();
        String entity = "";
        try {
            if (statusCode == 200) {
                entity = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(response.getEntity());
            } else {
                EntityUtils.consume(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public static void main(String[] args) {
        String reqUrl = "http://huaban.com/boards/31734506/";
        String html = HuabanMain.urlParse(reqUrl);
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div[class=pin wfc]");
        for (Element ele : elements) {
            String id = ele.attr("data-id");
            String src = ele.select("img").attr("src");
            String title = ele.select("img").attr("alt");

        }
    }
}
