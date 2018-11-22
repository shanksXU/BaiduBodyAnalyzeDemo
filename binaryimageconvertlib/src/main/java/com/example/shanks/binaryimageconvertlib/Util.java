package com.example.shanks.binaryimageconvertlib;

import android.util.Base64;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Util {

    public static BufferedImage resize(BufferedImage img, int w, int h){
        // 缩放（平滑）
        Image tmp = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        // 新建一个ARGB
        BufferedImage dimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        // 绘制BuffImg
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return dimg;
    }


    public static byte[] convert(String labelmapBase64, int width, int height){
        // 解析base64串
        byte[] bytes = Base64.decode(labelmapBase64, Base64.DEFAULT);
        //
        try{
            InputStream is = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(is);
            // 对图片进行缩放
            BufferedImage newImage = resize(image, width, height);

            BufferedImage grayImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int rgb = newImage.getRGB(i, j);
                    grayImg.setRGB(i, j, rgb * 255); // 写入换缓存（很重要）
                }
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(grayImg, "jpg", baos);
            baos.flush();
            byte[] data = baos.toByteArray();
            return data;

        }catch (IOException e){
            e.printStackTrace();
        }

        return  null;
    }
}
