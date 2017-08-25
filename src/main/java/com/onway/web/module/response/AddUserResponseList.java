package com.onway.web.module.response;

import com.onway.web.pojo.UserPojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
public class AddUserResponseList extends ResponseList<AddUserResponseList.UserView>{

    public void toResponse(List<UserPojo> userPojos){
        for(UserPojo a : userPojos){
            UserView userView = new UserView();
//            userView.setUserId(a.getUserId());
//            userView.setUserName(a.getUserName());
            getList().add(userView);
        }
    }

    public static class UserView{

        private int id;

        private String userName;

        private String userId;

        private String gmtCreate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }
    }


}
