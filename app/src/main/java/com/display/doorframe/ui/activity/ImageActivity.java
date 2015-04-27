package com.display.doorframe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.display.doorframe.R;
import com.display.doorframe.adapter.BigPicViewPagerAdapter;
import com.display.doorframe.adapter.GridViewAdapter;
import com.display.doorframe.data.ImageResource;
import com.display.doorframe.utils.ZoomTutorial;

/**
 * Created by Jun on 2015/4/24.
 */
public class ImageActivity extends BaseActivity {

    private GridView gridView;


    private String[] doorWcFolder = ImageResource.
            getImageFolder(ImageResource.categoryDoorWcFolderPath);
    private String[] doorBathRoomFolder = ImageResource.
            getImageFolder(ImageResource.categoryDoorBathRoomFolderPath);
    private String[] doorBedRoomFolder = ImageResource.
            getImageFolder(ImageResource.categoryDoorBedRoomFolderPath);
    private String[] doorHallFolder = ImageResource.
            getImageFolder(ImageResource.categoryDoorHallFolderPath);

    private String[] frameWcFolder = ImageResource.
            getImageFolder(ImageResource.categoryFrameWcFolderPath);
    private String[] frameBathRoomFolder = ImageResource.
            getImageFolder(ImageResource.categoryFrameBathRoomFolderPath);
    private String[] frameBedRoomFolder = ImageResource.
            getImageFolder(ImageResource.categoryFrameBedRoomFolderPath);
    private String[] frameHallFolder = ImageResource.
            getImageFolder(ImageResource.categoryFrameHallFolderPath);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_img_show_layout);

        gridView = (GridView) findViewById(R.id.category_gv);

        Intent intent =getIntent();
        String mainMenu = intent.getStringExtra("mainMenu");
        String secondMenu = intent.getStringExtra("secondMenu");

        GridViewAdapter adapter ;
        if(mainMenu.equals("door")){//门
            switch (secondMenu){
                case "wc":
                    initToolbar("厕所门");
                    adapter = new GridViewAdapter(this,doorWcFolder);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            setViewPagerAndZoom(view,position,doorWcFolder);
                        }
                    });
                    break;
                case "bathroom":
                    initToolbar("浴室门");
                    adapter = new GridViewAdapter(this,doorBathRoomFolder);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            setViewPagerAndZoom(view,position,doorBathRoomFolder);
                        }
                    });
                    break;
                case "bedroom":
                    initToolbar("卧室门");
                    adapter = new GridViewAdapter(this,doorBedRoomFolder);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            setViewPagerAndZoom(view,position,doorBedRoomFolder);
                        }
                    });
                    break;
                case "hall":
                    initToolbar("大厅门");
                    adapter = new GridViewAdapter(this,doorHallFolder);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            setViewPagerAndZoom(view,position,doorHallFolder);
                        }
                    });
                    break;
            }
        }else{//门框
            switch (secondMenu){
                case "wc":
                    initToolbar("厕所门框");
                    adapter = new GridViewAdapter(this,frameWcFolder);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            setViewPagerAndZoom(view,position,frameWcFolder);
                        }
                    });
                    break;
                case "bathroom":
                    initToolbar("浴室门框");
                    adapter = new GridViewAdapter(this,frameBathRoomFolder);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            setViewPagerAndZoom(view,position,frameBathRoomFolder);
                        }
                    });
                    break;
                case "bedroom":
                    initToolbar("卧室门框");
                    adapter = new GridViewAdapter(this,frameBedRoomFolder);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            setViewPagerAndZoom(view,position,frameBedRoomFolder);
                        }
                    });
                    break;
                case "hall":
                    initToolbar("大厅门框");
                    adapter = new GridViewAdapter(this,frameHallFolder);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            setViewPagerAndZoom(view,position,frameBedRoomFolder);
                        }
                    });
                    break;
            }

        }

    }

//    //图片放大处理
    public void setViewPagerAndZoom(View v ,int position, String[] imagePath) {
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
                Log.i("DoorFrame", "现在是-------------------> 大图状态");
            }
        });
    }
}
