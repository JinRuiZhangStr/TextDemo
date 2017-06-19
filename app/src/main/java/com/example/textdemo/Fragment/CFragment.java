package com.example.textdemo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.textdemo.R;
import com.example.textdemo.SharaActivity;
import com.example.textdemo.Utils.MyApplication;
import com.example.textdemo.Utils.SharedUtils;

/**
 * Created by 张金瑞 on 2017/5/16.
 */
public class CFragment extends Fragment {
    private ImageView fImg;
    private Button fBtn;
    private SharedUtils sharedUtils;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.allfragment,null);
        sharedUtils= MyApplication.sharedUtils;
        initView(view);
        return view;
    }

    private void initView(View view) {
        fImg= (ImageView) view.findViewById(R.id.fImg);
        fBtn= (Button) view.findViewById(R.id.fBtn);
        fBtn.setVisibility(View.VISIBLE);
        fImg.setImageResource(R.mipmap.xiayu6);

        fBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SharaActivity.class);
                startActivity(intent);
                sharedUtils.saveShared_int("status",1,getActivity());
            }
        });
    }
}
