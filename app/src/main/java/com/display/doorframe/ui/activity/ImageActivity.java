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
import com.display.doorframe.utils.ZoomTutorial;

/**
 * Created by Jun on 2015/4/24.
 */
public class ImageActivity extends BaseActivity {

    private GridView gridView;

    private Integer[] doorWcPic = {
            R.drawable.ic_door,R.drawable.ic_door,
            R.drawable.ic_door,R.drawable.ic_door,
    };
    private Integer[] doorBathRoomPic = {
            R.drawable.ic_door_red,R.drawable.ic_door_red,
            R.drawable.ic_door_red,R.drawable.ic_door_red,
    };
    private Integer[] doorBedRoomPic = {
            R.drawable.ic_door_brown,R.drawable.ic_door_brown,
            R.drawable.ic_door_brown,R.drawable.ic_door_brown,
    };
    private Integer[] doorHallPic = {
            R.drawable.ic_door_red,R.drawable.ic_door_red,
            R.drawable.ic_door_red,R.drawable.ic_door_red,
    };

    private Integer[] frameWcPic = {
            R.drawable.ic_door_frame,R.drawable.ic_door_frame,
            R.drawable.ic_door_frame,R.drawable.ic_door_frame,
    };
    private Integer[] frameBathRoomPic = {
            R.drawable.ic_door_frame_yellow,R.drawable.ic_door_frame_yellow,
            R.drawable.ic_door_frame_yellow,R.drawable.ic_door_frame_yellow,
    };
    private Integer[] frameBedRoomPic = {
            R.drawable.ic_door_frame_red,R.drawable.ic_door_frame_red,
            R.drawable.ic_door_frame_red,R.drawable.ic_door_frame_red,
    };
    private Integer[] frameHallPic = {
            R.drawable.ic_door_frame_yellow,R.drawable.ic_door_frame_yellow,
            R.drawable.ic_door_frame_yellow,R.drawable.ic_door_frame_yellow,
    };

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
                    adapter = new GridViewAdapter(this,doorWcPic,doorWcPic);
                    gridView.setAdapter(adapter);
                    break;
                case "bathroom":
                    adapter = new GridViewAdapter(this,doorBathRoomPic,doorBathRoomPic);
                    gridView.setAdapter(adapter);
                    break;
                case "bedroom":
                    adapter = new GridViewAdapter(this,doorBedRoomPic,doorBedRoomPic);
                    gridView.setAdapter(adapter);
                    break;
                case "hall":
                    adapter = new GridViewAdapter(this,doorHallPic,doorHallPic);
                    gridView.setAdapter(adapter);
                    break;
            }
        }else{//门框
            switch (secondMenu){
                case "wc":
                    adapter = new GridViewAdapter(this,frameWcPic,frameWcPic);
                    gridView.setAdapter(adapter);
                    break;
                case "bathroom":
                    adapter = new GridViewAdapter(this,frameBathRoomPic,frameBathRoomPic);
                    gridView.setAdapter(adapter);
                    break;
                case "bedroom":
                    adapter = new GridViewAdapter(this,frameBedRoomPic,frameBedRoomPic);
                    gridView.setAdapter(adapter);
                    break;
                case "hall":
                    adapter = new GridViewAdapter(this,frameHallPic,frameHallPic);
                    gridView.setAdapter(adapter);
                    break;
            }

        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setViewPagerAndZoom(view,position);
            }
        });
    }

    //图片放大处理
    public void setViewPagerAndZoom(View v ,int position) {
        //得到要放大展示的视图界面
        ViewPager expandedView = (ViewPager) this.findViewById(R.id.detail_view);
        //最外层的容器，用来计算
        View containerView = this.findViewById(R.id.container);
        //实现放大缩小类，传入当前的容器和要放大展示的对象
        ZoomTutorial mZoomTutorial = new ZoomTutorial(containerView, expandedView);

        BigPicViewPagerAdapter adapter = new BigPicViewPagerAdapter(this,
                doorWcPic,mZoomTutorial);
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
