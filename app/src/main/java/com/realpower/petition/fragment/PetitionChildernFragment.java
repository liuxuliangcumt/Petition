package com.realpower.petition.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.realpower.petition.R;
import com.realpower.petition.activity.PetitionDetailActivity_;
import com.realpower.petition.activity.PetitionNewActivity_;
import com.realpower.petition.adapter.PetitionLAdapter;
import com.realpower.petition.bean.ShuquBean;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.IdParam;
import com.realpower.petition.net.result.ShuquResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by ruipu on 2018/8/24.
 */
@EFragment(R.layout.fragment_petition_childern)
public class PetitionChildernFragment extends BaseFragment {
    @ViewById(R.id.lv_petition)
    ListView lv_petition;
    PetitionLAdapter petitionLAdapter;
    private List<ShuquBean> datas;
    private int petitionCategory = 0;//诉求类别


    @AfterViews
    void onInitViews() {
        View headview = View.inflate(getContext(), R.layout.listview_headview, null);
        View booterView = View.inflate(getContext(), R.layout.listview_booterview, null);
        datas = new ArrayList<>();
        petitionLAdapter = new PetitionLAdapter(getContext(), datas);
        lv_petition.addHeaderView(headview);
        lv_petition.addFooterView(booterView);
        lv_petition.setAdapter(petitionLAdapter);

        lv_petition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PetitionDetailActivity_.intent(getActivity()).petitionId(datas.get(i - 1).getAppealId()).start();
            }
        });

        if (petitionCategory == 0) {

            getData();
        } else {
            searchPetition(petitionCategory);
        }


    }


    @Override
    public void onResume() {
        super.onResume();
        if (petitionCategory == 0) {
            getData();
        } else {
            searchPetition(petitionCategory);
        }

    }

    public void searchPetitionByCriteria(String criteria) {
        Call<ShuquResult> call = apiService.searchPetitionByCriteria(new IdParam("", criteria));
        call.enqueue(new MyCallback<ShuquResult>() {
            @Override
            public void onSuccessRequest(ShuquResult result) {
                if ("1".equals(result.getStatus()))
                    setData(result.getMessage());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<ShuquResult> call, Throwable t) {

            }
        });

    }


    private void searchPetition(int position) {
        Call<ShuquResult> call = apiService.searchPetitionByStatus(new IdParam("", position + ""));
        call.enqueue(new MyCallback<ShuquResult>() {
            @Override
            public void onSuccessRequest(ShuquResult result) {
                if ("1".equals(result.getStatus()))
                    setData(result.getMessage());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<ShuquResult> call, Throwable t) {

            }
        });
    }

    private void setData(List<ShuquBean> message) {
        datas.clear();
        datas.addAll(message);
        petitionLAdapter.notifyDataSetChanged();
    }

    public void getData() {
        Call<ShuquResult> call = apiService.shuquAll();
        call.enqueue(new MyCallback<ShuquResult>() {
            @Override
            public void onSuccessRequest(ShuquResult result) {
                // Log.e("aaa", "success  " + result.toString());
                if ("2".equals(result.getDesc().getCode())) {
                    setData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<ShuquResult> call, Throwable t) {
                Log.e("aaa", "failure  " + t.toString());

            }
        });
    }


    @Click({R.id.petition_btn})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.petition_btn:
                PetitionNewActivity_.intent(getContext()).start();
                break;
        }
    }

    public void setCategory(int category) {
        petitionCategory = category;

    }

}
