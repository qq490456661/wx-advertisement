package com.onway.web.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Pattern;

@Controller
public class WxController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String wxController(@RequestParam(value = "url", required = true) String url) throws Exception {
        log.info("开始输出");
        if (url == null || "".equals(url)){
//            return new ModelAndView("new");
            throw new RuntimeException("url为空");
        }
        Pattern pattern1 = Pattern.compile("^http(s)?://");
        Pattern pattern2 = Pattern.compile("^//");
        if (!pattern1.matcher(url).find()) {
            url += "http://";
        }
        if (pattern2.matcher(url).find()) {
            url += "http:";
        }
        Document doc = Jsoup.connect(url).referrer("never").get();
//        doc.select("body").first().after("<iframe src='html/index.html'></iframe>");
        doc.select("script").first().after("<script type='text/javascript' src='//cdn.bootcss.com/jquery/3.2.1/jquery.min.js'></script>'></script>");
        doc.select("script").first().after("<script type='text/javascript' src='js/jquery.js'></script>");
        doc.select("meta").last().after("<meta name='referrer' content=''>");
        doc.select("script").last().after("<script type='text/javascript'>" +
                "(function(){" +
                "var dataSrc=$('iframe').attr('data-src'); " +
                "$('iframe').attr('src', dataSrc);" +
                "$('iframe').css('display', 'block');" +
                "$('.img_loading').hide();" +
                "})();" +
                "</script>");
        Elements iframes = doc.select("iframe");
        for (Element iframe:iframes
             ) {
            if (iframe.hasClass("video_iframe")) {
                String iframeAttr = iframe.attr("data-src");
                iframe.attr("src",  iframeAttr);
                iframe.attr("style", "display:block");
//                iframe.removeAttr("data-src");
            }
        }
        log.info("视频转换成功");
        Elements elements = doc.select("img");
        for (Element e:elements
             ) {
            String attr = e.attr("data-src");
            if (attr == null || "".equals(attr))
                continue;
            e.attr("src", "http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=" + attr); //http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=
        }
        log.info("图片转链成功");
        String html = doc.html();
        File file = new File("src"+ File.separator + "main"+ File.separator + "resources"+ File.separator + "templates"+ File.separator + "new.html" );
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(html.getBytes());
        fos.flush();
        fos.close();
        log.info("输出成功跳转页面");
        return "new";
    }

    public static void main(String[] args) throws Exception {
        String url = "http://mp.weixin.qq.com/s/yhKh-5pLKXDxE4rKkVE4cQ";
        WxController wx = new WxController();
        wx.wxController(url);
    }
}
