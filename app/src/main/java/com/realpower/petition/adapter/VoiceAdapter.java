package com.realpower.petition.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.luck.picture.lib.entity.LocalMedia;
import com.realpower.petition.R;
import com.realpower.petition.bean.MyLocalMedia;
import com.realpower.petition.bean.VoiceBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 */

public class VoiceAdapter extends BaseAdapter {
    private Context context;
    private List<MyLocalMedia> data;
    public boolean isDetail = false;

    public VoiceAdapter(Context context, List<MyLocalMedia> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_voice, null);
            holder = new ViewHolder();
            holder.iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (isDetail) {
            holder.iv_delete.setVisibility(View.GONE);
        } else {
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView iv_delete, iv_voice;


    }

    public void setOnDeleteClickLisnter(OnDeleteClickLisnter lisnter) {
        onDeleteClickLisnter = lisnter;
    }

    private OnDeleteClickLisnter onDeleteClickLisnter;

    public interface OnDeleteClickLisnter {
        void onDeleteClick(int position);
    }
}
