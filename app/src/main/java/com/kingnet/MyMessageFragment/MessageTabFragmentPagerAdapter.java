package com.kingnet.MyMessageFragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedList;

/**
 * Created by clery on 2016/11/8.
 */

public class MessageTabFragmentPagerAdapter extends FragmentPagerAdapter {

    LinkedList<MessageBaseFragment> fragments = null;

    public MessageTabFragmentPagerAdapter(FragmentManager fm, LinkedList<MessageBaseFragment> fragments) {
        super(fm);
        if (fragments == null) {
            this.fragments = new LinkedList<MessageBaseFragment>();
        }else{
            this.fragments = fragments;
        }
    }

    @Override
    public MessageBaseFragment getItem(int position) {
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