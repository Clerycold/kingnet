package com.kingnet.Newfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingnet.R;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/10/27.
 */
public class TabFragment extends Fragment {

    private SlidingTabLayout tabs;
    private ViewPager pager;
    private FragmentPagerAdapter adapter;

    public static Fragment newInstance(){
        TabFragment f = new TabFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_frag_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //adapter
        final LinkedList<BaseFragment> fragments = getFragments();
        adapter = new TabFragmentPagerAdapter(getFragmentManager(), fragments);
        //pager
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        //tabs
        tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return fragments.get(position).getIndicatorColor();
            }

            @Override
            public int getDividerColor(int position) {
                return fragments.get(position).getDividerColor();
            }
        });
        /**
         * tabs背景色
         */
        tabs.setBackgroundResource(R.color.colorwhite);
        tabs.setViewPager(pager);
    }

    private LinkedList<BaseFragment> getFragments(){
        /**
         * indicatorColor 是底線指示器的顏色
         * 分隔線的顏色也可以指定，這裡設為透明。
         */
        int indicatorColor = getResources().getColor(R.color.colorMain);
        int dividerColor = Color.TRANSPARENT;

        LinkedList<BaseFragment> fragments = new LinkedList<BaseFragment>();
        fragments.add(BarcodeFtagment.newInstance(getResources().getString(R.string.barcodetext), indicatorColor, dividerColor));
        fragments.add(QrcodeFragment.newInstance(getResources().getString(R.string.qrcodetext), indicatorColor, dividerColor));

        return fragments;
    }
}