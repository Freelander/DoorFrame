package com.display.doorframe.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jun on 2015/4/26.
 */
public class FileUtil {
    public static String hotpath="";

    /**
     *
     * @param folderPath 目录路径
     * @param fileName 文件名
     * @param rawResource 文件资源ID
     */
    public static void createFile(Context context,String folderPath,
                                  String[] fileName, Integer[] rawResource){
        try {
            File dir = new File(folderPath);// 目录路径
            if (!dir.exists()) {// 如果不存在，则创建路径名
                System.out.println("要存储的目录不存在");
                if (dir.mkdirs()) {// 创建该路径名，返回true则表示创建成功
                    System.out.println("已经创建文件存储目录");
                } else {
                    System.out.println("创建目录失败");
                }
            }

            for(int i=0; i<fileName.length; i++){
                String filePath = folderPath + "/" + fileName[i];// 文件路径
                // 目录存在，则将apk中raw中的需要的文档复制到该目录下
                File file = new File(filePath);
                if (!file.exists()) {// 文件不存在
                    InputStream ins = context.getResources().openRawResource(rawResource[i]);
                    FileOutputStream fos = new FileOutputStream(file);//写出
                    byte[] buffer = new byte[8192];
                    int count = 0;// 循环写出
                    while ((count = ins.read(buffer)) > 0) {
                        fos.write(buffer, 0, count);
                    }

                    Log.i("TAG", "已经创建该文件");
                    fos.close();// 关闭流
                    ins.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG",e.getMessage());
        }
    }

    /**
     * 将门Bitmap和门框Bitmap整合成一个完整Bitmap
     * @param doorBitmap
     * @param frameBitmap
     * @return
     */
    public static Bitmap toConformBitmap(Bitmap doorBitmap, Bitmap frameBitmap){
        int doorWidth = doorBitmap.getWidth();
        int doorHeight = doorBitmap.getHeight();
        int frameWidth = frameBitmap.getWidth();
        int frameHeight = frameBitmap.getHeight();
        int a = (frameWidth-doorWidth)/2;
        int b = (frameHeight - doorHeight)/2;
        //create the new blank bitmap 创建一个新的和SRC长度宽度一样的位图,并设置为透明
        Bitmap bitmap = Bitmap.createBitmap(frameWidth,doorHeight, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(bitmap);
        cv.drawBitmap(doorBitmap,a,b,null);//在坐标0,0画入doorBitmap
        cv.drawBitmap(frameBitmap,0,0,null);//在坐标0,0画入frameBitmap
        cv.save(Canvas.ALL_SAVE_FLAG);
        cv.restore();
        return bitmap;

//        if (!doorBitmap.isMutable()) {
//            //设置图片为背景为透明
//            frameBitmap = frameBitmap.copy(Bitmap.Config.ARGB_8888, true);
//        }
//        Paint paint = new Paint();
//        Canvas canvas = new Canvas(frameBitmap);
//        canvas.drawBitmap(doorBitmap, 30, 30, paint);//叠加新图b2
//        canvas.save(Canvas.ALL_SAVE_FLAG);
//        canvas.restore();
//        return frameBitmap;
    }

    /**
     * 保存Bitmap为一张图片
     * @param bitmap
     * @param path 保存目录
     */
    public static boolean saveBitmap(Bitmap bitmap,String path){
        boolean bool = false;
        File filePath = new File(path);
        if (!filePath.exists()) {// 如果不存在，则创建路径名
            if (filePath.mkdirs()) {// 创建该路径名，返回true则表示创建成功
            } else {
                Log.i("TAG","创建目录失败");
            }
        }

        try {
            Random ran = new Random();//随机生成
            File imagePath = new File(path+"/"+ran.nextLong()+".png");
            FileOutputStream out = new FileOutputStream(imagePath);
            if(bitmap.compress(Bitmap.CompressFormat.PNG,100,out)){
                bool = true;
                out.flush();
                out.close();
            }
        }catch (Exception e){
            Log.i("TAG","图片保存失败"+e.getMessage());
            bool = false;
        }

        return bool;
    }


    /**
     * 获取目录下所有图片文件名称
     * @param folderPath 目录路径
     * @return 名称数组
     */
    public static String[] getImageNames(String folderPath) {
        File file01 = new File(folderPath);

        String[] files01 = file01.list();

        int imageFileNums = 0;
        for (int i = 0; i < files01.length; i++) {
            File file02 = new File(folderPath + "/" + files01[i]);

            if (!file02.isDirectory()) {

                if (isImageFile(file02.getName())) {

                    imageFileNums++;
                }
            }
        }

        String[] files02 = new String[imageFileNums];

        int j = 0;
        for (int i = 0; i < files01.length; i++) {
            File file02 = new File(folderPath + "/" + files01[i]);

            if (!file02.isDirectory()) {

                if (isImageFile(file02.getName())) {
                    files02[j] = file02.getName();
                    j++;
                }
            }
        }
        return files02;
    }

    /**
     * 判断是否为图片文件
     * @param fileName
     * @return
     */
    private static boolean isImageFile(String fileName) {
        String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1,
                fileName.length());
        if (fileEnd.equalsIgnoreCase("jpg")) {
            return true;
        } else if (fileEnd.equalsIgnoreCase("png")) {
            return true;
        } else if (fileEnd.equalsIgnoreCase("bmp")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteImageFile(ArrayList<String> mSelectorImage){
        boolean result = false;
        for (int i=0; i < mSelectorImage.size(); i++){
            File imageFile = new File(mSelectorImage.get(i));
            if(imageFile.exists()){
                if (imageFile.delete()){
                    result = true;
                }else{
                    Log.i("TAG","删除失败");
                }
            }
        }
        return result;
    }



}