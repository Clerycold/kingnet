package com.kingnet.JsonUtils;

import android.content.Context;
import android.os.AsyncTask;

import com.kingnet.GetShareMemory.SaveData;
import com.kingnet.ActivityHome.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by clery on 2016/11/28.
 */

public class GetUserCommunity extends AsyncTask<String,Integer,String> {

    private Context context;
    private SaveData saveData;

    public GetUserCommunity(Context context){
        this.context=context.getApplicationContext();
        saveData=new SaveData(this.context);
    }
    @Override
    protected String doInBackground(String... params) {
        String url;
        url=params[0];

        getUserCommunityInfo(url);

        return null;
    }

    private void getUserCommunityInfo(final String string) {

        if(MainActivity.VALIDCODE!=null){
            AsynNetUtils.post(string, "code="+MainActivity.VALIDCODE, new AsynNetUtils.Callback() {
                @Override
                public void onResponse(String response) {
                    parseDiffJson(response);
                }
            });
        }

    }


    private void parseDiffJson(String json) {

        String userId;
        String userCity;
        String UserCommunity;

        try{
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            userId=jsonObject.getString("id");
            userCity=jsonObject.getString("city");
            UserCommunity=jsonObject.getString("community");

            saveData.saveStringData("usercommunity","userId",userId);
            saveData.saveStringData("usercommunity","userCity",userCity);
            saveData.saveStringData("usercommunity","UserCommunity",UserCommunity);



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

