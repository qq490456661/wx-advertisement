package com.onway.web.controller;

import com.onway.web.util.QrCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by zs on 2017/8/9 0009.
 */
@RestController
public class QrCodeController {

    @GetMapping("/qrcode")
    public void qrCode(HttpServletResponse response){
        String descPath = "D:\\new.jpg";
        QrCodeUtil.encode("http://192.168.1.14:8090?url=http://mp.weixin.qq.com/s/yhKh-5pLKXDxE4rKkVE4cQ&v=1",
                512, 512, "D:\\20141230095312311.jpg", descPath);
        File file = new File(descPath);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int b;
            while ((b=fis.read(bytes)) != -1){
                baos.write(bytes, 0, b);
            }
            response.getOutputStream().write(baos.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
