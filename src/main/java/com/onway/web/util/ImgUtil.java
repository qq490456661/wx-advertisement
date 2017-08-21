package com.onway.web.util;

import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by zs on 2017/8/21 0021.
 */
public class ImgUtil {

    private static final Logger log = LoggerFactory.getLogger(ImgUtil.class);
    /**
     *
     * 有损压缩图片，按照原先比例。
     * @return
     */
    public static void uploadFileAndCreateThumbnail(MultipartFile srcFile, File descFile) throws IOException {
        //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
        Thumbnails.of(srcFile.getInputStream()).scale(1f).outputQuality(0.5f).toFile(descFile);
    }
}
