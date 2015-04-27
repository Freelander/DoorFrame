package com.display.doorframe.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.display.doorframe.R;
import com.display.doorframe.adapter.BigPicViewPagerAdapter;
import com.display.doorframe.adapter.GridViewAdapter;
import com.display.doorframe.data.ImageResource;
import com.display.doorframe.utils.ZoomTutorial;

/**
 * Created by Jun on 2015/4/15.
 */
public class CollectionFragment extends Fragment {

    private TextView hotText,diyText;
    private GridView gridView;
    private ViewPager deatilView;
    private View view;


    private String[] favoriteImageFolder = ImageResource.getImageFolder(ImageResource.favoriteFolderPath);

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_collection,container,false);

        initView();

        gridView.setAdapter(new GridViewAdapter(view.getContext(),favoriteImageFolder));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setViewPagerAndZoom(view,position);
            }
        });
        return view;
    }

    //初始化控件
    public void initView(){
        //hotText = (TextView) view.findViewById(R.id.hot_text);
        diyText = (TextView) view.findViewById(R.id.tab_diy_text);

        gridView = (GridView) view.findViewById(R.id.collection_gv);
        deatilView = (ViewPager) view.findViewById(R.id.detail_view);
    }

    //图片放大处理
    public void setViewPagerAndZoom(View v ,int position) {
        //得到要放大展示的视图界面
        ViewPager expandedView = (ViewPager) view.findViewById(R.id.detail_view);
        //最外层的容器，用来计算
        View containerView = view.findViewById(R.id.container);
        //实现放大缩小类，传入当前的容器和要放大展示的对象
        ZoomTutorial mZoomTutorial = new ZoomTutorial(containerView, expandedView);

        BigPicViewPagerAdapter adapter = new BigPicViewPagerAdapter(view.getContext(),
                favoriteImageFolder,mZoomTutorial);
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
