package com.onway.web.pojo;

import java.util.Date;

/**
 * Created by win7 on 2017/8/14.
 */
public class UserPath {
    private int id;
    private String userPath;
    private String userUrl;
    private String userTitle;
    private String userAuthor;
    private Date userDate;
    private String userQrcode;
    private String adJson;
    private String cell;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserPath() {
        return userPath;
    }

    public void setUserPath(String userPath) {
        this.userPath = userPath;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public String getUserAuthor() {
        return userAuthor;
    }

    public void setUserAuthor(String userAuthor) {
        this.userAuthor = userAuthor;
    }

    public Date getUserDate() {
        return userDate;
    }

    public void setUserDate(Date userDate) {
        this.userDate = userDate;
    }

    public String getUserQrcode() {
        return userQrcode;
    }

    public void setUserQrcode(String userQrcode) {
        this.userQrcode = userQrcode;
    }

    public String getAdJson() {
        return adJson;
    }

    public void setAdJson(String adJson) {
        this.adJson = adJson;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
}