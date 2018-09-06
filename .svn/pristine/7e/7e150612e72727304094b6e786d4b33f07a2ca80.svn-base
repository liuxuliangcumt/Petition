package com.realpower.petition.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.realpower.petition.R;
import com.realpower.petition.bean.ShuquBean;
import com.realpower.petition.bean.SuggestionBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */

public class SuggestionLAdapter extends BaseAdapter {
    private Context context;
    private List<SuggestionBean> data;

    public SuggestionLAdapter(Context context, List<SuggestionBean> data) {
        this.context = context;
        this.data = data;
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_petition, null);
            holder = new ViewHolder();
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.iv_state = (ImageView) convertView.findViewById(R.id.iv_state);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_content.setText(data.get(position).getSuggestionInfo());
        holder.tv_id.setText("ID:" + data.get(position).getSuggestionId() + "");
        holder.tv_title.setText(data.get(position).getSuggestionTitle());
        holder.tv_time.setText(data.get(position).getCreatetime());
        switch (data.get(position).getCurrentStatus()) {
            case 1:
                holder.iv_state.setImageDrawable(context.getResources().getDrawable(R.mipmap.to_be_solved));
                break;
            case 2:
                holder.iv_state.setImageDrawable(context.getResources().getDrawable(R.mipmap.solving_icon));
                break;
            case 3:
                holder.iv_state.setImageDrawable(context.getResources().getDrawable(R.mipmap.resolved_icon));
                break;
        }


        return convertView;
    }

    private class ViewHolder {
        TextView tv_time, tv_id, tv_content, tv_title;
        ImageView iv_state;
    }
}
