package com.kingnet.ActivityZoo;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.widget.ListView;

import com.kingnet.ActivityZoo.model.ZooDataModel;
import com.kingnet.ActivityZoo.presenter.ZooDataPresenterCompl;
import com.kingnet.ActivityZoo.view.ZooDataView;
import com.kingnet.ExpandableList.ZooListAdapter;
import com.kingnet.R;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by clery on 2016/12/12.
 */

public class ZooActivity extends Activity implements ZooDataView{

    private ZooListAdapter listviewadapter;
    ZooDataPresenterCompl zooDataPresenterCompl;

    ArrayList<ZooDataModel> zooDataList = new ArrayList<ZooDataModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.kingnet.R.layout.activity_zoo);

        initView();

        if(Build.VERSION.SDK_INT >= M){
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        200);
                return;}
            zooDataPresenterCompl=new ZooDataPresenterCompl(getApplicationContext(),this,zooDataList);
        }else{
            zooDataPresenterCompl=new ZooDataPresenterCompl(getApplicationContext(),this,zooDataList);
        }
        /**
         * 從Android 3.0以後，如果在onCreate要使用網路的話，必須要先授權，所以只要在你的onCreate內，執行程式碼以上加入以下代碼即可！
         */
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
    private void initView(){
        ListView listView = (ListView) findViewById(R.id.listView);
        listviewadapter = new ZooListAdapter(getApplicationContext(),zooDataList, listView);
        listView.setAdapter(listviewadapter);
    }

    @Override
    public void ZooDataShow() {
        listviewadapter.notifyDataSetChanged();
    }
}
