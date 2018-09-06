package com.realpower.petition.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.realpower.petition.R;
import com.realpower.petition.bean.PetitionType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */

public class TypeChildrenAdapter extends MyBaseAdapter<PetitionType.ChildrenBean> {

    public TypeChildrenAdapter(Context context, List<PetitionType.ChildrenBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_type, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(data.get(position).getName());
        return convertView;
    }

    private class ViewHolder {
        TextView tv_name;
    }

    @Override
    public void setData(List<PetitionType.ChildrenBean> data) {
        Collections.sort(data, new Comparator<PetitionType.ChildrenBean>() {
            public int compare(PetitionType.ChildrenBean arg0, PetitionType.ChildrenBean arg1) {
                return (arg0.getId().compareTo(arg1.getId()));
            }
        });
        super.setData(data);
    }
}
