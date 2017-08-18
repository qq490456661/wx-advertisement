package com.onway.web.dao;

import com.onway.web.pojo.User;
import com.onway.web.pojo.UserPathPojo;
import com.onway.web.pojo.UserPojo;
import org.apache.ibatis.annotations.*;

import java.util.Date;

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

    @Insert("insert into ad_path(USER_PATH,USER_URL,USER_TITLE,USER_AUTHOR,USER_DATE,USER_QRCODE,CELL,USER_FULL_AD,USER_BOTTOM_AD," +
            "USER_BOTTOM_TEXT) value(#{userPath},#{userUrl},#{userTitle},#{userAuthor},#{userDate},#{userQrcode},#{cell},#{userFullAd}," +
            "#{userBottomAd},#{userBottomText})")
    @Results({
            @Result(property = "userPath",column = "user_path"),
            @Result(property = "userUrl",column = "user_url"),
            @Result(property = "userTitle",column = "user_title"),
            @Result(property = "userAuthor",column = "user_author"),
            @Result(property = "userDate",column = "user_date"),
            @Result(property = "userQrcode",column = "user_qrcode"),
            @Result(property = "cell",column = "cell"),
            @Result(property = "userFullAd",column = "user_full_ad"),
            @Result(property = "userBottomAd",column = "user_bottom_ad"),
            @Result(property = "userBottomText",column = "user_bottom_text"),
    })
    public void insert(@Param("userPath")String userPath,
                               @Param("userUrl")String userUrl, @Param("userTitle")String userTitle,
                               @Param("userAuthor")String userAuthor, @Param("userDate")Date userDate,
                               @Param("userQrcode")String userQrcode, @Param("cell")String cell,
                               @Param("userFullAd")String userFullAd, @Param("userBottomAd")String userBottomAd,
                               @Param("userBottomText")String userBottomText);

    @Update("update ad_path set user_path=#{userPath},user_url=#{userUrl},user_title=#{userTitle}," +
            "user_author=#{userAuthor},user_date=#{userDate},user_qrcode=#{userQrcode},cell=#{cell}," +
            "user_full_ad=#{userFullAd},user_bottom_ad=#{userBottomAd},user_bottom_text=#{userBottomText} where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userPath",column = "user_path"),
            @Result(property = "userUrl",column = "user_url"),
            @Result(property = "userTitle",column = "user_title"),
            @Result(property = "userAuthor",column = "user_author"),
            @Result(property = "userDate",column = "user_date"),
            @Result(property = "userQrcode",column = "user_qrcode"),
            @Result(property = "cell",column = "cell"),
            @Result(property = "userFullAd",column = "user_full_ad"),
            @Result(property = "userBottomAd",column = "user_bottom_ad"),
            @Result(property = "userBottomText",column = "user_bottom_text"),
    })
    public void update(@Param("id")int id, @Param("userPath")String userPath,
                               @Param("userUrl")String userUrl, @Param("userTitle")String userTitle,
                               @Param("userAuthor")String userAuthor, @Param("userDate")Date userDate,
                               @Param("userQrcode")String userQrcode, @Param("cell")String cell,
                               @Param("userFullAd")String userFullAd, @Param("userBottomAd")String userBottomAd,
                               @Param("userBottomText")String userBottomText);

    @Select("select * from ad_path where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userPath",column = "user_path"),
            @Result(property = "userUrl",column = "user_url"),
            @Result(property = "userTitle",column = "user_title"),
            @Result(property = "userAuthor",column = "user_author"),
            @Result(property = "userDate",column = "user_date"),
            @Result(property = "userQrcode",column = "user_qrcode"),
            @Result(property = "cell",column = "cell"),
            @Result(property = "userFullAd",column = "user_full_ad"),
            @Result(property = "userBottomAd",column = "user_bottom_ad"),
            @Result(property = "userBottomText",column = "user_bottom_text"),
    })
    public UserPathPojo selectById(@Param("id")int id);
}
