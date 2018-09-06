package com.realpower.petition.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.realpower.petition.R;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.CodeParam;
import com.realpower.petition.net.param.RegisteParam;
import com.realpower.petition.net.result.StreetResult;
import com.realpower.petition.net.result.StringResult;
import com.realpower.petition.util.IdCardVerifyUtil;
import com.realpower.petition.util.MyToastUtils;
import com.realpower.petition.util.SystemInfoUtils;
import com.realpower.petition.util.TimeCount;
import com.realpower.petition.views.ClearEditText;
import com.realpower.petition.views.CustomDialog;
import com.realpower.petition.views.PassWordEditText;
import com.realpower.petition.views.addressview.PickAddressInterface;
import com.realpower.petition.views.addressview.PickAddressView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;

//注册账号
@EActivity
public class RegisterActivity extends BaseActivity {
    @ViewById(R.id.btn_code)
    Button btn_code;
    private TimeCount timeCount;

    @ViewById(R.id.et_idcard)
    ClearEditText et_idcard;

    @ViewById(R.id.et_name)
    ClearEditText et_name;
    @ViewById(R.id.et_phone)
    AppCompatEditText et_phone;

    @ViewById(R.id.et_addressDetail)
    ClearEditText et_addressDetail;

    @ViewById(R.id.et_password)
    PassWordEditText et_password;

    @ViewById(R.id.et_passwordRp)
    PassWordEditText et_passwordRp;
    @ViewById(R.id.et_code)
    ClearEditText et_code;

    @ViewById(R.id.tv_address)
    TextView tv_address;
    private String areaID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @AfterViews
    void initViews() {
        setTitleName("注册账号");
        timeCount = new TimeCount(this, 60000, btn_code);
        //getStreetData();
    }

    void getStreetData() {
        Call<StreetResult> call = apiService.getStreet();
        call.enqueue(new MyCallback<StreetResult>() {
            @Override
            public void onSuccessRequest(StreetResult result) {
                Log.e("aaa", " onResponse " + result.getMessage().size());

            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<StreetResult> call, Throwable t) {
                Log.e("aaa", " onFailureRequest " + t.getMessage());

            }
        });


    }

    @Click({R.id.btn_code, R.id.btn_ok, R.id.tv_address})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_code:
                sendCode();
                break;
            case R.id.btn_ok:
                onOkCkick();
                break;
            case R.id.tv_address:
                showAddressDialog();
                break;
        }
    }

    private void onOkCkick() {
        String name = et_name.getText().toString();
        String cardId = et_idcard.getText().toString();
        String passWord = et_password.getText().toString();
        String passWordRp = et_passwordRp.getText().toString();
        String phone = et_phone.getText().toString().trim();
        if (name.length() < 1) {
            MyToastUtils.showToast("请输入姓名");
            et_name.setFocusable(true);
            return;
        }

        if (cardId.length() != 18) {
            MyToastUtils.showToast("请输入正确的身份证号码");
            return;
        }
        if (passWord.length() < 6 || passWordRp.length() < 6) {
            MyToastUtils.showToast("请输入密码和重复密码");
            return;
        }
        if (!passWord.equals(passWordRp)) {
            MyToastUtils.showToast("请输入相同的密码和重复密码");
            return;
        }
        if (!phone.startsWith("1") || phone.length() != 11) {
            MyToastUtils.showToast("请输入11位有效手机号");
            return;
        }

        if (et_code.getText().toString().length() == 0) {
            MyToastUtils.showToast("请输入验证码");
            return;
        }
        if (areaID.length() == 0) {
            MyToastUtils.showToast("请选择所在地区");
            return;
        }
        if (et_addressDetail.getText().length() == 0) {
            MyToastUtils.showToast("请输入详细地址");
            return;
        }
        venifyId(name, cardId);//验证身份证号

    }

    private void sendCode() {
        String phone = et_phone.getText().toString();
        if (phone.length() != 11) {
            MyToastUtils.showToast("请输入11位手机号码");
            return;
        }
        if (!phone.startsWith("1")) {
            MyToastUtils.showToast("您输入的手机号格式不正确");
            return;
        }

        showMyDialog("正在发送验证码");
        Call<StringResult> call = apiService.getRegistCode(new CodeParam(phone));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    timeCount.start();
                    MyToastUtils.showToast("验证码已发送,请注意接收");
                } else {
                    MyToastUtils.showToast(result.getDesc().getDescription());
                }
                Log.e("aaa", "onSuccessRequest" + result.toString());
            }

            @Override
            public void afterRequest() {
                hideDialog();
            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {
                Log.e("aaa", "onFailure   " + t.getMessage());
            }
        });

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
            Log.e("aaa", "status  " + status + "  code   " + code);
            if ("1000".equals(code)) {
                starRegiste();
            } else {
                MyToastUtils.showToast("您的身份证号不正确,请核验");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void starRegiste() {
        String name = et_name.getText().toString();
        String cardId = et_idcard.getText().toString();
        String passWord = et_password.getText().toString();
        Call<StringResult> call = apiService.regist(new RegisteParam(Integer.parseInt(areaID), et_code.getText().toString(),
                et_addressDetail.getText().toString(), cardId,
                passWord, name,
                et_phone.getText().toString()));
        /* , et_phone.getText().toString(), et_addressDetail.getText().toString(),
                cardId, passWord,
                name*/
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    MyToastUtils.showToast(result.getDesc().getDescription());
                    finish();
                } else {
                    MyToastUtils.showToast("注册失败");
                }
                Log.e("aaa", "onSuccessRequest  " + result.toString() + "  \n\n\n" + result.getDesc().toString());

            }

            @Override
            public void afterRequest() {
                hideDialog();
            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {
                Log.e("aaa", "onFailureRequest  " + t.getMessage());

            }
        });

    }
}
