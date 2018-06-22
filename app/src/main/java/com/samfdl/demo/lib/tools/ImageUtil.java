package com.samfdl.demo.lib.tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class ImageUtil {
    /**
     * 拼接图片
     *
     * @param first  分享的背景图
     * @param second 生成的二维码图
     * @author samfdl
     * @date 2018/6/22
     */
    public static Bitmap add2Bitmap(Bitmap first, Bitmap second) {
        first = ImageUtil.scaleImg(first, 1125, 2001);
        Bitmap result = Bitmap.createBitmap(1125, 2001, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(first, 0, 0, null);
        canvas.drawBitmap(second, 420, 1572, null);
        return result;
    }

    /**
     * 描述：缩放图片,不压缩的缩放.
     *
     * @param bitmap    the bitmap
     * @param newWidth  新图片的宽
     * @param newHeight 新图片的高
     * @return Bitmap 新图片
     */
    public static Bitmap scaleImg(Bitmap bitmap, int newWidth, int newHeight) {
        if (bitmap == null) {
            return null;
        }
        if (newHeight <= 0 || newWidth <= 0) {
            return bitmap;
        }
        // 获得图片的宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (width <= 0 || height <= 0) {
            return null;
        }
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        //得到新的图片
        Bitmap newBm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newBm;
    }
}