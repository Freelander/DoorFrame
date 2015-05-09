package com.display.doorframe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.display.doorframe.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Jun on 2015/5/6.
 */
public class ImageSelectorGridAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;

    private String[] imagePath;//图片路径
    public static ArrayList<String> mSelectorImage;//用户选择的图片路径

    public ImageSelectorGridAdapter(Context mContext,String[] imagePath){
        this.mContext = mContext;
        this.imagePath = imagePath;
        this.inflater = LayoutInflater.from(mContext);
        mSelectorImage = new ArrayList<>();
    }

    public static ArrayList<String> getArrayList(){

        return mSelectorImage;
    }

    @Override
    public int getCount() {
        return imagePath.length;
    }

    @Override
    public Object getItem(int position) {
        return imagePath[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder ;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item_image,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inSampleSize = 4;//图片宽高都为原来的二分之一，即图片为原来的四分之一
//        Bitmap bitmap = BitmapFactory.decodeFile(imagePath[position],opts);
//        viewHolder.image.setImageBitmap(bitmap);
//        viewHolder.image.setTag(bitmap);

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
                .into(viewHolder.image);//将图片下载完后放进view中

        viewHolder.indicator.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                viewHolder.indicator.setVisibility(View.VISIBLE);
                if (mSelectorImage.contains(imagePath[position])) {//已选择的图片
                    viewHolder.indicator.setImageResource(R.drawable.btn_unselected);
                    mSelectorImage.remove(imagePath[position]);
                    viewHolder.msk.setVisibility(View.GONE);
                    notifyDataSetChanged();
                }else{
                    viewHolder.indicator.setImageResource(R.drawable.btn_selected);
                    mSelectorImage.add(imagePath[position]);
                    viewHolder.msk.setVisibility(View.VISIBLE);
                    /**
                     * 调用notifyDataSetChanged来更新GridView填充的数据
                     */
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }


    class ViewHolder{
        ImageView image;
        ImageView indicator;
        View msk;

        ViewHolder(View view){
            image = (ImageView) view.findViewById(R.id.image);
            indicator = (ImageView) view.findViewById(R.id.checkmark);
            msk = view.findViewById(R.id.mask);
        }

    }
}
