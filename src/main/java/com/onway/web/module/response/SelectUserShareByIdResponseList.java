package com.onway.web.module.response;
import com.onway.web.pojo.UserSharePojo;

import java.util.Date;
import java.util.List;

/**
 * Created by win7 on 2017/8/29.
 */
public class SelectUserShareByIdResponseList extends ResponseList<SelectUserShareByIdResponseList.UserShareView>{
    public void toResponse(List<UserSharePojo> userSharePojoList){
        for(UserSharePojo userSharePojo:userSharePojoList){
            UserShareView userShareView=new UserShareView();
            userShareView.setId(userSharePojo.getId());
            userShareView.setOpenId(userSharePojo.getOpenId());
            userShareView.setTitle(userSharePojo.getTitle());
            userShareView.setDate(userSharePojo.getDate());
            userShareView.setUrl(userSharePojo.getUrl());
            getList().add(userShareView);
        }
    }
    public static class UserShareView{
        private int id;
        private String openId;
        private String title;
        private Date date;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    
}
