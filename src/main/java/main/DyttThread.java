package main;

import db.MYSQLControl;
import db.MyDataSource;
import model.DyttModel;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import utils.URLParser;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangSi
 * @version V1.0
 * @Title: DyttThread
 * @Package main
 * @Description: 多线程
 * Copyright: Copyright (c) 2017
 * @create 2017-02-09 下午 1:53
 **/

public class DyttThread extends Thread{
    public static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/moviedata");
    public static QueryRunner qr = new QueryRunner(ds);

    String reqUrl = "";
    public  DyttThread(String reqUrl){
        this.reqUrl = reqUrl;
    }
    @Override
    public void run() {
        try {
            HttpClient client = new DefaultHttpClient();
            List<DyttModel> datas = URLParser.parser(client, reqUrl);
            MYSQLControl.executeInsert(datas);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        ResultSetHandler<List<DyttModel>> h = new BeanListHandler<DyttModel>(DyttModel.class);
        List<DyttModel> Starturls = qr.query("SELECT `movieId`,`movieTitle`,`movieUrl`,`newDate` FROM `moviedata`.`tb_dytt_url`", h);
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (DyttModel d : Starturls){
            exec.execute(new DyttThread("http://dytt8.net"+d.getMovieUrl()));
        }
    }
}
