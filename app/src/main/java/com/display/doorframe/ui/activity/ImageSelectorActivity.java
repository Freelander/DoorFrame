package com.display.doorframe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.display.doorframe.R;
import com.display.doorframe.adapter.BigPicViewPagerAdapter;
import com.display.doorframe.adapter.ImageSelectorGridAdapter;
import com.display.doorframe.data.ImageResource;
import com.display.doorframe.utils.FileUtil;
import com.display.doorframe.utils.ZoomTutorial;

import java.util.ArrayList;

/**
 * Created by Jun on 2015/5/6.
 */
public class ImageSelectorActivity extends BaseActivity  {

    private ImageView actionBackImage;
    private TextView title;
    private Button deleteBtn;
    private GridView imageSelectorGv;
    private ImageSelectorGridAdapter adapter;

    private ArrayList<String> mSelectorImage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selector);

        initView();

        Intent intent = getIntent();
        String settingName = intent.getStringExtra("settings_name");
        switch(settingName){
            case "hotSetting":
                title.setText("热门区设置");
                adapter = new ImageSelectorGridAdapter(ImageSelectorActivity.this,
                        ImageResource.getImageFolder(ImageResource.hotFolderPath));
                imageSelectorGv.setAdapter(adapter);
                /**
                 * 删除按钮
                 */
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSelectorImage = ImageSelectorGridAdapter.getArrayList();
                        if(mSelectorImage.size() == 0){
                            Toast.makeText(ImageSelectorActivity.this, "请选择要删除的图片",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            boolean result = FileUtil.deleteImageFile(mSelectorImage);
                            if(result){
                                //重新绑定适配器
                                imageSelectorGv.setAdapter(new ImageSelectorGridAdapter(
                                        ImageSelectorActivity.this,
                                        ImageResource.getImageFolder((ImageResource.hotFolderPath))
                                ));

                            }else{
                                Toast.makeText(ImageSelectorActivity.this,"删除失败",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                /**
                 * 点击监听事件
                 */
                imageSelectorGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        setViewPagerAndZoom(view,position,
                                ImageResource.getImageFolder(
                                        (ImageResource.hotFolderPath)));
                    }
                });
                break;
            case "collectionSetting":
                title.setText("收藏区设置");
                adapter = new ImageSelectorGridAdapter(ImageSelectorActivity.this,
                        ImageResource.getImageFolder(ImageResource.favoriteFolderPath));
                imageSelectorGv.setAdapter(adapter);
                /**
                 * 删除按钮
                 */
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSelectorImage = ImageSelectorGridAdapter.getArrayList();
                        if(mSelectorImage.size() == 0){
                            Toast.makeText(ImageSelectorActivity.this, "请选择要删除的图片",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            boolean result = FileUtil.deleteImageFile(mSelectorImage);
                            if(result){
                                imageSelectorGv.setAdapter(new ImageSelectorGridAdapter(
                                        ImageSelectorActivity.this,
                                        ImageResource.getImageFolder((ImageResource.favoriteFolderPath))
                                ));
                            }else{
                                Toast.makeText(ImageSelectorActivity.this,"删除失败",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                /**
                 * 点击监听事件
                 */
                imageSelectorGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        setViewPagerAndZoom(view,position,
                                ImageResource.getImageFolder(
                                        (ImageResource.favoriteFolderPath)));
                    }
                });
                break;
//            case "frame":
//                adapter = new ImageSelectorGridAdapter(ImageSelectorActivity.this,
//                        ImageResource.getImageFolder(ImageResource.door));
//                imageSelectorGv.setAdapter(adapter);
//                /**
//                 * 删除按钮
//                 */
//                deleteBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mSelectorImage = ImageSelectorGridAdapter.getArrayList();
//                        if(mSelectorImage == null){
//                            Toast.makeText(ImageSelectorActivity.this, "请选择要删除的图片",
//                                    Toast.LENGTH_SHORT).show();
//                        }else{
//                            boolean result = FileUtil.deleteImageFile(mSelectorImage);
//                            if(result){
//                                imageSelectorGv.setAdapter(new ImageSelectorGridAdapter(
//                                        ImageSelectorActivity.this,
//                                        ImageResource.getImageFolder((ImageResource.favoriteFolderPath))
//                                ));
//                            }else{
//                                Toast.makeText(ImageSelectorActivity.this,"删除失败",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });
//                break;
//            case "door":
//                adapter = new ImageSelectorGridAdapter(ImageSelectorActivity.this,
//                        ImageResource.getImageFolder(ImageResource.favoriteFolderPath));
//                imageSelectorGv.setAdapter(adapter);
//                /**
//                 * 删除按钮
//                 */
//                deleteBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mSelectorImage = ImageSelectorGridAdapter.getArrayList();
//                        if(mSelectorImage == null){
//                            Toast.makeText(ImageSelectorActivity.this, "请选择要删除的图片",
//                                    Toast.LENGTH_SHORT).show();
//                        }else{
//                            boolean result = FileUtil.deleteImageFile(mSelectorImage);
//                            if(result){
//                                imageSelectorGv.setAdapter(new ImageSelectorGridAdapter(
//                                        ImageSelectorActivity.this,
//                                        ImageResource.getImageFolder((ImageResource.favoriteFolderPath))
//                                ));
//                            }else{
//                                Toast.makeText(ImageSelectorActivity.this,"删除失败",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });
//                break;

        }





        /**
         * 返回按钮
         */
        actionBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    /**
     * 初始化控件
     */
    public void initView(){
        actionBackImage = (ImageView) findViewById(R.id.action_back);
        title = (TextView) findViewById(R.id.action_bar_title);
        deleteBtn = (Button) findViewById(R.id.action_delete_btn);
        imageSelectorGv = (GridView) findViewById(R.id.gv_image_selector);
    }

    /**
     * 图片放大处理
     */
    public void setViewPagerAndZoom(View v ,int position,String[] imagePath) {
        //得到要放大展示的视图界面
        ViewPager expandedView = (ViewPager) this.findViewById(R.id.detail_view);
        //最外层的容器，用来计算
        View containerView = this.findViewById(R.id.container);
        //实现放大缩小类，传入当前的容器和要放大展示的对象
        ZoomTutorial mZoomTutorial = new ZoomTutorial(containerView, expandedView);

        BigPicViewPagerAdapter adapter = new BigPicViewPagerAdapter(this,
                imagePath,mZoomTutorial);
        expandedView.setAdapter(adapter);
        expandedView.setCurrentItem(position);

        // 通过传入Id来从小图片扩展到大图，开始执行动画
        mZoomTutorial.zoomImageFromThumb(v);
        mZoomTutorial.setOnZoomListener(new ZoomTutorial.OnZoomListener() {

            @Override
            public void onThumbed() {
                // TODO 自动生成的方法存根
                Log.i("DoorFrame", "现在是-------------------> 小图状态");
            }

            @Override
            public void onExpanded() {
                // TODO 自动生成的方法存根
                Log.i("DoorFrame","现在是-------------------> 大图状态");
            }
        });
    }
}
