package com.realpower.petition.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.realpower.petition.R;
import com.realpower.petition.adapter.RegulationAdapter;
import com.realpower.petition.bean.NoticeBean;
import com.realpower.petition.bean.RegulationBean;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.PagingParam;
import com.realpower.petition.net.result.RegulationResult;
import com.realpower.petition.util.BeanPropertiesUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class RegulationActivity extends BaseActivity {

    @ViewById
    ListView lv_Regulation;

    RegulationAdapter adapter;
    List<RegulationBean.ListBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulation);
        setTitleName("政策法规");
        data = new ArrayList<>();
        adapter = new RegulationAdapter(this, data);
        lv_Regulation.setAdapter(adapter);
        getData();
        lv_Regulation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoticeBean.ListBean bean = new NoticeBean.ListBean();
                try {
                    BeanPropertiesUtil.copyProperties(data.get(i), bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                NoticeDetailActivity_.intent(RegulationActivity.this).noticeBean(bean).start();
            }
        });
    }

    private void getData() {
        Call<RegulationResult> call = apiService.regulationPage(new PagingParam("1"));
        call.enqueue(new MyCallback<RegulationResult>() {
            @Override
            public void onSuccessRequest(RegulationResult result) {
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
            public void onFailureRequest(Call<RegulationResult> call, Throwable t) {

            }
        });
    }
}