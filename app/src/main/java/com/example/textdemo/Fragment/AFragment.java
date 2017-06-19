package com.example.textdemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.textdemo.R;

/**
 * Created by 张金瑞 on 2017/5/16.
 */
public class AFragment extends Fragment {
    private ImageView fImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.allfragment,null);
        fImg= (ImageView) view.findViewById(R.id.fImg);
        fImg.setImageResource(R.mipmap.xiayu4);
        return view;
    }
}
