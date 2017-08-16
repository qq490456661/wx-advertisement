package com.onway.web.controller;

import com.onway.web.dao.UserDao;
import org.apache.commons.io.FileUtils;
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
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by win7 on 2017/8/1.
 */
@RestController
public class GetUrlController {
    //图片保存路径
    //private static final String saveImgPath="E://imgs";
    //private static final String htmlPath="192.168.0.130:8090/";
    private static final String UrlPath="http://118.178.135.106:8098/";
//    private static final String relativelyPath=System.getProperty("user.dir")+"/src/";
    private static final String relativelyPath="/usr/local/wx_resource/";
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final int id=1;

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

    @RequestMapping("/addAd.do")
    public String addAd(HttpServletResponse response, HttpServletRequest request,
                      @RequestParam(value ="bottomText" ,required = false) String bottomText,
                      @RequestParam(value ="links" ,required = false) String links,
                      @RequestParam(value ="author" ,required = false) String author,
                      @RequestParam(value ="tel" ,required = false) String tel,
                      @RequestParam(value ="title" ,required = false) String title,
                      @RequestParam(value ="time" ,required = false) String time,
                      @RequestParam(value ="timestamp" ,required = false) String timestamp,
                      @RequestParam(value ="status" ,required = false) String status){


        System.out.println(time);
        //String url="\"http://mp.weixin.qq.com/s/4k6TOX9EFmhsuiSnQMHmkA\"";
        String url="http://mp.weixin.qq.com/s/yhKh-5pLKXDxE4rKkVE4cQ";
        // 利用Jsoup获得连接
        Connection connect = Jsoup.connect(url);
        String[] ary = url.split("/");
        String name="";
        for (int i = 0; i < ary.length; i++) {
            if (i==ary.length-1){
                name=ary[i]+".html";
            }
        }
        try {
            // 得到Document对象
            Document document = connect.referrer("never").get();
            document.select("meta").last().after("<meta name='referrer' content='never'>");
            System.out.println(document);
            // 查找所有img标签
            Elements imgs = document.getElementsByTag("img");
            Element authors=document.getElementById("post-user");
            authors.text(author);
            LocalDate date = LocalDate.now();
            System.out.println(date.toString());
            Element time1=document.getElementById("post-date");
            time1.text(time);
            Element title1=document.getElementById("activity-name");
            title1.text(title);
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
            //修改电话
            Element newTel=doc.getElementById("phone-link");
            newTel.attr("href","tel:"+tel);
            //修改底部文案
            Element newBottomText=doc.getElementById("adTxt");
            newBottomText.text(bottomText);
            //修改底部文案连接
            Element newBottomLink=doc.getElementById("adLink");
            newBottomLink.attr("href",links);
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
            pubNum.attr("src","../../images/QuickMark/"+day+"/QuickMark"+timestamp+".jpg");
            //修改底部广告
            if(status=="1"){
                Element BottomAd=doc.getElementById("adImg");
                BottomAd.attr("src","../../images/BottomAd/"+day+"/QuickMark"+timestamp+".jpg");
                Element BottomText =doc.getElementById("adTxt");
                BottomText.remove();
            }else {
                Element BottomAd=doc.getElementById("adImg");
                BottomAd.remove();
                Element BottomText =doc.getElementById("adTxt");
                BottomText.text(bottomText);
            }
            //修改全屏广告
            Element FullAd=doc.getElementById("fullImg");
            FullAd.attr("src","../../images/FullAd/"+day+"/QuickMark"+timestamp+".jpg");

            //生成带广告的广告页面
            File newFile = new File(relativelyPath+dirPath+"/"+fileName);
            FileUtils.writeByteArrayToFile(newFile,String.valueOf(doc).getBytes());
            Runtime.getRuntime().exec("chmod -R 755 " + relativelyPath);
//            response.sendRedirect(fileName);
            String returnPath=UrlPath+dirPath+"/"+fileName;
            return returnPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

//    // 图片宽度的一般
//    private static final int IMAGE_WIDTH = 80;
//    private static final int IMAGE_HEIGHT = 80;
//    private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
//    private static final int FRAME_WIDTH = 2;
//
//    // 二维码写码器
//    private static MultiFormatWriter mutiWriter = new MultiFormatWriter();
//
//    public static void encode(String content, int width, int height,
//                              String srcImagePath, String destImagePath) {
//        try {
//            ImageIO.write(genBarcode(content, width, height, srcImagePath),
//                    "jpg", new File(destImagePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static BufferedImage genBarcode(String content, int width,
//                                            int height, String srcImagePath) throws WriterException,
//            IOException {
//        // 读取源图像
//        BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH,
//                IMAGE_HEIGHT, true);
//        int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
//        for (int i = 0; i < scaleImage.getWidth(); i++) {
//            for (int j = 0; j < scaleImage.getHeight(); j++) {
//                srcPixels[i][j] = scaleImage.getRGB(i, j);
//            }
//        }
//
//        Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
//        hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
//        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//        // 生成二维码
//        BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE,
//                width, height, hint);
//
//        // 二维矩阵转为一维像素数组
//        int halfW = matrix.getWidth() / 2;
//        int halfH = matrix.getHeight() / 2;
//        int[] pixels = new int[width * height];
//
//        for (int y = 0; y < matrix.getHeight(); y++) {
//            for (int x = 0; x < matrix.getWidth(); x++) {
//                // 读取图片
//                if (x > halfW - IMAGE_HALF_WIDTH
//                        && x < halfW + IMAGE_HALF_WIDTH
//                        && y > halfH - IMAGE_HALF_WIDTH
//                        && y < halfH + IMAGE_HALF_WIDTH) {
//                    pixels[y * width + x] = srcPixels[x - halfW
//                            + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
//                }
//                // 在图片四周形成边框
//                else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
//                        && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
//                        && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
//                        + IMAGE_HALF_WIDTH + FRAME_WIDTH)
//                        || (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH
//                        && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
//                        && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
//                        + IMAGE_HALF_WIDTH + FRAME_WIDTH)
//                        || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
//                        && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
//                        && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
//                        - IMAGE_HALF_WIDTH + FRAME_WIDTH)
//                        || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
//                        && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
//                        && y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
//                        + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
//                    pixels[y * width + x] = 0xfffffff;
//                } else {
//                    // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
//                    pixels[y * width + x] = matrix.get(x, y) ? 0xff000000
//                            : 0xfffffff;
//                }
//            }
//        }
//
//        BufferedImage image = new BufferedImage(width, height,
//                BufferedImage.TYPE_INT_RGB);
//        image.getRaster().setDataElements(0, 0, width, height, pixels);
//
//        return image;
//    }
//
//    /**
//     * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标
//     *
//     * @param srcImageFile
//     *            源文件地址
//     * @param height
//     *            目标高度
//     * @param width
//     *            目标宽度
//     * @param hasFiller
//     *            比例不对时是否需要补白：true为补白; false为不补白;
//     * @throws IOException
//     */
//    private static BufferedImage scale(String srcImageFile, int height,
//                                       int width, boolean hasFiller) throws IOException {
//        double ratio = 0.0; // 缩放比例
//        File file = new File(srcImageFile);
//        BufferedImage srcImage = ImageIO.read(file);
//        Image destImage = srcImage.getScaledInstance(width, height,
//                BufferedImage.SCALE_SMOOTH);
//        // 计算比例
//        if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
//            if (srcImage.getHeight() > srcImage.getWidth()) {
//                ratio = (new Integer(height)).doubleValue()
//                        / srcImage.getHeight();
//            } else {
//                ratio = (new Integer(width)).doubleValue()
//                        / srcImage.getWidth();
//            }
//            AffineTransformOp op = new AffineTransformOp(
//                    AffineTransform.getScaleInstance(ratio, ratio), null);
//            destImage = op.filter(srcImage, null);
//        }
//        if (hasFiller) {// 补白
//            BufferedImage image = new BufferedImage(width, height,
//                    BufferedImage.TYPE_INT_RGB);
//            Graphics2D graphic = image.createGraphics();
//            graphic.setColor(Color.white);
//            graphic.fillRect(0, 0, width, height);
//            if (width == destImage.getWidth(null))
//                graphic.drawImage(destImage, 0,
//                        (height - destImage.getHeight(null)) / 2,
//                        destImage.getWidth(null), destImage.getHeight(null),
//                        Color.white, null);
//            else
//                graphic.drawImage(destImage,
//                        (width - destImage.getWidth(null)) / 2, 0,
//                        destImage.getWidth(null), destImage.getHeight(null),
//                        Color.white, null);
//            graphic.dispose();
//            destImage = image;
//        }
//        return (BufferedImage) destImage;
//    }
}
