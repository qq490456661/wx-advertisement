package com.onway.web.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HttpUtil {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/templates")
    public void templates(@RequestParam("img") String img, HttpServletResponse response) throws IOException {

        ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(img, byte[].class);
        byte[] bytes = responseEntity.getBody();
//        byte[] doc = Jsoup.connect(img).referrer("never").get().html().getBytes();
//        try {
//            // 获取图片URL
//            URL uri = new URL(img);
//            // 获得连接
//            HttpURLConnection connection = null;
//
//            connection = (HttpURLConnection)uri.openConnection();
//            connection.setRequestProperty("Referer", "qq.com");
//            // 设置 HttpURLConnection的字符编码
//            connection.setRequestProperty("Accept-Charset", "UTF-8");
//            connection.setRequestMethod("GET");
//            // 设置10秒的相应时间
//            connection.setConnectTimeout(10 * 1000);
//
//            InputStream is = connection.getInputStream();
//
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//
//            byte[] buffer = new byte[1024*2];
//            int len = 0;
//            while( (len=is.read(buffer)) != -1 ){
//                bos.write(buffer, 0, len);
//            }
//            bos.flush();
//            is.close();
//
//            response.setContentType("image/png");
            ServletOutputStream sos = response.getOutputStream();
//            sos.write(bos.toByteArray());
            sos.write(bytes);

//            return response;

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}
