package utils;

import model.DyttModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangSi
 * @version V1.0
 * @Title: DyttParse
 * @Package utils
 * @Description: 分析电影列表
 * Copyright: Copyright (c) 2017
 * @create 2017-02-09 上午 10:35
 **/

public class DyttParse {
    public static List<DyttModel> getData(String html) {
        List<DyttModel> data = new ArrayList<DyttModel>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div[class=co_content8]").select("tr");
        for (Element ele : elements) {
            String url = ele.select("td").eq(0).select("a").eq(1).attr("href");
            String title = ele.select("td").eq(0).select("a").eq(1).text();
            String newDate = ele.select("td").eq(1).select("font").text();
            DyttModel dyttModel = new DyttModel();
            dyttModel.setMovieTitle(title);
            dyttModel.setMovieUrl(url);
            dyttModel.setNewDate(newDate);
            data.add(dyttModel);
        }
        //返回数据
        return data;
    }
}
