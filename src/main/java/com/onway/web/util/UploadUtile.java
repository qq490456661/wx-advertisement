package com.onway.web.util;

import com.onway.web.dao.UserDao;
import com.onway.web.module.request.SelectByIdRequest;
import com.onway.web.module.response.Response;
import com.onway.web.pojo.UserPathPojo;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by win7 on 2017/8/11.
 */
@Controller
public class UploadUtile {
//    private static final String relativelyPath=System.getProperty("user.dir")+"/src/main/resources/static/";
    private static final String relativelyPath="/usr/local/apache-tomcat-8.5.20/webapps/";
    private static final String images="http://weixin.puyuekeji.com/images/";
    private static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    private static BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    //private String openId="1";
    @Autowired
    private UserDao userDao;
//    @RequestMapping("/upload.do")
//    @ResponseBody
//    public void uploadImg(@RequestParam(value ="preview1"  ,required = false) MultipartFile preview1,
//                              @RequestParam(value ="preview2"  ,required = false) MultipartFile preview2,
//                              @RequestParam(value ="preview3"  ,required = false) MultipartFile preview3,
//                              @RequestParam(value ="bottomText" ,required = false) String bottomText,
//                              @RequestParam(value ="links" ,required = false) String links,
//                              @RequestParam(value ="author" ,required = false) String author,
//                              @RequestParam(value ="tel" ,required = false) String tel,
//                              @RequestParam(value ="title" ,required = false) String title,
//                              @RequestParam(value ="time" ,required = false) String time,
//                              SelectByIdRequest request,
//                              @RequestParam(value ="openId" ,required = false) String openId
//                          ) throws ParseException {
//        String timestamp=String.valueOf(System.currentTimeMillis());
//        Response response=new Response(request);
//        MultipartFile[] files={preview1,preview2,preview3};
//        List<String> list = new ArrayList<String>();
//        if (files != null && files.length > 0) {
//            for (int i = 0; i < files.length; i++) {
//                MultipartFile file = files[i];
//                // 保存文件
//                if(i==0&files[i]!=null){
//                    list = saveFile(file, list,"0",bottomText,links,author,tel,title,time,timestamp,openId);
//                }else if(i==1&files[i]!=null){
//                    list = saveFile(file, list,"1",bottomText,links,author,tel,title,time,timestamp,openId);
//                }else if(i==2&files[i]!=null){
//                    list = saveFile(file, list,"2",bottomText,links,author,tel,title,time,timestamp,openId);
//                }else if(files[i]==null){
//                }
//
//            }
//        }
//        java.util.Date date=null;
//        if (time.length()!=0){
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            date = format.parse(time);
//        }
//        UserPathPojo userPath;
//        userPath=userDao.selectById(openId);
//        if(userPath==null){
//            userDao.insert(openId,userPath.getUserPath(),links,title,author,date,userPath.getUserQrcode(),tel,"","",bottomText);
//        }else{
//            userDao.update(openId,userPath.getUserPath(),links,title, author,date,userPath.getUserQrcode(),tel
//                    ,userPath.getUserFullAd(),userPath.getUserBottomAd(),bottomText);
//        }
//        //写着测试，删了就可以
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("集合里面的数据" + list.get(i));
//        }
//        LocalDate day=LocalDate.now();
//        Map map =new HashMap();
//        if(preview1==null){
//            map.put("status1","0");
//        }else{
//            map.put("status1","1");
//        }
//        if(preview2==null){
//            map.put("status2","0");
//        }else{
//            map.put("status2","1");
//        }
//        if(preview3==null){
//            map.put("status3","0");
//        }else{
//            map.put("status3","1");
//        }
//        map.put("bottomText",bottomText);
//        map.put("links",links);
//        map.put("author",author);
//        map.put("tel",tel);
//        map.put("title",title);
//        map.put("time",time);
//        map.put("timestamp",timestamp);
//        response.setData(map);
//    }
//    private List<String> saveFile(MultipartFile file, List<String> list,
//                                  String num,String bottomText,String links,
//                                  String author,String tel, String title,String time,
//                                  String timestamp,String openId) {
//
//
//        UserPathPojo userPath;
//        // 判断文件是否为空
//        if (!file.isEmpty()) {
//            try {
//                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
//                // )
//                String filePath ="";
//                LocalDate day=LocalDate.now();
//                String fileName="";
//                java.util.Date date=null;
//                if (time.length()!=0){
//                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                    date = format.parse(time);
//                }
//                //判断底部广告图片,没有的话先清空数据库
//                if(num=="0"){
//                    fileName="QuickMark"+timestamp+".jpg";
//                    filePath=relativelyPath+"images/QuickMark/"+String.valueOf(day)+"/"+fileName;
//                    String path=images+"QuickMark/"+String.valueOf(day)+"/"+fileName;
////                    String path="../../images/QuickMark/"+String.valueOf(day)+"/"+fileName;
//                    userPath=userDao.selectById(openId);
//                    if(userPath==null){
//                        userDao.insert(openId,"",links,title,author,date,path,tel,"","",bottomText);
//                    }else{
//                        userDao.update(openId,userPath.getUserPath(),links,title, author,date,path,tel
//                                ,userPath.getUserFullAd(),userPath.getUserBottomAd(),bottomText);}
//                }else if(num=="1"){
//                    fileName="BottomAd"+timestamp+".jpg";
//                    filePath=relativelyPath+"images/BottomAd/"+String.valueOf(day)+"/"+fileName;
//                    String path=images+"BottomAd/"+String.valueOf(day)+"/"+fileName;
////                    String path="../../images/BottomAd/"+String.valueOf(day)+"/"+fileName;
//                    userPath=userDao.selectById(openId);
//                    if(userPath==null){
//                        userDao.insert(openId,"",links,title,author,date,"",tel,"",path,bottomText);
//                    }else{
//                        userDao.update(openId,userPath.getUserPath(),links,title,
//                                author,date,userPath.getUserQrcode(),tel
//                                ,userPath.getUserFullAd(),path,bottomText);
//                    }
//                }else if(num=="2"){
//                    fileName="FullAd"+timestamp+".jpg";
//                    filePath=relativelyPath+"images/FullAd/"+String.valueOf(day)+"/"+fileName;
//                    String path=images+"FullAd/"+String.valueOf(day)+"/"+fileName;
////                    String path="../../images/FullAd/"+String.valueOf(day)+"/"+fileName;
//                    userPath=userDao.selectById(openId);
//                    if(userPath==null){
//                        userDao.insert(openId,"",links,title,author,date,"",tel,path,"",bottomText);
//                    }else{
//                        userDao.update(openId,userPath.getUserPath(),links,title,
//                                author,date,userPath.getUserQrcode(),tel
//                                ,path,userPath.getUserBottomAd(),bottomText);}
//                }
//                Runtime.getRuntime().exec("chmod -R 755 " + relativelyPath);
//                list.add(file.getOriginalFilename());
//                File saveDir = new File(filePath);
//                if (!saveDir.getParentFile().exists()) {
//                    saveDir.getParentFile().mkdirs();
//                }
//                // 转存文件
//                file.transferTo(saveDir);
//                return list;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }

    @RequestMapping("/uploadImage.do")
    @ResponseBody
    public void uploadImage(@RequestParam(value ="image1Path" ,required = false) String image1Path,
                            @RequestParam(value ="image2Path" ,required = false) String image2Path,
                            @RequestParam(value ="image3Path" ,required = false) String image3Path,
                            @RequestParam(value ="bottomText" ,required = false) String bottomText,
                            @RequestParam(value ="links" ,required = false) String links,
                            @RequestParam(value ="author" ,required = false) String author,
                            @RequestParam(value ="tel" ,required = false) String tel,
                            @RequestParam(value ="title" ,required = false) String title,
                            @RequestParam(value ="time" ,required = false) String time,
                            @RequestParam(value ="openId" ,required = false) String openId){

        if(image1Path.length()<204800&&image2Path.length()<204800&&image3Path.length()<204800){
            String timestamp=String.valueOf(System.currentTimeMillis());
            java.util.Date date=null;
            if (time.length()!=0){
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    date = format.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            UserPathPojo userPath;
            userPath=userDao.selectById(openId);
            if(userPath==null){
                userDao.insert(openId,userPath.getUserPath(),links,title,author,date,userPath.getUserQrcode(),tel,"","",bottomText);
            }else{
                userDao.update(openId,userPath.getUserPath(),links,title, author,date,userPath.getUserQrcode(),tel
                        ,userPath.getUserFullAd(),userPath.getUserBottomAd(),bottomText);
            }
            if(image1Path.length()!=0&&image1Path!=null){
                base64StringToImage(image1Path,"1",bottomText,links,author,tel,title,time,timestamp,openId);
            }
            if(image2Path.length()!=0&&image2Path!=null){
                base64StringToImage(image2Path,"2",bottomText,links,author,tel,title,time,timestamp,openId);
            }
            if(image3Path.length()!=0&&image3Path!=null){
                base64StringToImage(image3Path,"3",bottomText,links,author,tel,title,time,timestamp,openId);
            }
        }else{

        }

    }
    public void base64StringToImage(String base64String,String num,String bottomText,String links,
                                    String author,String tel, String title,String time,
                                    String timestamp,String openId) {
        try {
            String filePath ="";
            LocalDate day=LocalDate.now();
            String fileName="";
            java.util.Date date=null;
            UserPathPojo userPath;
            if (time.length()!=0){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                date = format.parse(time);
            }
            if(num=="1"){
                fileName="QuickMark"+timestamp+".jpg";
                filePath=relativelyPath+"images/QuickMark/"+String.valueOf(day)+"/"+fileName;
                String path=images+"QuickMark/"+String.valueOf(day)+"/"+fileName;
//                    String path="../../images/FullAd/"+String.valueOf(day)+"/"+fileName;
                userPath=userDao.selectById(openId);
                if(userPath==null){
                        userDao.insert(openId,"",links,title,author,date,path,tel,"","",bottomText);
                    }else{
                        userDao.update(openId,userPath.getUserPath(),links,title, author,date,path,tel
                                ,userPath.getUserFullAd(),userPath.getUserBottomAd(),bottomText);
                }
            }else if(num=="2"){
                fileName="BottomAd"+timestamp+".jpg";
                filePath=relativelyPath+"images/BottomAd/"+String.valueOf(day)+"/"+fileName;
                String path=images+"BottomAd/"+String.valueOf(day)+"/"+fileName;
//                    String path="../../images/FullAd/"+String.valueOf(day)+"/"+fileName;
                userPath=userDao.selectById(openId);
                if(userPath==null){
                    userDao.insert(openId,"",links,title,author,date,"",tel,"",path,bottomText);
                }else{
                    userDao.update(openId,userPath.getUserPath(),links,title,
                    author,date,userPath.getUserQrcode(),tel
                    ,userPath.getUserFullAd(),path,bottomText);
                }
            }else if(num=="3"){
                fileName="FullAd"+timestamp+".jpg";
                filePath=relativelyPath+"images/FullAd/"+String.valueOf(day)+"/"+fileName;
                String path=images+"FullAd/"+String.valueOf(day)+"/"+fileName;
//                String path="../../images/FullAd/"+String.valueOf(day)+"/"+fileName;
                userPath=userDao.selectById(openId);
                if(userPath==null){
                   userDao.insert(openId,"",links,title,author,date,"",tel,path,"",bottomText);
                }else{
                   userDao.update(openId,userPath.getUserPath(),links,title,
                   author,date,userPath.getUserQrcode(),tel
                   ,path,userPath.getUserBottomAd(),bottomText);}
            }
            byte[] bytes1 = decoder.decodeBuffer(base64String);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File(filePath);// 可以是jpg,png,gif格式
            if (!w2.getParentFile().exists()) {
                w2.getParentFile().mkdirs();
            }
            ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动
//            Thumbnails.of(w2).scale(1f).outputQuality(0.5f).toFile(w2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
