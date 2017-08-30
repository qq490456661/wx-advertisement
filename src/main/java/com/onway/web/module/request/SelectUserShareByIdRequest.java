package com.onway.web.module.request;
import java.util.Date;
/**
 * Created by win7 on 2017/8/29.
 */
public class SelectUserShareByIdRequest extends Request{
    private int Id;
    private String openId;
    private String title;
    private Date date;
    private String url;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
