package com.example.textdemo.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.textdemo.JavaBean.User;
import com.example.textdemo.R;

import org.w3c.dom.Text;

import java.util.List;


/**
 * Created by 张金瑞 on 2017/5/16.
 */
public class ListAdapter extends BaseAdapter {
    private List<User> list;
    private Context context;

    public ListAdapter (List<User> list,Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.item,null);
            viewholder=new Viewholder();
            viewholder.jImg= (ImageView) convertView.findViewById(R.id.jImg);
            viewholder.jTv= (TextView) convertView.findViewById(R.id.jTv);
            viewholder.jTv_come= (TextView) convertView.findViewById(R.id.jTv_come);
            viewholder.jTv_date= (TextView) convertView.findViewById(R.id.jTv_date);
            convertView.setTag(viewholder);
        }else {
            viewholder= (Viewholder) convertView.getTag();
        }
        User user=list.get(position);
        viewholder.jTv.setText(user.getTitle());
        viewholder.jTv_date.setText(user.getSystem());
        viewholder.jTv_come.setText(user.getCome());
        Glide.with(context).load(user.getImg()).into(viewholder.jImg);

        return convertView;
    }
    static class Viewholder{
        private TextView jTv,jTv_come,jTv_date;
        private ImageView jImg;
    }
}
