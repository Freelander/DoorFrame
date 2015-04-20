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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.display.doorframe.R;
import com.display.doorframe.adapter.BigPicViewPagerAdapter;
import com.display.doorframe.adapter.GridViewAdapter;
import com.display.doorframe.adapter.ViewPagerAdapter;
import com.display.doorframe.utils.ZoomTutorial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 2015/4/15.
 *
 */
public class HotFragment extends Fragment {

    private ViewPager viewPager;
    private List<View> viewList;
    private TextView hotText,diyText;
    private LinearLayout hotTextBg,diyTextBg;
    private View view;
    private GridView gridView;

    private View pager1,pager2;

    public static Integer[] hotPicIds = {
            R.drawable.ic_hot_one,R.drawable.ic_hot_two,
            R.drawable.ic_hot_three,R.drawable.ic_hot_four,
            R.drawable.ic_hot_five,R.drawable.ic_hot_six,
            R.drawable.ic_hot_seven,R.drawable.ic_hot_eight,
            R.drawable.ic_hot_nine,R.drawable.ic_hot_ten
    };

    public static Integer[] hotLargePicIds = hotPicIds;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       //获取布局的填充者
        view = inflater.inflate(R.layout.fragment_hot,container,false);

        //初始化组件
        initView();

        //将两个个view动态地添加到Adapter对象中
        viewList = new ArrayList<>();

        pager1 = inflater.inflate(R.layout.hot_pager_layout, null);
        pager2 = inflater.inflate(R.layout.diy_page_layout,null);
        viewList.add(pager1);
        viewList.add(pager2);
        viewPager.setAdapter(new ViewPagerAdapter(viewList));
        //指向第一个页面setCurrentItem(0)
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPagerChangeListener());


        gridView = (GridView) pager1.findViewById(R.id.hot_gv);
        gridView.setAdapter(new GridViewAdapter(view.getContext(),hotPicIds,hotLargePicIds));

        //GridView Item 点击监听事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setViewPagerAndZoom(view,position);
            }
        });
        return view;
    }

    //图片放大处理
    public void setViewPagerAndZoom(View v ,int position) {
        //得到要放大展示的视图界面
        ViewPager expandedView = (ViewPager) pager1.findViewById(R.id.detail_view);
        //最外层的容器，用来计算
        View containerView = pager1.findViewById(R.id.container);
        //实现放大缩小类，传入当前的容器和要放大展示的对象
        ZoomTutorial mZoomTutorial = new ZoomTutorial(containerView, expandedView);

        BigPicViewPagerAdapter adapter = new BigPicViewPagerAdapter(pager1.getContext(),
                hotLargePicIds,mZoomTutorial);
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

    //完成View组件的初始化
    public void initView(){

        viewPager = (ViewPager) view.findViewById(R.id.vPager);
        hotText = (TextView) view.findViewById(R.id.tab_hot_text);
        diyText = (TextView) view.findViewById(R.id.tab_diy_text);
        hotTextBg = (LinearLayout) view.findViewById(R.id.tab_hot_bg);
        diyTextBg = (LinearLayout) view.findViewById(R.id.tab_diy_bg);



//        gridView = (GridView) view.findViewById(R.id.hot_gv);
//        gridView.setAdapter(new GridViewAdapter(view.getContext()));

        //给两个TabTitle设置点击事件
        hotText.setOnClickListener(new TabClickListener(0));
        diyText.setOnClickListener(new TabClickListener(1));
    }

    //ViewPager页面滑动监听事件
    public class ViewPagerChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //重置tab颜色
            resetView();

            switch(position){
                case 0:
                    hotTextBg.setBackgroundResource(R.color.tab_blue_text);
                    hotText.setTextColor(view.getResources().getColor(R.color.tab_white_text));
                    diyTextBg.setBackgroundResource(R.color.tab_white_text);
                    diyText.setTextColor(view.getResources().getColor(R.color.tab_blue_text));
                    break;
                case 1:
                    diyTextBg.setBackgroundResource(R.color.tab_blue_text);
                    diyText.setTextColor(view.getResources().getColor(R.color.tab_white_text));
                    hotTextBg.setBackgroundResource(R.color.tab_white_text);
                    hotText.setTextColor(view.getResources().getColor(R.color.tab_blue_text));
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        //重置tab颜色
        public void resetView(){
            diyTextBg.setBackgroundResource(R.color.tab_blue_text);
            diyText.setTextColor(view.getResources().getColor(R.color.tab_white_text));
            hotTextBg.setBackgroundResource(R.color.tab_white_text);
            hotText.setTextColor(view.getResources().getColor(R.color.tab_blue_text));
        }
    }

    //设置点击事件,点击上面文字切换页面的
    public class TabClickListener implements View.OnClickListener{

        private int index = 0;

        public TabClickListener(int index){
            this.index = index;
        }
        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }
    }

}
