package com.realpower.petition.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.realpower.petition.R;
import com.realpower.petition.adapter.NoticeAdapter;
import com.realpower.petition.bean.NoticeBean;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.PagingParam;
import com.realpower.petition.net.result.NoticeResult;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class LegalaidActivity extends BaseActivity {
    @ViewById
    ListView lv_legalaid;
    NoticeAdapter adapter;
    List<NoticeBean.ListBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legalaid);
        data = new ArrayList<>();
        adapter = new NoticeAdapter(this, data);
        lv_legalaid.setAdapter(adapter);
        setTitleName("法律援助");
        lv_legalaid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoticeDetailActivity_.intent(LegalaidActivity.this).noticeBean(data.get(i)).start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    void getData() {
        Call<NoticeResult> call = apiService.legalaidPage(new PagingParam("1"));
        call.enqueue(new MyCallback<NoticeResult>() {
            @Override
            public void onSuccessRequest(NoticeResult result) {
                if ("1".equals(result.getStatus())) {
                    data.clear();
                    data.addAll(result.getMessage().getList());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<NoticeResult> call, Throwable t) {

            }
        });
    }
}
