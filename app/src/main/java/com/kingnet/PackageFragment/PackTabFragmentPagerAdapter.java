package com.kingnet.PackageFragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kingnet.Newfragment.BaseFragment;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/11/1.
 */
public class PackTabFragmentPagerAdapter extends FragmentPagerAdapter {

    private LinkedList<PackBaseFragment> fragments = null;

    public PackTabFragmentPagerAdapter(FragmentManager fm, LinkedList<PackBaseFragment> fragments) {
        super(fm);
        if (fragments == null) {
            this.fragments = new LinkedList<PackBaseFragment>();
        }else{
            this.fragments = fragments;
        }
    }

    @Override
    public PackBaseFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }

}