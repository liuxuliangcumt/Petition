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
 * 新建述求 选择述求类型 父级类型
 */

public class TypeParentAdapter extends MyBaseAdapter<PetitionType> {


    public TypeParentAdapter(Context context, List<PetitionType> data) {
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
    public void setData(List<PetitionType> data) {
        Collections.sort(data, new Comparator<PetitionType>() {
            public int compare(PetitionType arg0, PetitionType arg1) {
                return (arg0.getId().compareTo(arg1.getId()));
            }
        });
        super.setData(data);
    }
}
