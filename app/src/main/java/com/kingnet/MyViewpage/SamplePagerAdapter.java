package com.kingnet.MyViewpage;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/25.
 */
public class SamplePagerAdapter extends PagerAdapter {

    private ArrayList<ViewGroup> pageList= new ArrayList<ViewGroup>();
    public int position;

    @Override
    public int getCount() {
        return pageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView((View) pageList.get(position));
        this.position=position;
        return pageList.get(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    public void addPagelist(ViewGroup pageView){
        pageList.add(pageView);

    }
    public int getPosition(){
        return this.position;
    }
}