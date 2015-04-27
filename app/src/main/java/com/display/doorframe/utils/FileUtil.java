package com.display.doorframe.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

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

}