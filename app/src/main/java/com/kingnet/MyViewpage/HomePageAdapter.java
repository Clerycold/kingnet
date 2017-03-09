package com.kingnet.MyViewpage;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class HomePageAdapter extends PagerAdapter {
    private List<ViewGroup> listViews;
    public int position;

    public HomePageAdapter(List<ViewGroup> listViews) {
        this.listViews = listViews;
    }

    @Override
    public int getCount() {
        return listViews == null ? 0 : listViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup imageView = listViews.get(position);
        container.addView(imageView);
        this.position=position;
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(listViews.get(position));
    }
    public int getPosition(){
        return this.position;
    }
}