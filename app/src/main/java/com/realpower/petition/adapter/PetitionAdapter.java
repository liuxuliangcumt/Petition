package com.realpower.petition.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.realpower.petition.R;

import java.util.List;


/**
 * Created by Administrator on 2017/11/9.
 */

public class PetitionAdapter extends BaseAdapter {
    private List<String> data;
    private Context context;

    public PetitionAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_petition, null);
            holder = new ViewHolder();
            holder.iv_state = (ImageView) convertView.findViewById(R.id.iv_state);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText("这是标题");
        holder.tv_content.setText("这是内容");
        holder.tv_time.setText("这是时间");
        return convertView;
    }

    private class ViewHolder {
        ImageView iv_state;
        TextView tv_title, tv_content, tv_time;
    }
}
