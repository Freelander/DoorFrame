package com.display.doorframe.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.display.doorframe.data.ImageResource;
import com.display.doorframe.utils.FileUtil;

/**
 * Created by Jun on 2015/5/8.
 */
public class EntryActivity extends Activity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("first_start",MODE_PRIVATE);

        boolean result = preferences.getBoolean("first_start",true);
        if(result){//第一启动是时将raw目录下的图片复制到sdcard相对应的目录下
            initResource();
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("first_start",false);//修改值为false
        editor.commit();//提交修改后的数据

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

        finish();
    }

    /**
     * 在SDCard上创建目录并复制raw目录下图片都相对应位置
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
