package com.display.doorframe.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.display.doorframe.utils.ZoomTutorial;

/**
 * Created by Jun on 2015/4/18.
 */
public class BigPicViewPagerAdapter extends PagerAdapter {

    private Integer[] largePicIds;
    private Context  mContext;
    private ZoomTutorial mZoomTutorial;

    public BigPicViewPagerAdapter(Context mContext, Integer[] largePicIds, ZoomTutorial mZoomTutorial){
        this.mContext = mContext;
        this.largePicIds = largePicIds;
        this.mZoomTutorial = mZoomTutorial;
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        final ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(largePicIds[position]);
        //imageView.setScaleType(ImageView.ScaleType.MATRIX);
        //imageView.setOnTouchListener(new MulitPointTouchListener());
        container.addView(imageView,
                ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.MATCH_PARENT
        );


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mZoomTutorial.closeZoomAnim(position);
            }
        });

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return largePicIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
