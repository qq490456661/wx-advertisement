package com.onway.web.util;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
//    private static final String relativelyPath=System.getProperty("user.dir")+"/src/";
    private static final String relativelyPath="/usr/local/wx_resource/";
    private static final String timestamp=String.valueOf(System.currentTimeMillis());
    @RequestMapping("/upload.do")
    @ResponseBody
    public Map uploadImg(@RequestParam(value ="preview1"  ,required = false) MultipartFile preview1,
                          @RequestParam(value ="preview2"  ,required = false) MultipartFile preview2,
                          @RequestParam(value ="preview3"  ,required = false) MultipartFile preview3,
                          @RequestParam(value ="bottomText" ,required = false) String bottomText,
                          @RequestParam(value ="links" ,required = false) String links,
                          @RequestParam(value ="author" ,required = false) String author,
                          @RequestParam(value ="tel" ,required = false) String tel,
                          @RequestParam(value ="title" ,required = false) String title,
                          @RequestParam(value ="time" ,required = false) String time,
                          HttpServletRequest request
                          )
    {
        //String timestamp= String.valueOf(System.currentTimeMillis());

        MultipartFile[] files={preview1,preview2,preview3};
        List<String> list = new ArrayList<String>();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                if(i==0&files[i]!=null){
                    list = saveFile(request, file, list,"0");
                }else if(i==1&files[i]!=null){
                    list = saveFile(request, file, list,"1");
                }else if(i==2&files[i]!=null){
                    list = saveFile(request, file, list,"2");
                }
            }
        }
        //写着测试，删了就可以
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
        }
        LocalDate day=LocalDate.now();
        Map map =new HashMap();
        if(preview2==null){
            map.put("status","0");
        }else if(preview2!=null){
            map.put("status","1");
        }
        map.put("bottomText",bottomText);
        map.put("links",links);
        map.put("author",author);
        map.put("tel",tel);
        map.put("title",title);
        map.put("time",time);
        map.put("timestamp",timestamp);
        return map;
    }
    private List<String> saveFile(HttpServletRequest request,
                                  MultipartFile file, List<String> list,String num) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                // )
                String filePath ="";
                LocalDate day=LocalDate.now();

                String fileName="";
                if(num=="0"){
                    fileName="QuickMark"+timestamp+".jpg";
                    filePath=relativelyPath+"images/QuickMark/"+String.valueOf(day)+"/"+fileName;

                }else if(num=="1"){
                    fileName="FullAd"+timestamp+".jpg";
                    filePath=relativelyPath+"images/FullAd/"+String.valueOf(day)+"/"+fileName;
                }else if(num=="2"){
                    fileName="BottomAd"+timestamp+".jpg";
                    filePath=relativelyPath+"images/BottomAd/"+String.valueOf(day)+"/"+fileName;
                }
                list.add(file.getOriginalFilename());
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists()) {
                    saveDir.getParentFile().mkdirs();
                }
                // 转存文件
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
