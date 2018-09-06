package com.realpower.petition.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.realpower.petition.R;
import com.realpower.petition.bean.AssociaterBean;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.AssociateParam;
import com.realpower.petition.net.result.AssociaterResult;
import com.realpower.petition.net.result.BaseInfoResult;
import com.realpower.petition.net.result.StringResult;
import com.realpower.petition.util.IdCardVerifyUtil;
import com.realpower.petition.util.MyToastUtils;
import com.realpower.petition.util.SystemInfoUtils;
import com.realpower.petition.views.ClearEditText;
import com.realpower.petition.views.CustomDialog;
import com.realpower.petition.views.addressview.PickAddressInterface;
import com.realpower.petition.views.addressview.PickAddressView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class JointerNewActivity extends BaseActivity {

    @ViewById(R.id.tv_address)
    TextView tv_address;
    private String areaID;
    @ViewById(R.id.et_name)
    ClearEditText et_name;
    @ViewById(R.id.et_idcard)
    ClearEditText et_idcard;
    @ViewById(R.id.et_addressDetail)
    ClearEditText et_addressDeatail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jointer_new);
        getAssociate();
        getBaseInfo();
    }

    private List<AssociaterBean> dataList = new ArrayList<>();

    private void getAssociate() {
        Call<AssociaterResult> call = apiService.getAssociate();
        call.enqueue(new MyCallback<AssociaterResult>() {
            @Override
            public void onSuccessRequest(AssociaterResult result) {
                if ("1".equals(result.getStatus())) {
                    dataList = result.getMessage();
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

    @AfterViews
    void initViews() {
        setTitleName("新建联名人");
    }

    @Click({R.id.tv_address, R.id.btn_add})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_address:
                showAddressDialog();
                break;
            case R.id.btn_add:
                inputCheck();
                break;
        }
    }


    private void showAddressDialog() {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_chose_address);
        dialog.show();
        dialog.getWindow().setWindowAnimations(R.style.DialogBottom);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this));
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        PickAddressView pickView = (PickAddressView) dialog.getCustomView().findViewById(R.id.pickView);
        pickView.setOnTopClicklistener(new PickAddressInterface() {
            @Override
            public void onOkClick(String name, String areaId) {
                tv_address.setText(name);
                areaID = areaId;
                dialog.dismiss();
            }

            @Override
            public void onCancelClick() {
                dialog.dismiss();
            }
        });

    }

    private void inputCheck() {
        String name = et_name.getText().toString();
        String idCard = et_idcard.getText().toString().toLowerCase();
        String addressDetail = et_addressDeatail.getText().toString();
        if ("".equals(name)) {
            MyToastUtils.showToast("请输入姓名");
            return;
        }
        if (idCard.length() != 18) {
            MyToastUtils.showToast("请输入18位身份证号");
            return;
        }
        if ("".equals(addressDetail)) {
            MyToastUtils.showToast("请输入详细地址");
            return;
        }
        boolean isSame = false;
        for (int i = 0; i < dataList.size(); i++) {
            String cardID = dataList.get(i).getIdcard().toLowerCase();
          //  Log.e("aaa", "比对 " + cardID + "  cardID  " + idCard + "  idCard  " + i);
            if (cardID.equals(idCard)) {
                isSame = true;
            }
        }
      //  Log.e("aaa", "联名人比对结果  " + isSame);
        if (isSame || selfCardID.equals(idCard)) {
            MyToastUtils.showToast("联名人不能重复,且不能是自己");
            return;
        }

        venifyId(name, idCard);
    }

    private String selfCardID = "";

    private void getBaseInfo() {
        Call<BaseInfoResult> call = apiService.getBaseInfo();
        call.enqueue(new MyCallback<BaseInfoResult>() {
            @Override
            public void onSuccessRequest(BaseInfoResult result) {
                if ("1".equals(result.getStatus())) {
                    selfCardID = result.getMessage().getMonitoredIdcard().toString().toLowerCase();
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

    @Background
    public void venifyId(String name, String idCard) {
        IdCardVerifyUtil idCardVerifyUtil = new IdCardVerifyUtil();
        String js = idCardVerifyUtil.idcard_verify(name, idCard);
        //{"data":{"code":"1108","message":"异常"},"status":"2005"}
        try {
            JSONObject jsonObject = new JSONObject(js);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            String status = jsonObject.getString("status");
            String code = dataObject.getString("code");
            if ("1000".equals(code)) {
                associateAdd();
            } else if ("1001".equals(code)) {
                MyToastUtils.showToast("身份号码验证未通过");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void associateAdd() {
        Call<StringResult> call = apiService.associateAdd(new AssociateParam(et_addressDeatail.getText().toString(),
                Integer.parseInt(areaID), et_idcard.getText().toString(), 0, et_name.getText().toString()));

        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("4".equals(result.getDesc().getCode())) {
                    MyToastUtils.showToast("创建成功");
                    finish();
                } else {
                    MyToastUtils.showToast(result.getDesc().getDescription());
                }
                Log.e("aaa", "创建联名人  " + result.toString());
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {

            }
        });
    }
}
