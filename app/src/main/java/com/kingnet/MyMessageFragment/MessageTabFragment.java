package com.kingnet.MyMessageFragment;

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
 * Created by clery on 2016/11/8.
 */

public class MessageTabFragment extends Fragment {

    private MessageSlidingTabLayout tabs;
    private ViewPager pager;
    private FragmentPagerAdapter adapter;

    public static Fragment newInstance(){
        MessageTabFragment f = new MessageTabFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_frag_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //adapter
        final LinkedList<MessageBaseFragment> fragments = getFragments();
        adapter = new MessageTabFragmentPagerAdapter(getFragmentManager(), fragments);
        //pager
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        //tabs
        tabs = (MessageSlidingTabLayout) view.findViewById(R.id.tabs);
        tabs.setCustomTabColorizer(new MessageSlidingTabLayout.TabColorizer() {

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

    private LinkedList<MessageBaseFragment> getFragments(){
        int indicatorColor = getResources().getColor(R.color.colorYellow);
        int dividerColor = getResources().getColor(R.color.colorMain);

        LinkedList<MessageBaseFragment> fragments = new LinkedList<MessageBaseFragment>();
        fragments.add(MessagecommunityFrag.newInstance("社區訊息", indicatorColor, dividerColor));
        fragments.add(MessageSystemFrag.newInstance("系統訊息", indicatorColor, dividerColor));
        fragments.add(MessageGoodFrag.newInstance("好康消息", indicatorColor, dividerColor));
        return fragments;
    }
}