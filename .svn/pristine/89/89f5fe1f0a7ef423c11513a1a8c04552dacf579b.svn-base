package com.realpower.petition.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.realpower.petition.R;
import com.realpower.petition.bean.NoticeBean;

import java.util.List;
/**
 * Created by ruipu on 2018/7/12.
 */

public class NoticeAdapter extends MyBaseAdapter<NoticeBean.ListBean> {
    public NoticeAdapter(Context context, List<NoticeBean.ListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_listview_notice, null);
            holder = new ViewHolder();
            holder.titile = (TextView) view.findViewById(R.id.tv_title);
            holder.tv_content= (TextView) view.findViewById(R.id.tv_content);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.titile.setText(data.get(i).getTitle());
        holder.tv_content.setText(data.get(i).getContent());


        return view;
    }

    private class ViewHolder {
        TextView titile, tv_content;
    }
}
