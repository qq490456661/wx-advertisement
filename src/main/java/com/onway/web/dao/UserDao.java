package com.onway.web.dao;

import com.onway.web.pojo.User;
import com.onway.web.pojo.UserPathPojo;
import com.onway.web.pojo.UserPojo;
import com.onway.web.pojo.UserSharePojo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/6 0006.
 */
@Mapper
public interface UserDao{


    @Select("select * from cif_user where open_id = #{openId}")
    @Results({
            @Result(property = "openId",column = "open_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "realName",column = "real_name"),
    })
    public User select(@Param("openId")int openId);

    @Insert("insert into ad_path(OPEN_ID,USER_PATH,USER_URL,USER_TITLE,USER_AUTHOR,USER_DATE,USER_QRCODE,CELL,USER_FULL_AD,USER_BOTTOM_AD," +
            "USER_BOTTOM_TEXT) value(#{openId},#{userPath},#{userUrl},#{userTitle},#{userAuthor},#{userDate},#{userQrcode},#{cell},#{userFullAd}," +
            "#{userBottomAd},#{userBottomText})")
    @Results({
            @Result(property = "openId",column = "open_id"),
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
    public void insert(@Param("openId")String openId,@Param("userPath")String userPath,
                               @Param("userUrl")String userUrl, @Param("userTitle")String userTitle,
                               @Param("userAuthor")String userAuthor, @Param("userDate")Date userDate,
                               @Param("userQrcode")String userQrcode, @Param("cell")String cell,
                               @Param("userFullAd")String userFullAd, @Param("userBottomAd")String userBottomAd,
                               @Param("userBottomText")String userBottomText);

    @Update("update ad_path set user_path=#{userPath},user_url=#{userUrl},user_title=#{userTitle}," +
            "user_author=#{userAuthor},user_date=#{userDate},user_qrcode=#{userQrcode},cell=#{cell}," +
            "user_full_ad=#{userFullAd},user_bottom_ad=#{userBottomAd},user_bottom_text=#{userBottomText} where open_id=#{openId}")
    @Results({
            @Result(property = "openId", column = "open_id"),
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
    public void update(@Param("openId")String openId, @Param("userPath")String userPath,
                               @Param("userUrl")String userUrl, @Param("userTitle")String userTitle,
                               @Param("userAuthor")String userAuthor, @Param("userDate")Date userDate,
                               @Param("userQrcode")String userQrcode, @Param("cell")String cell,
                               @Param("userFullAd")String userFullAd, @Param("userBottomAd")String userBottomAd,
                               @Param("userBottomText")String userBottomText);

    @Update("update ad_path set signature=#{signature},timestamp=#{timestamp},nonceStr=#{nonceStr} where open_id=#{openId}")
    @Results({
            @Result(property = "openId", column = "open_id"),
            @Result(property = "signature", column = "signature"),
            @Result(property = "timestamp", column = "timestamp"),
            @Result(property = "nonceStr", column = "nonceStr"),
    })
    public void updateSignature(@Param("openId")String openId, @Param("signature")String signature,
                                @Param("timestamp")String timestamp, @Param("nonceStr")String nonceStr);
    @Update("update ad_path set MSG_DESC=#{msgDesc},MSG_TITLE=#{msgTitle},MSG_CDN_URL=#{msgCdnUrl} where open_id=#{openId}")
    @Results({
            @Result(property = "openId", column = "open_id"),
            @Result(property = "msgDesc", column = "MSG_DESC"),
            @Result(property = "msgTitle", column = "MSG_TITLE"),
            @Result(property = "msgCdnUrl", column = "MSG_CDN_URL"),
    })
    public void updateInfo(@Param("openId")String openId, @Param("msgDesc")String msgDesc,
                                @Param("msgTitle")String msgTitle, @Param("msgCdnUrl")String msgCdnUrl);

    @Select("select * from ad_path where open_id = #{openId}")
    @Results({
            @Result(property = "openId", column = "open_id"),
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
            @Result(property = "timestamp",column = "timestamp"),
            @Result(property = "signature",column = "signature"),
            @Result(property = "noncestr",column = "noncestr"),
            @Result(property = "msgDesc",column = "msg_desc"),
            @Result(property = "msgTitle",column = "msg_title"),
            @Result(property = "msgCdnUrl",column = "msg_cdn_url"),
    })
    public UserPathPojo selectById(@Param("openId") String openId);

    @Select("select * from cif_user where open_id=#{openId}")
    @Results({
            @Result(property = "openId",column="open_id")
    })
    public UserPojo selectUserById(@Param("openId") String openId);

    @Insert("insert into cif_user(open_id,nickname,sex,city,country,province,headimgurl," +
            "user_level,status,accoess_token,gmt_starttime,gmt_endtime) value(#{openId},#{nickName}," +
            "#{sex},#{city},#{country},#{province},#{headimgUrl},#{userLevel},#{status},#{accoessToken}," +
            "#{gmtStartTime},#{gmtEndTime})")
    @Results({
            @Result(property = "openId", column = "open_id"),
            @Result(property = "nickName",column = "nickname"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "city",column = "city"),
            @Result(property = "country",column = "country"),
            @Result(property = "province",column = "province"),
            @Result(property = "headimgUrl",column = "headimgUrl"),
            @Result(property = "userLevel",column = "user_level"),
            @Result(property = "status",column = "status"),
            @Result(property = "accoessToken",column = "accoess_token"),
            @Result(property = "gmtStartTime",column = "gmt_StartTime"),
            @Result(property = "gmtEndTime",column = "GMT_ENDTIME"),
    })
    public void insertUser(@Param("openId") String openId,@Param("nickName") String nickName,
                           @Param("sex") int sex,@Param("city") String city,@Param("country") String country,
                           @Param("province") String province, @Param("headimgUrl") String headimgUrl,
                           @Param("userLevel") String userLevel, @Param("status") String status,
                           @Param("accoessToken") String accoessToken,
                           @Param("gmtStartTime") Date gmtStartTime,@Param("gmtEndTime") Date gmtEndTime
    );

    @Update("update cif_user set nickname=#{nickname},sex=#{sex},city=#{city}," +
            "country=#{country},province=#{province},headimgUrl=#{headimgUrl}," +
            "userLevel=#{userLevel},status=#{status},accoessToken=#{accoessToken}," +
            "gmtStartTime=#{gmtStartTime},gmtEndTime=#{gmtEndTime} where open_id=#{openId}")
    @Results({
            @Result(property = "openId", column = "open_id"),
            @Result(property = "nickName",column = "nickname"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "city",column = "city"),
            @Result(property = "country",column = "country"),
            @Result(property = "province",column = "province"),
            @Result(property = "headimgUrl",column = "headimgUrl"),
            @Result(property = "userLevel",column = "user_level"),
            @Result(property = "status",column = "status"),
            @Result(property = "accoessToken",column = "accoess_token"),
            @Result(property = "gmtStartTime",column = "gmt_StartTime"),
            @Result(property = "gmtEndTime",column = "GMT_ENDTIME"),
    })
    public void updateUser(@Param("openId") String openId,@Param("nickName") String nickName,
                           @Param("sex") int sex,@Param("city") String city,@Param("country") String country,
                           @Param("province") String province, @Param("headimgUrl") String headimgUrl,
                           @Param("userLevel") String userLevel, @Param("status") String status,
                           @Param("accoessToken") String accoessToken,
                           @Param("gmtStartTime") Date gmtStartTime,@Param("gmtEndTime") Date gmtEndTime);

    @Insert("insert into cif_share(open_id,title,date,url,ty) value(#{openId},#{title},#{date},#{url})")
    public void insertUserShare(@Param("openId") String openId,@Param("title") String title,
                                @Param("date") Date date,@Param("url") String url);

    @Select("select * from cif_share where open_id=#{openId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "openId", column = "open_id"),
            @Result(property = "title",column = "title"),
            @Result(property = "date",column = "date"),
            @Result(property = "url",column = "url")
    })
    public List<UserSharePojo> selectUserShareByOpenId(@Param("openId") String openId);

    @Delete("delete from cif_share where id=#{id}")
    public void deleteUserShareById(@Param("id") int id);
}
