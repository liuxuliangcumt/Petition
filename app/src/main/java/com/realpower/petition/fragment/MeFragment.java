package com.realpower.petition.fragment;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.realpower.petition.R;
import com.realpower.petition.activity.ResetInfoActivity_;
import com.realpower.petition.activity.ResetPasswordActivity_;
import com.realpower.petition.activity.ResetPhoneActivity_;
import com.realpower.petition.bean.BaseInfoBean;
import com.realpower.petition.net.Mate;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.result.BaseInfoResult;
import com.realpower.petition.util.GlideCircleTransform;
import com.realpower.petition.util.SystemInfoUtils;
import com.realpower.petition.views.CustomDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;


/**
 * Created by Administrator on 2017/11/9.
 */
@EFragment(R.layout.fragment_me)
public class MeFragment extends BaseFragment {

    @ViewById
    ImageView iv_head;

    @ViewById
    TextView tv_name;

    @AfterViews
    void initViews() {
        setTitleName("我的");
        getData();
    }


    private void getData() {
        Call<BaseInfoResult> call = apiService.getBaseInfo();
        call.enqueue(new MyCallback<BaseInfoResult>() {
            @Override
            public void onSuccessRequest(BaseInfoResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
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

    private void setData(BaseInfoBean message) {
        tv_name.setText(message.getMonitoredRealname());
        RequestOptions options = new RequestOptions();
        options.transform(new GlideCircleTransform(getContext()));

        Glide.with(getContext()).load(Mate.PIC_PATH + message.getAvatar()).apply(options).into(iv_head);
    }


    @Click({R.id.ll_phone, R.id.ll_update, R.id.ll_info, R.id.ll_password})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_phone:
                ResetPhoneActivity_.intent(getContext()).start();
                break;
            case R.id.ll_update:
                showUpdateDailog();
                break;
            case R.id.ll_info:
                ResetInfoActivity_.intent(getActivity()).start();
                break;
            case R.id.ll_password:
                ResetPasswordActivity_.intent(getActivity()).isLogin(true).start();
                break;
        }
    }

    private void showUpdateDailog() {
        CustomDialog dialog = new CustomDialog(getActivity(), R.style.customDialog, R.layout.dialog_version_update);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.CENTER);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(getActivity()) * 0.80);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        RatingBar bar = (RatingBar) dialog.getCustomView().findViewById(R.id.RB_graded);

    }
}
