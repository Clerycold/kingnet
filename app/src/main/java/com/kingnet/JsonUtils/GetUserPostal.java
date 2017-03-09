package com.kingnet.JsonUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.kingnet.Data.PostListData;
import com.kingnet.GetShareMemory.SaveData;
import com.kingnet.ActivityHome.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by clery on 2016/11/28.
 */

public class GetUserPostal extends AsyncTask<String,Integer,String> {

    private Context context;
    private SaveData saveData;
    private static List<PostListData>postListDatas;

    public GetUserPostal(Context context){
        this.context=context.getApplicationContext();
        saveData=new SaveData(this.context);
    }

    @Override
    protected String doInBackground(String... params) {

        String url;
        url=params[0];

        getUserPostal(url);

        return null;
    }

    private void getUserPostal(String string){

        String usercode;
        String userId;

        usercode=MainActivity.VALIDCODE;
        userId=MainActivity.USERID;

        try {
            String stringBuffer = URLEncoder.encode("code", "UTF-8") +
                    "=" +
                    URLEncoder.encode(usercode, "UTF-8") +
                    "&" +
                    URLEncoder.encode("com_id", "UTF-8") +
                    "=" +
                    URLEncoder.encode(userId, "UTF-8");

            Log.d("----stringBuffer",stringBuffer);

            AsynNetUtils.post(string, stringBuffer, new AsynNetUtils.Callback() {
                @Override
                public void onResponse(String response) {
                    parseDiffJson(response);
                }
            });

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    private void parseDiffJson(String json) {

        PostListData postListData;

        try{
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject;
            if(jsonArray.length()!=0){
                postListDatas=new ArrayList<PostListData>();
                for(int i = 0,j=jsonArray.length();i <= j;i++){
                    jsonObject = (JSONObject) jsonArray.get(i);
                    postListData=new PostListData();
                    postListData.setA(jsonObject.getString("a"));
                    postListData.setB(jsonObject.getString("b"));
                    postListData.setC(jsonObject.getString("c"));
                    postListData.setD(context,jsonObject.getString("d"));
                    postListData.setE(jsonObject.getString("e"));
                    postListData.setF(jsonObject.getString("f"));
                    postListData.setL(jsonObject.getString("l"));
                    postListData.setI(jsonObject.getString("i"));
                    postListData.setM(jsonObject.getString("m"));
                    postListData.setN(jsonObject.getString("n"));
                    postListData.setO(jsonObject.getString("o"));
                    postListData.setP(jsonObject.getString("p"));
                    postListData.setQ(jsonObject.getString("q"));
                    postListDatas.add(postListData);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static List<PostListData> getPostListDatas(){
        return postListDatas;
    }

}
