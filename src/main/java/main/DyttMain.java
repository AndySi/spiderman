package main;

import db.MYSQLControl;
import model.DyttModel;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import utils.HTTPUtils;
import utils.URLParser;

import java.util.List;

/**
 * @author zhangSi
 * @version V1.0
 * @Title: DyttMain
 * @Package main
 * @Description: ${DESCRIPTION}
 * Copyright: Copyright (c) 2017
 * @create 2017-02-09 上午 11:06
 **/

public class DyttMain {
    public static void main(String[] args) throws Exception {
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        String url = "http://dytt8.net/index1.htm";
        List<DyttModel> datas = URLParser.parser(client, url);
        MYSQLControl.executeInsert(datas);
    }
}
