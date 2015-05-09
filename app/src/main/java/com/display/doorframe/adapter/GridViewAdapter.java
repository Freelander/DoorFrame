package com.display.doorframe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.display.doorframe.R;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Jun on 2015/4/16.
 *
 */
public class GridViewAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;

    private Context mContext;

    private Integer[] picIds, largePicIds;

    private String[] imagePath;//图片路径

    public GridViewAdapter(Context mContext,String[] imagePath){
        this.mContext = mContext;
        this.imagePath = imagePath;
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
        return imagePath.length;
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
            //设置ImageView宽高
            imageView.setLayoutParams(new GridView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 200));

        }else{
            imageView = (ImageView)convertView;
        }

//        imageView.setImageResource(picIds[position]);
//        imageView.setTag(picIds[position]);

//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inSampleSize = 4;//图片宽高都为原来的二分之一，即图片为原来的四分之一
//        Bitmap bitmap = BitmapFactory.decodeFile(imagePath[position],opts);
//        imageView.setImageBitmap(bitmap);
//        imageView.setTag(bitmap);
        Picasso picasso = Picasso.with(mContext);
        picasso.setLoggingEnabled(true);//打开日志，即log中会打印出目前下载的进度、情况
        /**
         * 在加载的图片左上角显示一个 三角形
         * 红色：代表从网络下载的图片
         * 黄色：代表从磁盘缓存加载的图片
         * 绿色：代表从内存中加载的图片
         */
//        picasso.setDebugging(true);
        picasso.load(new File(imagePath[position]))//图片加载的路径
                .placeholder(R.drawable.ic_default)//当图片正在加载时显示的图片(optional)
                .error(R.drawable.ic_default)//将图片下载完后放进view中
                .resize(400, 200)
                .centerCrop()
                .into(imageView);//将图片下载完后放进view中

        return imageView;

    }

}
