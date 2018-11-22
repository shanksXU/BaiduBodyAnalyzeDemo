package com.example.shanks.testbodyanalysis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.util.Base64;
import android.util.Log;

import com.baidu.aip.util.Base64Util;

import java.io.ByteArrayOutputStream;


public class Util {
    private static final String TAG = Util.class.toString();

    public static final String APP_ID = "Your APP ID";        // APP id
    public static final String APP_KEY = "You APP Key";       // APP key
    public static final String SECRET_KEY = "You Secret Key";    // APP secret key


    // 人像分割（返回的二值图像需要进行二次处理才可查看分割效果）
    /*
    目前接口返回的是一个4道通图片（Base64解码收到的字符串，再用一个图片解码器转为PNG格式）， 把这个图片的RGB通中 值是1的改为255  就可以显示成纯黑纯白的图片了；
     */
    public static Bitmap thumbnailImg(Bitmap bm, int width, int height){
        // 缩放
        Bitmap scaleBitmap = ThumbnailUtils.extractThumbnail(bm, width, height);
        Bitmap newbitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int r, g, b = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = scaleBitmap.getPixel(i, j);
                r = Color.red(color) * 255;
                g = Color.green(color) * 255;
                b = Color.blue(color) * 255;

                newbitmap.setPixel(i, j, Color.argb(122, r, g, b));

            }
        }
        return scaleBitmap;
    }

    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight){
        int w = bm.getWidth();
        int h = bm.getHeight();

        float scalew = ((float) newWidth) / w;
        float scaleh = ((float) newHeight) / h;

        Matrix matrix = new Matrix();
        matrix.postScale(scalew, scaleh);

        Bitmap scaleBitmap = Bitmap.createBitmap(bm, 0, 0, w, h, matrix, true);
        Bitmap newbitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        int r, g, b = 0;
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                int color = scaleBitmap.getPixel(i, j);
                r = Color.red(color) * 255;
                g = Color.green(color) * 255;
                b = Color.blue(color) * 255;

                newbitmap.setPixel(i, j, Color.argb(122, r, g, b));

            }
        }
        return newbitmap;
    }

    /**
     * 转为灰度图
     *
     * @param bmp
     *            原图bitmap
     * @param w
     *            转换后的宽
     * @param h
     *            转换后的高
     * @return
     */
    public static Bitmap convertToBMW(Bitmap bmp, int w, int h,int tmp) {
        int width = bmp.getWidth(); // 获取位图的宽
        int height = bmp.getHeight(); // 获取位图的高
        int[] pixels = new int[width * height]; // 通过位图的大小创建像素点数组
        // 设定二值化的域值，默认值为100
        //tmp = 180;
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];
                // 分离三原色
                alpha = ((grey & 0xFF000000) >> 24);
                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);
                if (red > tmp) {
                    red = 255;
                } else {
                    red = 0;
                }
                if (blue > tmp) {
                    blue = 255;
                } else {
                    blue = 0;
                }
                if (green > tmp) {
                    green = 255;
                } else {
                    green = 0;
                }
                pixels[width * i + j] = alpha << 24 | red << 16 | green << 8
                        | blue;
                if (pixels[width * i + j] == -1) {
                    pixels[width * i + j] = -1;
                } else {
                    pixels[width * i + j] = -16777216;
                }
            }
        }
        // 新建图片
        Bitmap newBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // 设置图片数据
        newBmp.setPixels(pixels, 0, width, 0, 0, width, height);
        Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(newBmp, w, h);
        return resizeBmp;
    }
}
