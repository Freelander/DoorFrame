package com.display.doorframe.data;

import com.display.doorframe.R;
import com.display.doorframe.utils.FileUtil;

/**
 * Created by Jun on 2015/4/26.
 */
public class ImageResource {

    public final static String path = android.os.Environment.
            getExternalStorageDirectory().getAbsolutePath();//获取外部sdcard路径

    public final static String hotFolderPath = path + "/ringDoor/hot/";//热门图片存储的路径
    public final static String favoriteFolderPath = path + "/ringDoor/favorite/";//收藏图片存储的路径

    public final static String categoryDoorWcFolderPath = path + "/ringDoor/category/door/wc/";
    public final static String categoryDoorBathRoomFolderPath = path + "/ringDoor/category/door/bathroom/";
    public final static String categoryDoorBedRoomFolderPath = path + "/ringDoor/category/door/bedroom/";
    public final static String categoryDoorHallFolderPath = path + "/ringDoor/category/door/hall/";

    public final static String categoryFrameWcFolderPath = path + "/ringDoor/category/frame/wc/";
    public final static String categoryFrameBathRoomFolderPath = path + "/ringDoor/category/frame/bathroom/";
    public final static String categoryFrameBedRoomFolderPath = path + "/ringDoor/category/frame/bedroom/";
    public final static String categoryFrameHallFolderPath = path + "/ringDoor/category/frame/hall/";

    /**
     * 热门图片
     */
    public final static String[] hotFileName = {//文件名
            "ic_hot1.png", "ic_hot2.png",
            "ic_hot3.png", "ic_hot4.png",
            "ic_hot5.png", "ic_hot6.png",
            "ic_hot7.png", "ic_hot8.png",
            "ic_hot9.png", "ic_hot10.png"
    };
    public final static Integer[] hotRawResource = {//资源Id
            R.raw.ic_hot_one, R.raw.ic_hot_two,
            R.raw.ic_hot_three, R.raw.ic_hot_four,
            R.raw.ic_hot_five, R.raw.ic_hot_six,
            R.raw.ic_hot_seven, R.raw.ic_hot_eight,
            R.raw.ic_hot_nine, R.raw.ic_hot_ten
    };

    /**
     * 收藏图片
     */
    public final static String[] favoriteFileName = {
            "ic_favorite1.png", "ic_favorite2.png",
            "ic_favorite3.png", "ic_favorite4.png",
            "ic_favorite5.png", "ic_favorite6.png",
            "ic_favorite7.png", "ic_favorite8.png",
            "ic_favorite9.png", "ic_favorite10.png"
    };
    public final static Integer[] favoriteRawResource = {
            R.raw.ic_favorite1, R.raw.ic_favorite2,
            R.raw.ic_favorite3, R.raw.ic_favorite4,
            R.raw.ic_favorite5, R.raw.ic_favorite6,
            R.raw.ic_favorite7, R.raw.ic_favorite8,
            R.raw.ic_favorite9, R.raw.ic_favorite10
    };

    /**
     * 门分类之厕所
     */
    public final static String[] doorWcFileName = {
            "ic_door_wc1.png", "ic_door_wc2.png",
    };
    public final static Integer[] doorWcRawResource = {
            R.raw.ic_door,R.raw.ic_door_brown
    };

    /**
     * 门分类之浴室
     */
    public final static String[] doorBathRoomFileName = {
            "ic_door_bathroom1.png", "ic_door_bathroom2.png",
    };
    public final static Integer[] doorBathRoomRawResource = {
            R.raw.ic_door,R.raw.ic_door_red
    };

    /**
     * 门分类之卧室
     */
    public final static String[] doorBedRoomFileName = {
            "ic_door_bedroom1.png", "ic_door_bedroom2.png"
    };
    public final static Integer[] doorBedRoomRawResource = {
            R.raw.ic_door_brown,R.raw.ic_door_red
    };

    /**
     * 门分类之大厅
     */
    public final static String[] doorHallFileName = {
            "ic_door_hall1.png", "ic_door_hall2.png",
    };
    public final static Integer[] doorHallRawResource = {
            R.raw.ic_door_yellow,R.raw.ic_door_brown
    };


    /**
     * 门框分类之厕所
     */
    public final static String[] frameWcFileName = {
            "ic_frame_wc1.png", "ic_frame_wc2.png",
    };
    public final static Integer[] frameWcRawResource = {
            R.raw.ic_door_frame_blue,R.raw.ic_door_frame_yellow
    };

    /**
     * 门框分类之浴室
     */
    public final static String[] frameBathRoomFileName = {
            "ic_frame_bathroom1.png", "ic_frame_bathroom2.png",
    };
    public final static Integer[] frameBathRoomRawResource = {
            R.raw.ic_door_frame,R.raw.ic_door_frame_red
    };

    /**
     * 门框分类之卧室
     */
    public final static String[] frameBedRoomFileName = {
            "ic_frame_bedroom1.png", "ic_frame_bedroom2.png"
    };
    public final static Integer[] frameBedRoomRawResource = {
            R.raw.ic_door_frame_blue,R.raw.ic_door_frame_yellow
    };

    /**
     * 门框分类之大厅
     */
    public final static String[] frameHallFileName = {
            "ic_frame_hall1.png", "ic_frame_hall2.png"
    };
    public final static Integer[] frameHallRawResource = {
            R.raw.ic_door_frame_red,R.raw.ic_door_frame
    };

    /**
     * 获取图片目录路径
     * @param folderPath 图片所在目录
     * @return 所有图片目录数组
     */
    public final static String[] getImageFolder(String folderPath){
        String[] imageName = FileUtil.getImageNames(folderPath);
        String[] imageFolder = new String[imageName.length];
        for(int i = 0; i < imageName.length; i++){
            imageFolder[i] = folderPath + imageName[i];
        }
        return imageFolder;
    }

}
