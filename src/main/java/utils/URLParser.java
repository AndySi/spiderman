package utils;

import model.DyttModel;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangSi
 * @version V1.0
 * @Title: URLParser
 * @Package utils
 * @Description: ${DESCRIPTION}
 * Copyright: Copyright (c) 2017
 * @create 2017-02-09 上午 10:22
 **/

public class URLParser {
    public static List<DyttModel> parser (HttpClient client, String url) throws Exception {
        List<DyttModel> data = new ArrayList<DyttModel>();
        //获取网站响应的html，这里调用了HTTPUtils类
        HttpResponse response = HTTPUtils.getRawHtml(client, url);
        //获取响应状态码
        int StatusCode = response.getStatusLine().getStatusCode();
        //如果状态响应码为200，则获取html实体内容或者json文件
        if(StatusCode == 200){
            String entity = EntityUtils.toString (response.getEntity(),"GBK");
            data = DyttParse.getData(entity);
            EntityUtils.consume(response.getEntity());
        }else{
            //否则，消耗掉实体
            EntityUtils.consume(response.getEntity());
        }
        return data;
    }
}
