package com.kingnet.PackageFragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/11/1.
 */
public class PackBaseFragment extends Fragment {

    private String title = "";
    private int indicatorColor = Color.BLUE;
    private int dividerColor = Color.GRAY;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getIndicatorColor() {
        return indicatorColor;
    }
    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
    }
    public int getDividerColor() {
        return dividerColor;
    }
    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
    }

}