package com.kingnet.PackageFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.kingnet.Control.ScreenWH;
import com.kingnet.Data.PostListData;
import com.kingnet.ExpandableList.PostDataListAdapter;
import com.kingnet.JsonUtils.GetUserPostal;
import com.kingnet.R;
import com.kingnet.UIpattern.ButtonImgText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */
public class PackageGetFragment extends PackBaseFragment {
    private View rootView;

    private ListView postListView;
    private List<PostListData> postListDatas;
    private List<PostListData> postListDatas2;
    private ButtonImgText packimagebg;

    private int Width;
    private int Height;


    public static PackageGetFragment newInstance(String title, int indicatorColor, int dividerColor) {
        PackageGetFragment f = new PackageGetFragment();
        f.setTitle(title);
        f.setIndicatorColor(indicatorColor);
        f.setDividerColor(dividerColor);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Width = ScreenWH.getScreenWidth();
        Height = ScreenWH.getScreenHidth();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         * 先用同一個 如果後來版面不同在做修改
         */
        rootView = inflater.inflate(R.layout.pack_frag_common, container, false);

        initView();
        xmlArrange();

        postListDatas= GetUserPostal.getPostListDatas();
        PostListData postListData=new PostListData();
        PostListData postListData2=new PostListData();
        postListDatas=new ArrayList<PostListData>();
        postListData.setA("232423423");
        postListData.setB("七分鐘之前");
        postListData.setC("包裹");
        postListData.setD(getContext(),"2");
        postListData.setE("到底");
        postListData.setF("0");
        postListData.setL("((9H)220號9F-1)");
        postListData.setI("1");
        postListData.setM("");
        postListData.setN("2");
        postListData.setO("2");
        postListData.setP("");
        postListData.setQ("");

        postListData2.setA("232423423");
        postListData2.setB("七分鐘之前");
        postListData2.setC("包裹");
        postListData2.setD(getContext(),"1");
        postListData2.setE("剛剛");
        postListData2.setF("0");
        postListData2.setL("((9H)220號9F-1)");
        postListData2.setI("2");
        postListData2.setM("");
        postListData2.setN("1");
        postListData2.setO("1");
        postListData2.setP("");
        postListData2.setQ("123132");
        postListDatas.add(postListData);
        postListDatas.add(postListData2);

        postListDatas2=selectionList(postListDatas);

        if (postListDatas != null ) {
            PostDataListAdapter PostListGetData = new PostDataListAdapter(getContext(), postListDatas2);
            postListView.setAdapter(PostListGetData);
        }else{
            packimagebg.setImageView(R.drawable.iconpackage, Width / 4, Width / 4);
            packimagebg.setTextView("目前尚無資料");
        }


        return rootView;
    }

    private void initView() {

        postListView = (ListView) rootView.findViewById(R.id.postList);
        packimagebg = (ButtonImgText) rootView.findViewById(R.id.packimagebg);
        postListView.setEmptyView(packimagebg);

    }

    private void xmlArrange() {

        RelativeLayout.LayoutParams pack_postList_lay = new RelativeLayout.LayoutParams((int) (double) Width, ViewGroup.LayoutParams.MATCH_PARENT);
        postListView.setLayoutParams(pack_postList_lay);

        RelativeLayout.LayoutParams pack_imagebg_lay = new RelativeLayout.LayoutParams((int) (double) (Width / 3), (int) (double) (Width / 3));
        pack_imagebg_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        pack_imagebg_lay.addRule(RelativeLayout.CENTER_VERTICAL);
        packimagebg.setLayoutParams(pack_imagebg_lay);

    }

    /**
     * 分類資料 已領取 未領取
     * @param list
     * @return
     */
    private List selectionList(List<PostListData> list){
        postListDatas2=new ArrayList<>();

        for(int i=0,j=list.size();i<j;i++){
            if(list.get(i).getI().equals("2")){
                postListDatas2.add(list.get(i));
            }
        }
        return postListDatas2;
    }
}