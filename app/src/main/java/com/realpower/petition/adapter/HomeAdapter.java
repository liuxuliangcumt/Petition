package com.realpower.petition.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.realpower.petition.R;
import com.realpower.petition.bean.NewsBean;
import com.realpower.petition.net.Mate;
import com.realpower.petition.util.GlideRoundTransform;
import com.realpower.petition.util.GlideRoundedCornersTransformation;
import com.realpower.petition.util.ShadowDrawable;
import com.realpower.petition.util.ShadowDrawableTest;
import com.realpower.petition.util.UIUtils;

import java.util.List;

/**
 * Created by ruipu on 2018/8/28.
 */

public class HomeAdapter extends MyBaseAdapter<NewsBean.ListBean> {
    public HomeAdapter(Context context, List<NewsBean.ListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {

            convertView = View.inflate(context, R.layout.item_listview_home, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.iv_icon = convertView.findViewById(R.id.iv_icon);
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_content = convertView.findViewById(R.id.tv_content);
            holder.ll_bottom = convertView.findViewById(R.id.ll_bottom);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ShadowDrawable.setShadowDrawable(holder.ll_bottom,
                Color.parseColor("#ffffff"), UIUtils.dpToPx(20),
                Color.parseColor("#75f8f8f8"),
                1, 0, UIUtils.dpToPx(10));

        RequestOptions options = new RequestOptions();
        options.transform(new GlideRoundedCornersTransformation());
        Glide.with(context).load("http://a0.att.hudong.com/54/49/01300542891809141879496908378.jpg")
                .apply(options)
                .into(holder.iv_icon);
        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_content.setText(data.get(position).getContent());
        return convertView;
    }

    private class ViewHolder {
        ImageView iv_icon;
        TextView tv_title, tv_content;
        LinearLayout ll_bottom;
    }


}
