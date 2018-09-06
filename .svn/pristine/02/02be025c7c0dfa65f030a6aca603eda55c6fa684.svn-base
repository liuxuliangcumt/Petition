package com.realpower.petition.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.realpower.petition.R;
import com.realpower.petition.adapter.SwipeMenuAdapter;
import com.realpower.petition.bean.AssociaterBean;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.StringIdParam;
import com.realpower.petition.net.result.AssociaterResult;
import com.realpower.petition.net.result.StringResult;
import com.realpower.petition.util.MyToastUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class JointerChooseActivity extends BaseActivity {
    @ViewById(R.id.RV_pointer)
    LRecyclerView RV_pointer;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private SwipeMenuAdapter mDataAdapter = null;
    private List<AssociaterBean> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jointer_choose);
    }

    @AfterViews
    void initViews() {
        setTitleName("选择联名人");
        setRightName("新建");
        dataList = new ArrayList<>();
        mDataAdapter = new SwipeMenuAdapter(this);
        mDataAdapter.setDataList(dataList);
        mDataAdapter.setOnDelListener(new SwipeMenuAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                //RecyclerView关于notifyItemRemoved的那点小事 参考：http://blog.csdn.net/jdsjlzx/article/details/52131528
                deleteAssociater(mDataAdapter.getDataList().get(pos).getId());
                mDataAdapter.getDataList().remove(pos);
                mDataAdapter.notifyItemRemoved(pos);//推荐用这个
                if (pos != (mDataAdapter.getDataList().size())) { // 如果移除的是最后一个，忽略 注意：这里的mDataAdapter.getDataList()不需要-1，因为上面已经-1了
                    mDataAdapter.notifyItemRangeChanged(pos, mDataAdapter.getDataList().size() - pos);
                }
                //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();

            }

        });
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mDataAdapter);
        RV_pointer.setAdapter(mLRecyclerViewAdapter);

        RV_pointer.setLayoutManager(new LinearLayoutManager(this));

        RV_pointer.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        RV_pointer.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        RV_pointer.setPullRefreshEnabled(false);
        RV_pointer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
              /*  mDataAdapter.clear();
                mLRecyclerViewAdapter.notifyDataSetChanged();//fix bug:crapped or attached views may not be recycled. isScrap:false isAttached:true
                mCurrentCounter = 0;
                isRefresh = true;
                requestData();*/
                RV_pointer.refreshComplete(10);
            }
        });
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.rv_divider_height)
                .setColorResource(R.color.ctouming)
                .build();
        RV_pointer.addItemDecoration(divider);


    }

    @Override
    protected void onResume() {
        super.onResume();
        getAssociate();
    }

    private void deleteAssociater(int id) {
        Call<StringResult> call = apiService.deleteAssociate(new StringIdParam(id + ""));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("2".equals(result.getDesc().getCode())) {
                    MyToastUtils.showToast(result.getDesc().getDescription());
                }
                Log.e("aaa", "删除联名人success  " + result.toString());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {
                Log.e("aaa", "删除联名人failure  " + t.toString());

            }
        });
    }

    private void getAssociate() {
        Call<AssociaterResult> call = apiService.getAssociate();
        call.enqueue(new MyCallback<AssociaterResult>() {
            @Override
            public void onSuccessRequest(AssociaterResult result) {
                if ("2".equals(result.getDesc().getCode())) {
                    mDataAdapter.clear();
                    mDataAdapter.addAll(result.getMessage());
                }
                Log.e("aaa", "获取联名人列表  " + result.toString());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<AssociaterResult> call, Throwable t) {
                Log.e("aaa", "获取联名人列表onFailureRequest  " + t.toString());

            }
        });
    }

    @Click({R.id.tv_right, R.id.btn_ok})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                JointerNewActivity_.intent(this).start();
                break;
            case R.id.btn_ok:
                chooseData();
                break;
        }
    }

    private void chooseData() {
        List<AssociaterBean> chosedData = new ArrayList<>();
        for (int i = 0; i < mDataAdapter.getDataList().size(); i++) {
            if (mDataAdapter.getDataList().get(i).isSelected()) {
                chosedData.add(mDataAdapter.getDataList().get(i));
            }
        }
        if (chosedData.size() != 0) {
            Intent intent = new Intent();
            // 获取用户计算后的结果
            Bundle bundle = new Bundle();
            bundle.putSerializable("choosedata", (Serializable) chosedData);
            intent.putExtras(bundle);
            //通过intent对象返回结果，必须要调用一个setResult方法，
            //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以，但是
            setResult(RESULT_OK, intent);
            Log.e("aaa", "choosedata  " + chosedData.size());
            finish();
        }

    }

}
