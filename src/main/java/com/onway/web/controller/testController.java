package com.onway.web.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Created by win7 on 2017/8/23.
 */
@Controller
public class testController {

    public static  String imageToBase64(String path){
        //path="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCAHgAeADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD6oooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACl7UVnaxqlno+nTXuozpb20Q3PI56f/XoSvoiZS5dWXyeOlcZ4r+I3hvw2zw3t+s10nW3th5jj2PZf+BEV4n8Q/izqWvyyWmjPJp+ldMods0o9z2HsP1rzIDe6hAxYngDvXsYbKnJc1Z2PGxObJPlor5nuWp/HwhmXStEyOz3E3/soH9axn+O/iTPyWGkhfeOQ/8As9cvonwy8WauiyW+kSQRH+O6YQ/+On5v0rok+Bfikrk3WkKfQzSZ/wDQK6vZYGn7srHL7XHVNY3NXT/j5fowGo6LbTDuYJTH/PNd34b+MPhjWGjiuJ5NNuDxi7Xan/fY+X88V87+MPDGoeEdUSw1XyTM8QmBibIKFmX/ANlNYRyfpVvLsPWjzU2THMsTRfLP8T7qhlSaNZImV0YZDKcgipc8dK+O/BXjzWfCNwv2GdprEH95aStmM/T0PuP1r6b8EeMtM8X6Z9q0+TZKnE0Dn54m9/b3rxsVgamH13R7OFx9PE6bM6miiiuM7gooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigCC4nit4HmmdUjQFmZjgADqa+U/ir47m8Yay0Vs7Jo9s+LePpvP/AD0Pue3oP+BV6f8AtE+J20/RrfQ7STbcX/zzY6iEdvxP8jXgeh6Vc61q9pptim64uZAiei/7TewHNe5luFiofWJng5niXOfsIGn4K8I6j4v1X7Hpy4iXme4b7kQ9/f0FfTHg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ooA+ZvG/hzxBot78PZbXQ77VJdHs4XnS1geRN6SbyhIU/SvXfhr4p1jxFJqCax4UuvD6wBDGZlcedu3ZxuRemP1rvKKQgrw39oXRtV1bXfCUmlabfXqQPJ5r20DyiP54vvbRx91q9yooA8M/aI03VrvXPCl5pGkX2pCzaWSQW0DygfPEQp2qcZ21ej1TX/iF4c8RaHd+ELrw88llmCadXRZZARhMlFr2WigD5ZhsvHc3gzTPBOn+GtWsbm0vHme+DPHG6ndxv4G352/ib7orpz4b1eH4+aHcz6deXFhb20MUt79ncwsy2zKSXxt+9Xv9FO47nzl4R8G6ze+JfiRaS6dd2UWoQ3MVtPPA8UchabcmGI2lfp2rAudH8Yaj4C03wJ/wiOoxXFnftcfbXBWFgd/G/bj/AJafe3dq+rKKAPn/AFfwZfD4p+FLGWwvbnSINFTTLi7igcxj9zLGcvjaPvf+PVxXg7wZ4i0qTxpbXWi6lzpFxawv9kk2Tv50eFQ4+bOGYYr61opCPnn4Q+IfFPhyy0rw3c+CdW+yveBZLySCVBEjuNzEbMYGc/erOl0nxP4O1Xx1p8Phm/1W08QRyw291bIXCB9+1mwD2k5Bx92vpeimM+V9f+GviHTvhHpa/YJpr5NQku7i1hG940dEQcL1/wBWucf3q2rCDVfEXxk8P+J4vCepaVp8jBZneA4ZwrZdzjjqBuP3ttfR1FAHhvx10bVdS8ceD7jT9NvbuCBx50kEDyLH+9Q/MQOK9yoopCCiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACisfxTrdt4b0G91e/3G2tY97BerdgB7kkCuO8B/FzR/GmvDStPsdQgmMbS75wmMD/dY0Aek0V4/qnx20G0v7mG007U7+1tn2zXUCLs+9tyMt0z64rrb74jaBZ+CIPFTzynTrj5YkCfvHfn5Nvr8rflQB2dFeX+FPjFoviDW7fSpbLUNNuroBrU3SDZLn7vIPft2PrXqFABRWP4p1u28N6De6vf7jbWse9gvVuwA9ySBXHeA/i5o/jTXhpWn2OoQTGNpd84TGB/usaAPSaK8f1T47aDaX9zDaadqd/a2z7ZrqBF2fe25GW6Z9cV1t98RtAs/BEHip55Tp1x8sSBP3jvz8m31+VvyoA7OivL/Cnxi0XxBrdvpUtlqGm3V0A1qbpBslz93kHv27H1r1CgAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigDx39oO7k1CPw74RtGIn1m+TzCo6IrAc/i27/AIBXnXwNtYovjLqVpEu2BI7qML6DdivUfiN8NtX8TeMrPX9H11NNmtrYQx/IS6MC/wAwP/A68f8AhLoOst8WGi+0XEDwvMZ7koQJ1Dcj8aYzk47pNL0vxXpeno+o2U5hQX8YKoipNuDNx36V1/jS2t7T4E+EEsrz7Ukt5JK7hCgD7WyNp/u/drmFhu/DOneJvDmrWVzHqV55MUKeXwWSbJ+qkdGFd5r/AIL1sfAXQ1+wzfarW7ku5rbZ+8SN92Dj/vn5f9r2oA56+48efDwr/wA+mmf+hLVvxtqN74I8SeONCW4uBBqUaNaneW2Ayo4x/wAAZ1/4DVfwzb3PjD4geEBpFrcGPTILOK7kePasfk8uWb8Pl/vU7xfpd/418ReOdfW2uDFZAG3Gw/OBKkYx/wBs1c0Adb4ys7qz+H/gbwMJJTqWtTpNdljllywO1vozr/37rF+BtrFF8ZdStIl2wJHdRhfQbsV0Wm+Ddb8fWPhvxRY62dKv7GxSxYSRnzEeNnUv7bg2fxrjvhLoOst8WGi+0XEDwvMZ7koQJ1Dcj8aAOTjuk0vS/Fel6ej6jZTmFBfxgqiKk24M3HfpXX+NLa3tPgT4QSyvPtSS3kkruEKAPtbI2n+792uYWG78M6d4m8OatZXMepXnkxQp5fBZJsn6qR0YV3mv+C9bHwF0NfsM32q1u5Lua22fvEjfdg4/75+X/a9qAOevuPHnw8K/8+mmf+hLX1/XyH4Zt7nxh8QPCA0i1uDHpkFnFdyPHtWPyeXLN+Hy/wB6vryhgwooopCCiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigD//Z";
        byte data[]=null;
        try {
            InputStream in= new FileInputStream(path);
            data =new byte[in.read()];
            in.read(data);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder=new BASE64Encoder();
        System.out.println(encoder.encode(data));
        return encoder.encode(data);
    }
    public static boolean base64ToImage(String base64, String path) {// 对字节数组字符串进行Base64解码并生成图片
        if (base64 == null){ // 图像数据为空
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCAHgAeADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD6oooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACl7UVnaxqlno+nTXuozpb20Q3PI56f/XoSvoiZS5dWXyeOlcZ4r+I3hvw2zw3t+s10nW3th5jj2PZf+BEV4n8Q/izqWvyyWmjPJp+ldMods0o9z2HsP1rzIDe6hAxYngDvXsYbKnJc1Z2PGxObJPlor5nuWp/HwhmXStEyOz3E3/soH9axn+O/iTPyWGkhfeOQ/8As9cvonwy8WauiyW+kSQRH+O6YQ/+On5v0rok+Bfikrk3WkKfQzSZ/wDQK6vZYGn7srHL7XHVNY3NXT/j5fowGo6LbTDuYJTH/PNd34b+MPhjWGjiuJ5NNuDxi7Xan/fY+X88V87+MPDGoeEdUSw1XyTM8QmBibIKFmX/ANlNYRyfpVvLsPWjzU2THMsTRfLP8T7qhlSaNZImV0YZDKcgipc8dK+O/BXjzWfCNwv2GdprEH95aStmM/T0PuP1r6b8EeMtM8X6Z9q0+TZKnE0Dn54m9/b3rxsVgamH13R7OFx9PE6bM6miiiuM7gooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigCC4nit4HmmdUjQFmZjgADqa+U/ir47m8Yay0Vs7Jo9s+LePpvP/AD0Pue3oP+BV6f8AtE+J20/RrfQ7STbcX/zzY6iEdvxP8jXgeh6Vc61q9pptim64uZAiei/7TewHNe5luFiofWJng5niXOfsIGn4K8I6j4v1X7Hpy4iXme4b7kQ9/f0FfTHg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ooA+ZvG/hzxBot78PZbXQ77VJdHs4XnS1geRN6SbyhIU/SvXfhr4p1jxFJqCax4UuvD6wBDGZlcedu3ZxuRemP1rvKKQgrw39oXRtV1bXfCUmlabfXqQPJ5r20DyiP54vvbRx91q9yooA8M/aI03VrvXPCl5pGkX2pCzaWSQW0DygfPEQp2qcZ21ej1TX/iF4c8RaHd+ELrw88llmCadXRZZARhMlFr2WigD5ZhsvHc3gzTPBOn+GtWsbm0vHme+DPHG6ndxv4G352/ib7orpz4b1eH4+aHcz6deXFhb20MUt79ncwsy2zKSXxt+9Xv9FO47nzl4R8G6ze+JfiRaS6dd2UWoQ3MVtPPA8UchabcmGI2lfp2rAudH8Yaj4C03wJ/wiOoxXFnftcfbXBWFgd/G/bj/AJafe3dq+rKKAPn/AFfwZfD4p+FLGWwvbnSINFTTLi7igcxj9zLGcvjaPvf+PVxXg7wZ4i0qTxpbXWi6lzpFxawv9kk2Tv50eFQ4+bOGYYr61opCPnn4Q+IfFPhyy0rw3c+CdW+yveBZLySCVBEjuNzEbMYGc/erOl0nxP4O1Xx1p8Phm/1W08QRyw291bIXCB9+1mwD2k5Bx92vpeimM+V9f+GviHTvhHpa/YJpr5NQku7i1hG940dEQcL1/wBWucf3q2rCDVfEXxk8P+J4vCepaVp8jBZneA4ZwrZdzjjqBuP3ttfR1FAHhvx10bVdS8ceD7jT9NvbuCBx50kEDyLH+9Q/MQOK9yoopCCiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACisfxTrdt4b0G91e/3G2tY97BerdgB7kkCuO8B/FzR/GmvDStPsdQgmMbS75wmMD/dY0Aek0V4/qnx20G0v7mG007U7+1tn2zXUCLs+9tyMt0z64rrb74jaBZ+CIPFTzynTrj5YkCfvHfn5Nvr8rflQB2dFeX+FPjFoviDW7fSpbLUNNuroBrU3SDZLn7vIPft2PrXqFABRWP4p1u28N6De6vf7jbWse9gvVuwA9ySBXHeA/i5o/jTXhpWn2OoQTGNpd84TGB/usaAPSaK8f1T47aDaX9zDaadqd/a2z7ZrqBF2fe25GW6Z9cV1t98RtAs/BEHip55Tp1x8sSBP3jvz8m31+VvyoA7OivL/Cnxi0XxBrdvpUtlqGm3V0A1qbpBslz93kHv27H1r1CgAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigDx39oO7k1CPw74RtGIn1m+TzCo6IrAc/i27/AIBXnXwNtYovjLqVpEu2BI7qML6DdivUfiN8NtX8TeMrPX9H11NNmtrYQx/IS6MC/wAwP/A68f8AhLoOst8WGi+0XEDwvMZ7koQJ1Dcj8aYzk47pNL0vxXpeno+o2U5hQX8YKoipNuDNx36V1/jS2t7T4E+EEsrz7Ukt5JK7hCgD7WyNp/u/drmFhu/DOneJvDmrWVzHqV55MUKeXwWSbJ+qkdGFd5r/AIL1sfAXQ1+wzfarW7ku5rbZ+8SN92Dj/vn5f9r2oA56+48efDwr/wA+mmf+hLVvxtqN74I8SeONCW4uBBqUaNaneW2Ayo4x/wAAZ1/4DVfwzb3PjD4geEBpFrcGPTILOK7kePasfk8uWb8Pl/vU7xfpd/418ReOdfW2uDFZAG3Gw/OBKkYx/wBs1c0Adb4ys7qz+H/gbwMJJTqWtTpNdljllywO1vozr/37rF+BtrFF8ZdStIl2wJHdRhfQbsV0Wm+Ddb8fWPhvxRY62dKv7GxSxYSRnzEeNnUv7bg2fxrjvhLoOst8WGi+0XEDwvMZ7koQJ1Dcj8aAOTjuk0vS/Fel6ej6jZTmFBfxgqiKk24M3HfpXX+NLa3tPgT4QSyvPtSS3kkruEKAPtbI2n+792uYWG78M6d4m8OatZXMepXnkxQp5fBZJsn6qR0YV3mv+C9bHwF0NfsM32q1u5Lua22fvEjfdg4/75+X/a9qAOevuPHnw8K/8+mmf+hLX1/XyH4Zt7nxh8QPCA0i1uDHpkFnFdyPHtWPyeXLN+Hy/wB6vryhgwooopCCiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigD//Z");
//            for (int i = 0; i < bytes.length; ++i) {
//                if (bytes[i] < 0) {// 调整异常数据
//                    bytes[i] += 256;
//                }
//            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(path);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //测试
    public static void main(String[] str) throws Exception {

        String path = "D:\\w18.jpg";
        String base64 = testController.imageToBase64(path);
        testController.base64ToImage(base64, "E:\\2.jpg");
        System.out.println(base64+"+++++++++++");



    }

    public static byte[] decode(final byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }

    /**
     * 二进制数据编码为BASE64字符串
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(final byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }
}
