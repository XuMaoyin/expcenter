package cn.ncist.glx.expcenter.beans.po;

import java.util.Date;

public class Notice {

    private Integer id;


    private String title;


    private Date datetime;


    private Date topTime;


    private Integer hits;


    private String content;

    //用于显示的公告大部日期（同datetime）
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public Date getDatetime() {
        return datetime;
    }


    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }


    public Date getTopTime() {
        return topTime;
    }


    public void setTopTime(Date topTime) {
        this.topTime = topTime;
    }


    public Integer getHits() {
        return hits;
    }


    public void setHits(Integer hits) {
        this.hits = hits;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }
}