package com.example.textdemo.Utils;

import com.example.textdemo.JavaBean.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张金瑞 on 2017/5/16.
 */
public class JSONUtils {
    public static List<User> JsonData(String s){
        List<User> list=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(s);
            JSONObject result=jsonObject.optJSONObject("result");
            JSONArray data=result.optJSONArray("data");

            for (int i=0;i<data.length();i++){
                JSONObject jo=data.optJSONObject(i);
                String title=jo.optString("title");
                String date=jo.optString("date");
                String come=jo.optString("author_name");
                String img=jo.getString("thumbnail_pic_s");

                User user=new User();
                user.setCome(come);
                user.setImg(img);
                user.setSystem(date);
                user.setTitle(title);
                list.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
