package com.onway.web.dao;

import com.onway.web.pojo.User;
import com.onway.web.pojo.UserPath;
import com.onway.web.pojo.UserPojo;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    @Select("select * from cif_user where id = #{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "realName",column = "real_name"),
    })
    public User select(@Param("id")int id);

    @Insert("insert into ad_path(id,user_path,user_url,user_title,user_author,user_date,user_qrcode,ad_json,ad_cell)" +
            "value(#{id},#{userPath},#{userUrl},#{userTitle},#{userAuthor},#{userDate},#{userQrcode},#{adJson},#{adCell})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "userPath",column = "user_path"),
            @Result(property = "userUrl",column = "user_url"),
            @Result(property = "userTitle",column = "user_title"),
            @Result(property = "userAuthor",column = "user_author"),
            @Result(property = "userDate",column = "user_date"),
            @Result(property = "userQrcode",column = "user_qrcode"),
            @Result(property = "adJson",column = "ad_json"),
            @Result(property = "adCell",column = "ad_cell"),
    })
    public UserPath insert(@Param("id")int id, @Param("userPath")String userPath,
                           @Param("userUrl")String userUrl, @Param("userTitle")String userTitle,
                           @Param("userAuthor")String userAuthor, @Param("userDate")Date userDate,
                           @Param("userQrcode")String userQrcode, @Param("adJson")String adJson,
                           @Param("adCell")String adCell);
}
