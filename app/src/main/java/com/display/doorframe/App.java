package com.display.doorframe;

import android.app.Application;

import com.display.doorframe.data.ImageResource;
import com.display.doorframe.utils.FileUtil;

/**
 * Created by Jun on 2015/4/15.
 *
 */
public class App extends Application {

    private static App mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        initResource();
    }

    public static App getInstance(){
        return mContext;
    }

    /**
     * 在SDCard上创建目录并复制图片都相对应位置
     */
    public void initResource(){

        FileUtil.createFile(this, ImageResource.hotFolderPath,
                ImageResource.hotFileName, ImageResource.hotRawResource);

        FileUtil.createFile(this,ImageResource.favoriteFolderPath,
                ImageResource.favoriteFileName, ImageResource.favoriteRawResource);

        FileUtil.createFile(this,ImageResource.categoryDoorWcFolderPath,
                ImageResource.doorWcFileName, ImageResource.doorWcRawResource);
        FileUtil.createFile(this,ImageResource.categoryDoorBathRoomFolderPath,
                ImageResource.doorBathRoomFileName, ImageResource.doorBathRoomRawResource);
        FileUtil.createFile(this,ImageResource.categoryDoorBedRoomFolderPath,
                ImageResource.doorBedRoomFileName, ImageResource.doorBedRoomRawResource);
        FileUtil.createFile(this,ImageResource.categoryDoorHallFolderPath,
                ImageResource.doorHallFileName, ImageResource.doorHallRawResource);

        FileUtil.createFile(this,ImageResource.categoryFrameWcFolderPath,
                ImageResource.frameWcFileName, ImageResource.frameWcRawResource);
        FileUtil.createFile(this,ImageResource.categoryFrameBathRoomFolderPath,
                ImageResource.frameBathRoomFileName, ImageResource.frameBathRoomRawResource);
        FileUtil.createFile(this,ImageResource.categoryFrameBedRoomFolderPath,
                ImageResource.frameBedRoomFileName, ImageResource.frameBedRoomRawResource);
        FileUtil.createFile(this,ImageResource.categoryFrameHallFolderPath,
                ImageResource.frameHallFileName, ImageResource.frameHallRawResource);
    }
}
