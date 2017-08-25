package com.onway.web.controller;

import com.onway.web.controller.base.BaseAction;
import com.onway.web.dao.UserDao;
import com.onway.web.module.request.GetHtmlInfoRequest;
import com.onway.web.module.request.GetSignatureRequest;
import com.onway.web.module.response.Response;
import com.onway.web.pojo.UserPathPojo;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by win7 on 2017/8/1.
 */
@RestController
public class GetUrlController extends BaseAction{
    //图片保存路径
    //private static final String saveImgPath="E://imgs";
    //private static final String htmlPath="192.168.0.130:8090/";
//    private static final String relativelyPath=System.getProperty("user.dir")+"/src/main/resources/static/";
    private static final String relativelyPath="/usr/local/apache-tomcat-8.5.20/webapps/";
    private static final String UrlPath="http://weixin.puyuekeji.com/";
    private Logger log = LoggerFactory.getLogger(this.getClass());
    //private static final String timestamp=String.valueOf(System.currentTimeMillis());
    //private static final String openId="1";
    private static final String AppId="wxa16425d3780729a5";
    private static final String AppSecret="0c6e4d07003bbe0afa850656980979bd";
    @Autowired
    private UserDao userDao;

//    @RequestMapping("/test")
//    public String test(HttpServletRequest request){
//        //String path=request.getSession().getServletContext().getRealPath("");
//        String path=request.getSession().getServletContext().getRealPath("/");
//
//        System.out.println(path);
//        return path;
//    }

    /**
     * 获取文章的URL并生成原网页和广告页
     * @param url
     * @param openId
     * @return
     */
    @RequestMapping("/getUrl.do")
    public String getUrl(@RequestParam(value = "url") String url, @RequestParam(value = "openId") String openId, HttpServletRequest request){
        String timestamp=String.valueOf(System.currentTimeMillis());
        UserPathPojo userPathPojo=userDao.selectById(openId);
        HttpSession session = request.getSession();
        session.setAttribute("oldUrl",url);
        //String url="\"http://mp.weixin.qq.com/s/4k6TOX9EFmhsuiSnQMHmkA\"";
        //String url="http://mp.weixin.qq.com/s/yhKh-5pLKXDxE4rKkVE4cQ";
        // 利用Jsoup获得连接
        String oldTitle="";
        Connection connect = Jsoup.connect(url);
        String name="old"+timestamp+".html";

        try {
            // 得到Document对象
            Document document = connect.referrer("never").get();
            document.select("meta").last().after("<meta name='referrer' content='never'>");
            System.out.println(document);

            // 查找所有img标签
            Elements imgs = document.getElementsByTag("img");
            //修改作者
            if(userPathPojo.getUserAuthor().length()!=0){
                Element authors=document.getElementById("post-user");
                authors.text(userPathPojo.getUserAuthor());
            }
            //修改时间
            String time= String.valueOf(userPathPojo.getUserDate());
            System.out.println(time);
            String formatStr="";
            if(time!="null"){
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                //java.util.Date对象
                java.util.Date date = (java.util.Date) sdf.parse(time);
                //2009-09-16
                formatStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                System.out.println(formatStr);
            }
            if(formatStr!=""){
                Element time1=document.getElementById("post-date");
                time1.text(formatStr);
            }
            Element title1=document.getElementById("activity-name");
            oldTitle= title1.text();
            //修改标题
            if(userPathPojo.getUserTitle().length()!=0){
                title1=document.getElementById("activity-name");
                title1.text(userPathPojo.getUserTitle());
                oldTitle=userPathPojo.getUserTitle();
            }

            System.out.println("共检测到下列图片URL：");
            System.out.println("开始下载");
            // 遍历img标签并获得src的属性
            for (Element element : imgs) {
                //获取每个img标签URL "abs:"表示绝对路径
                String imgSrc = element.attr("abs:data-src");
                element.attr("src","http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=" + imgSrc);
                element.removeAttr("data-src");
            }
            Elements iframe = document.getElementsByTag("iframe");
            for (Element element : iframe) {
                //获取每个iframe标签URL "abs:"表示绝对路径
                String imgSrc = element.attr("abs:data-src");
                element.attr("src", imgSrc);
                element.attr("style","display:block");
                //element.removeAttr("data-src");
                element.attr("id","video_iframe");
            }
            LocalDate day = LocalDate.now();
            File dir = new File("html/"+String.valueOf(day));
            String dirPath=dir.getPath();
            String html = String.valueOf(document);
            //生成原网页
            File file = new File(relativelyPath+dirPath+"/"+name);
            FileUtils.writeByteArrayToFile(file,html.getBytes());

            //修改模板的src地址
            File in = new File(relativelyPath+"template.html");
            Document doc = Jsoup.parse(in, "UTF-8", "");
            Elements elements=doc.getElementsByTag("iframe");
            for(Element element:elements){
                element.attr("src",name);
            }
            //修改底部文案连接
            Element title2=doc.getElementById("title");
            title2.text(oldTitle);
            //修改电话
            Element newTel=doc.getElementById("phone-link");
            newTel.attr("href","tel:"+userPathPojo.getCell());
            //修改底部文案连接
            if(userPathPojo.getUserUrl()!="http://"){
                Element newBottomLink=doc.getElementById("adLink");
                newBottomLink.attr("href",userPathPojo.getUserUrl());
            }
            //定义广告页的名字
            String fileName=timestamp+".html";
            //生成二维码
            //File quickResponseDir = new File(relativelyPath+"images/QuickMark/"+String.valueOf(day));
//            if(!quickResponseDir.exists()){
//                quickResponseDir.mkdir();
//            }
            //二维码的日期目录路径
            //String QuickResponseDirPath=quickResponseDir.getPath();
            //String QuickResponse=QuickResponseDirPath+"/img"+timestamp+".jpg";
            //生成二维码(扫描二维码跳转的路径/二维码宽度/二维码高度/二维码嵌入的图片位置/二维码生成路径)
            //GetUrl.encode(htmlPath+"html/"+day+"/"+fileName, 480, 480, relativelyPath+"images/img.jpg",QuickResponse);
            //修改二维码
                Element pubNum=doc.getElementById("pub-num");
                pubNum.attr("src",userPathPojo.getUserQrcode());
            //修改底部广告
            if(userPathPojo.getUserBottomAd().length()!=0){
                Element BottomAd=doc.getElementById("adImg");
                BottomAd.attr("src",userPathPojo.getUserBottomAd());
                Element BottomText =doc.getElementById("adTxt");
                BottomText.remove();
            }else if(userPathPojo.getUserBottomText().length()!=0){
                Element BottomAd=doc.getElementById("adImg");
                BottomAd.remove();
                Element BottomText =doc.getElementById("adTxt");
                BottomText.text(userPathPojo.getUserBottomText());
            }else{
                Element BottomText =doc.getElementById("adTxt");
                BottomText.remove();
                Element BottomAd=doc.getElementById("adImg");
                BottomAd.remove();
            }
            //修改全屏广告
            if(userPathPojo.getUserFullAd().length()!=0) {
                Element flagAd = doc.getElementById("flagAd");
                flagAd.attr("value", "1");

            }
            Element FullAd = doc.getElementById("fullImg");
            FullAd.attr("src", userPathPojo.getUserFullAd());
            //生成带广告的广告页面
            File newFile = new File(relativelyPath+dirPath+"/"+fileName);
            FileUtils.writeByteArrayToFile(newFile,String.valueOf(doc).getBytes());
//            Runtime.getRuntime().exec("chmod -R 755 " + relativelyPath);
            String returnPath=UrlPath+dirPath+"/"+fileName+"?openId="+openId;
//            String returnPath=dirPath+"/"+fileName;
            //url
            //String jsUrl="http://vav534.natappfree.cc"+dirPath+"/"+fileName;
            String jsUrl=returnPath;
            //1、获取access_token
            String access_token= (String) session.getAttribute("access_token");
            if(session.getAttribute("access_token")==null){
                access_token=getAccessToken();
                session.setAttribute("access_token",access_token);
            }
            //2、获取Ticket
            String jsapi_ticket=getTicket(access_token);
            System.out.println("ticket:"+jsapi_ticket);
            //3、时间戳和随机字符串
            String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
            timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
            //4、将参数排序并拼接字符串
            String str = "jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+jsUrl;
            //5、将字符串进行sha1加密
            String signature =SHA1(str);
            System.out.println("参数："+str+"\n签名："+signature);
            log.info("get请求结果openid:" + openId);
            userDao.updateSignature(openId,signature,timestamp,noncestr);

            return returnPath;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String  getAccessToken(){
        String access_token = "";
        String aturl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + AppId
                + "&secret=" + AppSecret;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(aturl);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                // System.out.println("get请求结果:" + strResult);
                JSONObject jsonResult = new JSONObject(strResult);
                System.out.println(jsonResult);
                access_token = (String) jsonResult.get("access_token");
                System.out.println("get请求结果   access_token  :   " + access_token);
            }
        } catch (IOException e) {
            //System.out.println("get请求提交失败:" + access_token_url + e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return access_token;
    }

    public static String getTicket(String access_token) {
        String ticket = null;
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ access_token +"&type=jsapi";//这个url链接和参数不能变
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                JSONObject jsonResult = new JSONObject(strResult);
                ticket = (String) jsonResult.get("ticket");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    @RequestMapping("/encryption.do")
    public Response encryption(HttpServletRequest request,GetSignatureRequest getSignatureRequest,
                               @RequestParam(value ="timestamp") String timestamp,
                               @RequestParam(value ="noncestr") String noncestr,
                               @RequestParam(value ="jsUrl") String jsUrl){

        Response response=new Response(getSignatureRequest);
        //String openId=request.getParameter("openId");
        HttpSession session = request.getSession();
        //String openId= (String) session.getAttribute("openId");
        //1、获取access_token
        String access_token= (String) session.getAttribute("access_token");
        if(session.getAttribute("access_token")==null){
            access_token=getAccessToken();
            session.setAttribute("access_token",access_token);
        }
        //2、获取Ticket
        String jsapi_ticket=getTicket(access_token);
        System.out.println("ticket:"+jsapi_ticket);
        //4、将参数排序并拼接字符串
        String str = "jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+jsUrl;
        //5、将字符串进行sha1加密
        String signature =SHA1(str);
        getSignatureRequest.setSignature(signature);
        response.setData(getSignatureRequest);
        return response;
    }
    @RequestMapping("/getHtmlInfo.do")
    public Response getHtmlInfo(HttpServletRequest request, @RequestParam(value = "url") String url
                                , GetHtmlInfoRequest getHtmlInfoRequest){
        Response response=new Response(getHtmlInfoRequest);
        HttpSession session=request.getSession();
        String openId= (String) session.getAttribute("openId");
        Connection connect = Jsoup.connect(url);
        java.util.Map map =new HashMap();
        //查找script
        try {
            Document  document = connect.referrer("never").get();
            document.select("meta").last().after("<meta name='referrer' content='never'>");
            Elements e = document.getElementsByTag("script").eq(10);
            for(Element element:e){
                System.out.println(e);
                String[] data = element.data().toString().split("var");
                for(String variable : data){
                /*过滤variable为空的数据*/
                    if(variable.contains("=")){
                    /*取到满足条件的JS变量*/
                        if(variable.contains("msg_desc") || variable.contains("msg_title")
                                || variable.contains("msg_cdn_url")){

                            String[]  kvp = variable.split("=");

                        /*取得JS变量存入map*/
                            if(!map.containsKey(kvp[0].trim()))
                                map.put(kvp[0].trim(), kvp[1].trim().substring(0, kvp[1].trim().length()-1).toString());
                        }
                    }
                }
            }
            String msgDesc=(String)map.get("msg_desc");
            String msgTitle=(String)map.get("msg_title");
            String msgCdnUrl=(String)map.get("msg_cdn_url");

            String desc=msgDesc.substring(1,msgDesc.length()-1);
            String title=msgTitle.substring(1,msgTitle.length()-1);
            String cndUrl=msgCdnUrl.substring(1,msgCdnUrl.length());
            getHtmlInfoRequest.setMsgCdnUrl(cndUrl);
            getHtmlInfoRequest.setMsgTitle(msgTitle);
            getHtmlInfoRequest.setMsgDesc(msgDesc);
            userDao.updateInfo(openId,desc,title,cndUrl);
            response.setData(getHtmlInfoRequest);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  response;
    }
}
