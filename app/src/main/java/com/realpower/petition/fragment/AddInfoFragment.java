package com.realpower.petition.fragment;

import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.realpower.petition.R;
import com.realpower.petition.bean.OtherInfoBean;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.UpdateOtherInfoParam;
import com.realpower.petition.net.result.OtherInfoResult;
import com.realpower.petition.net.result.StringResult;
import com.realpower.petition.util.MyToastUtils;
import com.realpower.petition.views.ClearEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;


@EFragment(R.layout.fragment_addinfo)
public class AddInfoFragment extends BaseFragment {

    @ViewById
    ClearEditText et_nation;

    @ViewById
    ClearEditText et_job;

    @ViewById
    ClearEditText et_tel;

    @ViewById
    ClearEditText et_postaladdress;

    @ViewById
    ClearEditText et_postalcode;
    @ViewById
    ClearEditText et_email;
    @ViewById
    ClearEditText et_workplace;
    @ViewById
    AppCompatButton btn_ok;

    @Click({R.id.btn_ok})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                updateOtherInfo();
                break;
        }
    }

    private void updateOtherInfo() {
        Call<StringResult> call = apiService.pdateOtherInfo(new UpdateOtherInfoParam(
                et_email.getText().toString(), et_job.getText().toString(),
                et_nation.getText().toString(), et_postaladdress.getText().toString(),
                et_postalcode.getText().toString(), et_tel.getText().toString(), et_workplace.getText().toString()));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("2".equals(result.getDesc().getCode())) {
                    btn_ok.setClickable(false);
                    MyToastUtils.showToast(result.getDesc().getDescription());
                }else {
                    MyToastUtils.showToast("修改失败");
                }

            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {

            }
        });
    }

    @AfterViews
    void initViews() {
        getdata();
    }

    private void getdata() {
        Call<OtherInfoResult> call = apiService.getOtherInfo();
        call.enqueue(new MyCallback<OtherInfoResult>() {
            @Override
            public void onSuccessRequest(OtherInfoResult result) {
                if ("2".equals(result.getDesc().getCode())) {
                    setData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<OtherInfoResult> call, Throwable t) {

            }
        });
    }

    private void setData(OtherInfoBean message) {
        et_email.setText(message.getEmail());
        et_job.setText(message.getJob());
        et_nation.setText(message.getNation());
        et_postaladdress.setText(message.getPostaladdress());
        et_postalcode.setText(message.getPostalcode());
        et_workplace.setText(message.getWorkplace());
        et_tel.setText(message.getTel());
    }

}
