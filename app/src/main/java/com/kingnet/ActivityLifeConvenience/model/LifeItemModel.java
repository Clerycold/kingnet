package com.kingnet.ActivityLifeConvenience.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clery on 2016/12/23.
 */

public class LifeItemModel implements LifeItem{

    List<String> ListTitle;
    List<String> ListContent;
    String[] title = new String[]{"加油站位置查詢","叫車服務-台灣大車隊"};
    String[] content = new String[]{"中國石油","叫車專線405-88888或手機直撥55688按"};

    @Override
    public List<String> getTitle(){

        ListTitle=new ArrayList<String>();
        addList(ListTitle,title);

        return ListTitle;
    }

    @Override
    public List<String> getContent(){

        ListContent=new ArrayList<String>();
        addList(ListContent,content);

        return ListContent;
    }

    private void addList(List<String> list,String[] string){
        for(int i=0,j=string.length;i<j;i++){
            list.add(string[i]);
        }
    }
}
