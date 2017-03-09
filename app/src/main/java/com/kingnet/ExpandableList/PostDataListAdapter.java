package com.kingnet.ExpandableList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.ScreenWH;
import com.kingnet.Control.TextUtils;
import com.kingnet.Data.PostListData;
import com.kingnet.R;
import com.kingnet.UIpattern.PostDitalTxt;
import com.kingnet.UIpattern.PostDoubleImg;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.kingnet.R.string.post_d1;

/**
 * Created by clery on 2016/11/28.
 */

public class PostDataListAdapter extends BaseAdapter {

    private Context context;
    private List<PostListData> postListDatas;
    private LayoutInflater infalInflater;

    public PostDataListAdapter(Context context,List postListDatas){
        this.context=context;
        this.postListDatas=postListDatas;
        infalInflater=(LayoutInflater) context.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return (postListDatas!=null ? postListDatas.size():0);
    }

    @Override
    public Object getItem(int position) {
        return postListDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        String yearData=postListDatas.get(position).getB();
        StringBuffer postType=new StringBuffer("類型：");
        postType.append(postListDatas.get(position).getC());
        String posttypestuff=postListDatas.get(position).getD();

        StringBuffer userAdress=new StringBuffer("姓名：");
        userAdress.append(postListDatas.get(position).getL());

        String postSource =postListDatas.get(position).getN();
        String postTrace =postListDatas.get(position).getO();
        String txtExpla =postListDatas.get(position).getQ();

        if (convertView == null) {

            convertView=infalInflater.inflate(R.layout.postdatalistadapter,null);
            holder=new ViewHolder();

            holder.postrelatbg = (RelativeLayout)convertView.findViewById(R.id.postrelatbg);
            holder.postDoubleImg = (PostDoubleImg)convertView.findViewById(R.id.postdoubleimg);
            holder.postDitalTxt = (PostDitalTxt)convertView.findViewById(R.id.postdital);

            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }

        final int HeightSY=ScreenWH.getUISpacingY();

        RelativeLayout.LayoutParams post_img_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        post_img_lay.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        post_img_lay.addRule(RelativeLayout.CENTER_VERTICAL);
        post_img_lay.setMargins(HeightSY*2,HeightSY*2,HeightSY*2,HeightSY*2);

        if(!"".equals(postSource)){
            int resID = context.getResources().getIdentifier(postSource , "drawable", context.getPackageName());
            if(postSource.equals("n2")|postSource.equals("n11")|postSource.equals("n8")){
                holder.postDoubleImg.setImageViewUpImg(resID,0);
            }else{
                holder.postDoubleImg.setImageViewUpImg(resID,1);
            }
        }

        holder.postDoubleImg.setLayoutParams(post_img_lay);

        RelativeLayout.LayoutParams postDitalTxt_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        postDitalTxt_lay.addRule(RelativeLayout.RIGHT_OF,holder.postDoubleImg.getId());
        postDitalTxt_lay.setMargins(0,HeightSY,0,HeightSY);
        postDitalTxt_lay.addRule(RelativeLayout.CENTER_VERTICAL);

        if(!"".equals(postTrace)){
            int resID = context.getResources().getIdentifier(postTrace , "drawable", context.getPackageName());
            if(postTrace.equals("o11")){
                holder.postDitalTxt.setPostTraceImg(resID,0);
            }else{
                holder.postDitalTxt.setPostTraceImg(resID,1);
            }
        }

        if(!"".equals(posttypestuff)){
            if(posttypestuff.equals(context.getResources().getString(R.string.post_d1))){
                holder.postDitalTxt.setPosttypestuffTxt(context.getResources().getString(R.string.post_d1));
            }else if(posttypestuff.equals(context.getResources().getString(R.string.post_d2))){
                holder.postDitalTxt.setPosttypestuffTxt(context.getResources().getString(R.string.post_d2));
            }
        }

        if(!"".equals(txtExpla)){
            holder.postDitalTxt.setExplanationTxt(txtExpla);
        }

        holder.postDitalTxt.setYearTxt(yearData);
        holder.postDitalTxt.setAdressTxt(userAdress);
        holder.postDitalTxt.setTypeTxt(postType);
        holder.postDitalTxt.setPadding(0,HeightSY,0,HeightSY);
        holder.postDitalTxt.setLayoutParams(postDitalTxt_lay);

        return convertView;
    }
    static class ViewHolder{

        RelativeLayout postrelatbg;
        PostDoubleImg postDoubleImg;
        PostDitalTxt postDitalTxt;

    }
}
