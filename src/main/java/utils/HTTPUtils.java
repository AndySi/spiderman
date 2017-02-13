package utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHttpResponse;

import java.io.IOException;

/**
 * @author zhangSi
 * @version V1.0
 * @Title: HTTPUtils
 * @Package utils
 * @Description: ${DESCRIPTION}
 * Copyright: Copyright (c) 2017
 * @create 2017-02-09 上午 10:17
 **/

public abstract class HTTPUtils {
    public static HttpResponse getRawHtml(HttpClient client, String reqUrl) {
        //获取响应文件，即html，采用get方法获取响应数据
        HttpGet getMethod = new HttpGet(reqUrl);
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                HttpStatus.SC_OK, "OK");
        try {
            //执行Get方法
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
}
