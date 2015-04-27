package com.display.doorframe.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.display.doorframe.ui.widget.TouchImageView;
import com.display.doorframe.utils.ZoomTutorial;

/**
 * Created by Jun on 2015/4/18.
 */
public class BigPicViewPagerAdapter extends PagerAdapter {

    private String[] imagePath;
    private Context  mContext;
    private ZoomTutorial mZoomTutorial;

    public BigPicViewPagerAdapter(Context mContext, String[] imagePath, ZoomTutorial mZoomTutorial){
        this.mContext = mContext;
        this.imagePath = imagePath;
        this.mZoomTutorial = mZoomTutorial;
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {

        final TouchImageView imageView = new TouchImageView(mContext);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 4;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath[position],opts);
        imageView.setImageBitmap(bitmap);

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
        return imagePath.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
