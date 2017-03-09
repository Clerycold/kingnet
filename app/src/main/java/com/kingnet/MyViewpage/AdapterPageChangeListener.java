package com.kingnet.MyViewpage;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/10/26.
 */
public class AdapterPageChangeListener implements ViewPager.OnPageChangeListener{

    private int preDotPosition =0;
    private PagerAdapter pagerAdapter=null;
    private ViewGroup viewGroup=null;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        // 取余后的索引，得到新的page的索引
        int newPostion = position % pagerAdapter.getCount();
        // 根据索引设置图片的描述
        // 把上一个点设置为被选中
        viewGroup.getChildAt(preDotPosition).setEnabled(false);
        // 根据索引设置那个点被选中
        viewGroup.getChildAt(newPostion).setEnabled(true);
        // 新索引赋值给上一个索引的位置
        preDotPosition = newPostion;

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setPagerAdapter(PagerAdapter pagerAdapter){
        this.pagerAdapter=pagerAdapter;
    }
    public void setViewGroup(ViewGroup viewGroup){
        this.viewGroup=viewGroup;
    }
}
