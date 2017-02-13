package model;

/**
 * @author zhangSi
 * @version V1.0
 * @Title: DyttModel
 * @Package model
 * @Description: 电影天堂(http://dytt8.net/index1.htm)
 * Copyright: Copyright (c) 2017
 * @create 2017-02-09 上午 10:32
 **/

public class DyttModel {
    private String movieId;
    private String movieTitle;
    private String movieUrl;
    private String newDate;

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieName) {
        this.movieTitle = movieName;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }
}
