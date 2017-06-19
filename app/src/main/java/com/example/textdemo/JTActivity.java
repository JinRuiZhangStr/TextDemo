package com.example.textdemo;

import android.os.Handler;
import android.os.Message;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.textdemo.Adapter.ListAdapter;
import com.example.textdemo.JavaBean.User;
import com.example.textdemo.Utils.JSONUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class JTActivity extends AppCompatActivity {
    private ListView jLv;
    private String uri="http://v.juhe.cn/toutiao/index?type=top&key=76598a468913e78079e1c6d05439a817";
    private List<User> list;
    private ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jt);
        initView();
    }

    private void initView() {
        jLv= (ListView) findViewById(R.id.jLv);
        thread();
    }
    private void thread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(uri);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    InputStream in=conn.getInputStream();

                    BufferedReader br=new BufferedReader(new InputStreamReader(in));
                    String len;
                    StringBuffer sb=new StringBuffer();
                    while ((len=br.readLine())!=null){
                        sb.append(len);
                    }
                    Message msg=handler.obtainMessage();
                    msg.what=0;
                    msg.obj=sb.toString();
                    handler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){
                String s= (String) msg.obj;
                list= JSONUtils.JsonData(s);
                adapter=new ListAdapter(list,JTActivity.this);
                jLv.setAdapter(adapter);
            }
        }
    };
}
