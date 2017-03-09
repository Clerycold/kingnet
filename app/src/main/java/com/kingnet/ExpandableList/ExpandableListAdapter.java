package com.kingnet.ExpandableList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/28.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private int[] listIv; // 標題圖示
    private List<String> listDataHeader; // 標題
    private HashMap<String, List<String>> listDataChild; // 內容

    DisplayMetrics Metrics = Resources.getSystem().getDisplayMetrics();
    float Width= Metrics.widthPixels;
    float Height=Metrics.heightPixels;

    /**
     * @param listIv
     *            -標題圖示
     * @param listDataHeader
     *            -標題
     * @param listChildData
     *            -內容
     */
    public ExpandableListAdapter(Context context, int[] listIv,
                                 List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this.context = context;
        this.listIv = listIv;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
    }

    /* -------------------- 標題 -------------------- */
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }
    public int getGroupCount() {
        return this.listDataHeader.size();
    }
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    /* -------------------- 內容 -------------------- */
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosititon);
    }
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .size();
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    /* 標題View */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition); // 取得標題
  /* 設置標題layout */
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
        /* 設置標題 */
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);

        RelativeLayout.LayoutParams open_img_lay=new RelativeLayout.LayoutParams((int) (double) Width,(int) (double) Height/10);
        open_img_lay.addRule(RelativeLayout.CENTER_VERTICAL);
        open_img_lay.setMargins(25,0,25,0);
        lblListHeader.setLayoutParams(open_img_lay);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    /* 內容View */
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition); // 取得內容

  /* 設置內容layout */
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

  /* 設置內容 */
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);

        /**
         * 子目錄的內容編輯
         */
        RelativeLayout.LayoutParams open_text_lay=new RelativeLayout.LayoutParams((int) (double) Width,(int) (double) (Height/5));
        open_text_lay.setMargins(25,0,25,0);
        txtListChild.setLayoutParams(open_text_lay);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
