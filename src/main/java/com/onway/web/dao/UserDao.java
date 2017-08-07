package com.onway.web.dao;

import com.onway.web.pojo.UserPojo;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/8/6 0006.
 */
@Mapper
public interface UserDao{

    @Select("select * from sys_user where user_name = #{userName}")
    @Results({
        @Result(property = "userName",column = "user_name"),
        @Result(property = "userId",column = "user_id"),
        @Result(id=true,property = "id",column = "id"),
    })
    public UserPojo get(@Param("userName")String userName);

}
