package com.realpower.petition.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.realpower.petition.R;
import com.realpower.petition.base.ListBaseAdapter;
import com.realpower.petition.base.SuperViewHolder;
import com.realpower.petition.bean.AssociaterBean;
import com.realpower.petition.util.MyToastUtils;
import com.realpower.petition.views.SwipeMenuView;


public class SwipeMenuAdapter extends ListBaseAdapter<AssociaterBean> {

    public SwipeMenuAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_list_swipe;
    }

    @Override
    public void onBindItemHolder(final SuperViewHolder holder, final int position) {
        View contentView = holder.getView(R.id.swipe_content);
        TextView title = holder.getView(R.id.title);
        Button btnDelete = holder.getView(R.id.btnDelete);
        CheckBox cb_associa = holder.getView(R.id.cb_associa);
       /* Button btnUnRead = holder.getView(R.id.btnUnRead);
        Button btnTop = holder.getView(R.id.btnTop);*/

        //这句话关掉IOS阻塞式交互效果 并依次打开左滑右滑
        ((SwipeMenuView) holder.itemView).setIos(true).setLeftSwipe(true);

        cb_associa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getDataList().get(position).setSelected(isChecked);
            }
        });
        cb_associa.setChecked(getDataList().get(position).isSelected());
        title.setText(getDataList().get(position).getName());
        //隐藏控件
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSwipeListener) {
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //((CstSwipeDelMenu) holder.itemView).quickClose();
                    mOnSwipeListener.onDel(position);
                }
                ((SwipeMenuView) holder.itemView).quickClose();
            }
        });
        //注意事项，设置item点击，不能对整个holder.itemView设置咯，只能对第一个子View，即原来的content设置，这算是局限性吧。
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   MyToastUtils.showToast(getDataList().get(position));
                //AppToast.makeShortToast(mContext, getDataList().get(position).title);
                Log.d("TAG", "onClick() called with: v = [" + v + "]");
            }
        });

    }

    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDel(int pos);
    }

    private onSwipeListener mOnSwipeListener;

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }


}

