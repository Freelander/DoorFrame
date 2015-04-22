package com.display.doorframe.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.display.doorframe.R;
import com.display.doorframe.utils.ZoomTutorial;

/**
 * Created by Jun on 2015/4/16.
 *
 */
public class GridViewAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;

    private Context mContext;

    private Integer[] picIds, largePicIds;

//    private Integer[] mDrawableId={
//            R.drawable.ic_one,R.drawable.ic_two,
//            R.drawable.ic_three,R.drawable.ic_four,
//            R.drawable.ic_five,R.drawable.ic_six,
//            R.drawable.ic_seven,R.drawable.ic_eight,
//            R.drawable.ic_nine,R.drawable.ic_ten,
//
//    };

    public GridViewAdapter(Context mContext){
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }
    public GridViewAdapter(Context mContext, Integer[] picIds, Integer[] largePicIds){
        this.mContext = mContext;
        this.picIds = picIds;
        this.largePicIds = largePicIds;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return picIds.length;
    }

    @Override
    public Object getItem(int position) {
        return picIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ImageView imageView;

        if(convertView == null){
            imageView = new ImageView(mContext);
            // 这里的scaleType是CENTER_CROP
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ////设置ImageView宽高
            imageView.setLayoutParams(new GridView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 200));

        }else{
            imageView = (ImageView)convertView;

        }

        imageView.setImageResource(picIds[position]);
        imageView.setTag(picIds[position]);
//        imageView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                //当前drawable的res的id
//                int id = (Integer) v.getTag();
//                setViewPagerAndZoom(imageView, id);
//            }
//        });
        return imageView;

    }



    public void setViewPagerAndZoom(View v ,int position) {
        //得到要放大展示的视图界面
        ViewPager expandedView = (ViewPager)((FragmentActivity)mContext).findViewById(R.id.detail_view);
        //最外层的容器，用来计算
        View containerView = ((Activity)mContext).findViewById(R.id.container);
        //实现放大缩小类，传入当前的容器和要放大展示的对象
        ZoomTutorial mZoomTutorial = new ZoomTutorial(containerView, expandedView);

        BigPicViewPagerAdapter adapter = new BigPicViewPagerAdapter(mContext,
                largePicIds,mZoomTutorial);
        expandedView.setAdapter(adapter);
        expandedView.setCurrentItem(position);

        // 通过传入Id来从小图片扩展到大图，开始执行动画
        mZoomTutorial.zoomImageFromThumb(v);
        mZoomTutorial.setOnZoomListener(new ZoomTutorial.OnZoomListener() {

            @Override
            public void onThumbed() {
                // TODO 自动生成的方法存根
                Log.i("DoorFrame","现在是-------------------> 小图状态");
            }

            @Override
            public void onExpanded() {
                // TODO 自动生成的方法存根
                Log.i("DoorFrame","现在是-------------------> 大图状态");
            }
        });
    }
}
