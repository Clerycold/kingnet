package com.kingnet.PackageFragment;

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
 * Created by Administrator on 2016/11/1.
 */
public class PackTabFragment extends Fragment {

    public static Fragment newInstance(){
        PackTabFragment f = new PackTabFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pack_frag_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //adapter
        final LinkedList<PackBaseFragment> fragments = getFragments();
        FragmentPagerAdapter adapter = new PackTabFragmentPagerAdapter(getFragmentManager(), fragments);
        //pager
        ViewPager pager = (ViewPager) view.findViewById(R.id.packpager);
        pager.setAdapter(adapter);
        //tabs
        PackSlidingTabLayout tabs = (PackSlidingTabLayout) view.findViewById(R.id.packtabs);
        tabs.setCustomTabColorizer(new PackSlidingTabLayout.TabColorizer() {
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
         * 指定tab背景顏色
         */

        tabs.setBackgroundResource(R.color.colorMain);
        tabs.setViewPager(pager);
    }

    private LinkedList<PackBaseFragment> getFragments(){
        int indicatorColor = getResources().getColor(R.color.colorYellow);
        int dividerColor = getResources().getColor(R.color.colorMain);

        LinkedList<PackBaseFragment> fragments = new LinkedList<PackBaseFragment>();
        fragments.add(PackageMainFragment.newInstance("未領取", indicatorColor, dividerColor));
        fragments.add(PackageGetFragment.newInstance("已領取", indicatorColor, dividerColor));
        fragments.add(PackageReturnFragment.newInstance("退貨狀態", indicatorColor, dividerColor));
        return fragments;
    }
}