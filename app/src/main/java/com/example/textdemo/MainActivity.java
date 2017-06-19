package com.example.textdemo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.textdemo.Adapter.ViewPagerAdapter;
import com.example.textdemo.Fragment.AFragment;
import com.example.textdemo.Fragment.BFragment;
import com.example.textdemo.Fragment.CFragment;
import com.example.textdemo.Utils.MyApplication;
import com.example.textdemo.Utils.SharedUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager mVp;
    private ViewPagerAdapter vPadapter;
    private AFragment af;
    private BFragment bf;
    private CFragment cf;
    private List<Fragment> list;
    private FragmentManager fm;
    private SharedUtils sharedUtils;
    private int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initList();
    }

    private void initList() {
        list=new ArrayList<>();
        af=new AFragment();
        bf=new BFragment();
        cf=new CFragment();
        list.add(af);
        list.add(bf);
        list.add(cf);
        fm=getSupportFragmentManager();
        vPadapter=new ViewPagerAdapter(fm,list);
        mVp.setAdapter(vPadapter);
    }

    private void initView() {
        mVp= (ViewPager) findViewById(R.id.mVp);
        sharedUtils= MyApplication.sharedUtils;
        status=sharedUtils.getShared_int("status",this);
        if (status==1){
            Intent intent=new Intent(this,SharaActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
