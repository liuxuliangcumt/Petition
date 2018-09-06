package com.realpower.petition.adapter;

import android.content.Context;
import android.widget.TextView;

import com.realpower.petition.R;
import com.realpower.petition.base.ListBaseAdapter;
import com.realpower.petition.base.SuperViewHolder;
import com.realpower.petition.bean.PetitionBean;

import java.util.List;


/**
 * Created by Lzx on 2016/12/30.
 */

public class PetitionRVAdapter extends ListBaseAdapter<PetitionBean> {

    public PetitionRVAdapter(Context context, List<PetitionBean> data) {
        super(context);
        setDataList(data);
    }

    @Override
    public int getLayoutId(){
        return R.layout.item_listview_petition;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        PetitionBean item = mDataList.get(position);
        TextView titleText = holder.getView(R.id.tv_title);
        TextView content = holder.getView(R.id.tv_content);
        titleText.setText(item.name + position);
        content.setText(item.content);
    }
}
