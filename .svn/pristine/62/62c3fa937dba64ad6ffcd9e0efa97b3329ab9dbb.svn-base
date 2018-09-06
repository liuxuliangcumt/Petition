package com.realpower.petition.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.realpower.petition.R;
import com.realpower.petition.bean.RegulationBean;

import java.util.List;

/**
 */

public class RegulationAdapter extends MyBaseAdapter<RegulationBean.ListBean> {
    public RegulationAdapter(Context context, List<RegulationBean.ListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_listview_regulation, null);
            holder = new ViewHolder();
            holder.tv_content = (TextView) view.findViewById(R.id.tv_content);
            holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_title.setText(data.get(i).getTitle());
        holder.tv_content.setText(data.get(i).getContent());
        return view;
    }

    private class ViewHolder {
        TextView tv_title, tv_content;
    }
}
