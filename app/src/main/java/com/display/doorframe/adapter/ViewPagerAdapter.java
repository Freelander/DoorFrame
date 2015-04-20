package com.display.doorframe.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by Jun on 2015/4/16.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> mListView;

    public ViewPagerAdapter(){}

    public ViewPagerAdapter(List<View> mListView){
        this.mListView = mListView;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager)container).addView(mListView.get(position),0);
        return mListView.get(position);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView(mListView.get(position));
    }

    @Override
    public int getCount() {
        return mListView.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
