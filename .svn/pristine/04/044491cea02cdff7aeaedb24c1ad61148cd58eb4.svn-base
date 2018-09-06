package com.realpower.petition.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.realpower.petition.R;
import com.realpower.petition.bean.BaseInfoBean;
import com.realpower.petition.bean.OtherInfoBean;
import com.realpower.petition.net.Mate;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.UpdateOtherInfoParam;
import com.realpower.petition.net.result.BaseInfoResult;
import com.realpower.petition.net.result.OtherInfoResult;
import com.realpower.petition.net.result.StringResult;
import com.realpower.petition.net.result.UrlResult;
import com.realpower.petition.util.GlideCircleTransform;
import com.realpower.petition.util.MyToastUtils;
import com.realpower.petition.views.ClearEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity
public class ResetInfoActivity extends BaseActivity {
    @ViewById
    TextView tv_name, tv_idcard,tv_phone,tv_sex,tv_address;

    @ViewById
    ClearEditText et_nation,et_tel,et_job,et_workplace,et_postaladdress,et_postalcode,et_email;

    @ViewById
    AppCompatButton btn_ok;
    @ViewById
    ImageView iv_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_info);
    }

    @AfterViews
    void initView() {
        setTitleName("个人信息");
        getBaseInfoData();
        getdata();
    }


    @Click({R.id.btn_ok, R.id.ll_head})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                updateOtherInfo();
                break;
            case R.id.ll_head:
                PictureSelector.create(this).openGallery(1)
                        .maxSelectNum(1)
                        .forResult(1);


                break;
        }
    }

    private void updateBaseInfo() {
        Call<StringResult> call = apiService.updateBaseInfo(baseInfoBean);
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {

            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {

            }
        });

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
                } else {
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

    private void getBaseInfoData() {
        Call<BaseInfoResult> call = apiService.getBaseInfo();
        call.enqueue(new MyCallback<BaseInfoResult>() {
            @Override
            public void onSuccessRequest(BaseInfoResult result) {
                if ("1".equals(result.getStatus())) {
                    setBaseInfoData(result.getMessage());
                }

            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<BaseInfoResult> call, Throwable t) {

            }
        });
    }

    private BaseInfoBean baseInfoBean;

    private void setBaseInfoData(BaseInfoBean message) {
        baseInfoBean = message;
        tv_name.setText(message.getMonitoredRealname());
        tv_idcard.setText(message.getMonitoredIdcard());
        tv_phone.setText(message.getMonitoredPhone());
        tv_address.setText(message.getPermanentAddress());

        RequestOptions options = new RequestOptions();
        options.transform(new GlideCircleTransform(this));
        Glide.with(this).load(Mate.PIC_PATH+message.getAvatar()).apply(options).into(iv_head);

        // et_address.setText(message.getPermanentAddress());
        if (message.getMonitoredIdcard().length() != 0) {
            String idcard = message.getMonitoredIdcard();
            int sex = Integer.parseInt(String.valueOf(idcard.charAt(idcard.length() - 2)));
            if (sex % 2 == 0) {
                tv_sex.setText("女");
            } else {
                tv_sex.setText("男");
            }

        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1://tupian
                    //去上传文件
                    List<LocalMedia> mediaList = PictureSelector.obtainMultipleResult(data);
                    uploadheanIcon(mediaList);


                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadheanIcon(List<LocalMedia> mediaList) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (LocalMedia media : mediaList) {
            File file = new File(media.getPath());
            RequestBody requestBody = RequestBody.create(null, file);
            builder.addFormDataPart("pic", file.getName(), requestBody);
        }

        Call<UrlResult> call = apiService.upload(builder.build());
        call.enqueue(new MyCallback<UrlResult>() {
            @Override
            public void onSuccessRequest(UrlResult result) {
                    if ("1".equals(result.getStatus())){
                        baseInfoBean.setAvatar(result.getMessage().getUrl());
                        updateBaseInfo();
                    }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<UrlResult> call, Throwable t) {

            }
        });
    }
}
