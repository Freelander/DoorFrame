package com.display.doorframe.ui.fragment;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.display.doorframe.R;
import com.display.doorframe.adapter.BigPicViewPagerAdapter;
import com.display.doorframe.adapter.GridViewAdapter;
import com.display.doorframe.adapter.HorizontalScrollViewAdapter;
import com.display.doorframe.adapter.ViewPagerAdapter;
import com.display.doorframe.data.ImageResource;
import com.display.doorframe.ui.widget.MyHorizontalScrollView;
import com.display.doorframe.utils.FileUtil;
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

    private MyHorizontalScrollView doorHorizontalScrollView,doorFrameHorizontalScrollView;
    private HorizontalScrollViewAdapter horizontalScrollViewAdapter,horizontalScrollViewAdapter1;
    private ImageView doorImage,doorFrameImage,collectionIamge;
    private Integer[] doorPicIds = {
            R.drawable.ic_door,R.drawable.ic_door_brown,
            R.drawable.ic_door_red,R.drawable.ic_door,
            R.drawable.ic_door_brown,R.drawable.ic_door_brown,
            R.drawable.ic_door_brown,R.drawable.ic_door_brown,
            R.drawable.ic_door_brown,R.drawable.ic_door_brown,
            R.drawable.ic_door_brown,R.drawable.ic_door_brown,
            R.drawable.ic_door_red,R.drawable.ic_door_red,
            R.drawable.ic_door_red,R.drawable.ic_door_red,
            R.drawable.ic_door_red,R.drawable.ic_door_red,
            R.drawable.ic_door,R.drawable.ic_door,
            R.drawable.ic_door,R.drawable.ic_door,
            R.drawable.ic_door,R.drawable.ic_door

    };
    private Integer[] doorFramePicIds = {
            R.drawable.ic_door_frame,R.drawable.ic_door_frame_red,
            R.drawable.ic_door_frame_yellow,R.drawable.ic_door_frame,
            R.drawable.ic_door_frame_red,R.drawable.ic_door_frame_red,
            R.drawable.ic_door_frame_red,R.drawable.ic_door_frame_red,
            R.drawable.ic_door_frame_red,R.drawable.ic_door_frame_red,
            R.drawable.ic_door_frame_yellow,R.drawable.ic_door_frame_yellow,
            R.drawable.ic_door_frame_yellow,R.drawable.ic_door_frame_yellow,
            R.drawable.ic_door_frame_yellow,R.drawable.ic_door_frame_yellow,
            R.drawable.ic_door_frame,R.drawable.ic_door_frame,
            R.drawable.ic_door_frame,R.drawable.ic_door_frame,
            R.drawable.ic_door_frame,R.drawable.ic_door_frame,
            R.drawable.ic_door_frame,R.drawable.ic_door_frame

    };

    private View pager1,pager2;

    //获取所有热门图片在sdcard位置路径名称
    private String[] hotImageFolder = ImageResource.getImageFolder(ImageResource.hotFolderPath);

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
        gridView.setAdapter(new GridViewAdapter(view.getContext(),hotImageFolder));

        //GridView Item 点击监听事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setViewPagerAndZoom(view,position);
            }
        });


        doorImage = (ImageView) pager2.findViewById(R.id.door_img);
        doorImage.setImageResource(R.drawable.ic_door);
        doorFrameImage = (ImageView) pager2.findViewById(R.id.door_frame_img);

        doorFrameHorizontalScrollView = (MyHorizontalScrollView) pager2.findViewById(R.id.horizontalScrollView_top);
        doorHorizontalScrollView = (MyHorizontalScrollView) pager2.findViewById(R.id.horizontalScrollView_bottom);

        horizontalScrollViewAdapter = new HorizontalScrollViewAdapter(view.getContext(),doorFramePicIds);
        doorFrameHorizontalScrollView.initDatas(horizontalScrollViewAdapter);
        horizontalScrollViewAdapter1 = new HorizontalScrollViewAdapter(view.getContext(),doorPicIds);
        doorHorizontalScrollView.initDatas(horizontalScrollViewAdapter1);
        MyHorizontalScrollViewListener();

        collectionIamge = (ImageView) pager2.findViewById(R.id.collection_btn);
        collectionIamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable doorDrawable = doorImage.getDrawable();
                Drawable frameDrawable = doorFrameImage.getDrawable();
                Bitmap doorBitmap = ((BitmapDrawable)doorDrawable).getBitmap();
                if(frameDrawable == null){//如果用户没有选择门框
                    Toast.makeText(view.getContext(),"请选择门框",Toast.LENGTH_SHORT).show();
                    return;//跳出
                }
                Bitmap frameBitmap = ((BitmapDrawable)frameDrawable).getBitmap();
                boolean result = FileUtil.saveBitmap(FileUtil.toConformBitmap(doorBitmap,frameBitmap),
                        ImageResource.favoriteFolderPath);
                if(result){//收藏成功
                    collectionIamge.setImageResource(R.drawable.ic_fa_star_o_1);
                }else{
                    Toast.makeText(view.getContext(),"收藏失败",Toast.LENGTH_SHORT).show();
                }
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
                hotImageFolder,mZoomTutorial);
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

    //MyHorizontalScroollView 监听事件
    public void MyHorizontalScrollViewListener(){


        //滚动回调
        doorFrameHorizontalScrollView.setCurrentImageChangeListener(new MyHorizontalScrollView.CurrentImageChangeListener() {
            @Override
            public void onCurrentImgChanged(int position, View viewIndicator) {
//                doorFrameImage.setImageResource(doorFramePicIds[position]);
//                doorFrameImage.setBackgroundColor(Color.TRANSPARENT);//设置图片为透明的
            }
        });

        doorHorizontalScrollView.setCurrentImageChangeListener(new MyHorizontalScrollView.CurrentImageChangeListener() {
            @Override
            public void onCurrentImgChanged(int position, View viewIndicator) {
//                doorImage.setImageResource(doorPicIds[position]);
            }
        });

        //点击回调
        doorFrameHorizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                collectionIamge.setImageResource(R.drawable.ic_fa_star_o);
                doorFrameImage.setImageResource(doorFramePicIds[position]);
                Toast.makeText(view.getContext(),"点击门框"+position+"",Toast.LENGTH_SHORT).show();
            }
        });


        doorHorizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                collectionIamge.setImageResource(R.drawable.ic_fa_star_o);
                doorImage.setImageResource(doorPicIds[position]);
                Toast.makeText(view.getContext(),"点击门"+position+"",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
