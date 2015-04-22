package com.display.doorframe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.display.doorframe.R;

/**
 * Created by Jun on 2015/4/21.
 *
 */
public class HorizontalScrollViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;
    private Integer[] picIds;

    public HorizontalScrollViewAdapter(Context mContext, Integer[] picIds){
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.picIds = picIds;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.horizontalscrollview_item_layout,
                    parent,false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.hsv_img);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(picIds[position]);
        viewHolder.imageView.setTag(picIds[position]);

        return convertView;
    }

    public class ViewHolder{
        ImageView imageView;
    }
}
