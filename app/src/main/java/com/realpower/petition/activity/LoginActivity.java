package com.realpower.petition.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import com.realpower.petition.MyApplication;
import com.realpower.petition.R;
import com.realpower.petition.keepalive.IntentWrapper;
import com.realpower.petition.net.Mate;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.LoginParam;
import com.realpower.petition.net.result.LoginResult;
import com.realpower.petition.server.LocationServer;
import com.realpower.petition.util.MyToastUtils;
import com.realpower.petition.util.PermissionUtils;
import com.realpower.petition.util.ShadowDrawable;
import com.realpower.petition.util.ShadowDrawableTest;
import com.realpower.petition.util.SharedPreferencesHelper;
import com.realpower.petition.util.SystemInfoUtils;
import com.realpower.petition.util.UIUtils;
import com.realpower.petition.views.ClearEditText;
import com.realpower.petition.views.PassWordEditText;
import com.tamic.novate.exception.ServerException;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushToken;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.FormatterClosedException;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

@EActivity
public class LoginActivity extends BaseActivity implements PermissionUtils.PermissionGrant {

    @ViewById(R.id.et_account)
    ClearEditText et_account;
    @ViewById(R.id.et_password)
    PassWordEditText et_password;
    private String useSig = "eJxlj1FPgzAUhd-5FYRn42gHTEx8KBMnxi3KJhpfGqQFr0hpaNkgxv**DZeI8fn7zj33fBmmaVqb*-V5mmV1KzTVveSWeWlatnX2C6UERlNNpw37B3knoeE0zTVvBohc18W2PXaAcaEhh5OhudIjqlhJh4qfuHPI*mj654CCYoDL8GkekTux9KWavMIqzthHdNvGdVy*Ve1Kp7BIZEUecC1nghTvBELyOVn0SR-ldeSsk8DrnJfguijdtrBvpBds58-dzvfCjYyqx6tRpYaKn-ZgD3uzC*SM6JY3CmoxCNhGLsKHh4*jjW9jDx6-XX4_";

    @ViewById
    AppCompatButton btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_account.setText(myPrefs.username().get());
        et_password.setText(myPrefs.password().get());
        String username = (String) SharedPreferencesHelper.getInstance().getData("username", "ff");
        if (username.length() == 11) {
            Intent startIntent = new Intent(this, LocationServer.class);
            startIntent.putExtra("username", username);
            startService(startIntent);
        }
        if (myPrefs.isShowAlive().get().length() <= 1) {
            IntentWrapper.whiteListMatters(this, "及时收到推送消息");
            myPrefs.isShowAlive().put("add");
        }

        ShadowDrawableTest.setShadowDrawable(btn_register,
                Color.parseColor("#e59c0e"), UIUtils.dpToPx(20),
                Color.parseColor("#75f8f8f8"),
                1, 0, UIUtils.dpToPx(10));


    }


    @Click({R.id.tv_register, R.id.btn_login, R.id.tv_password})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                loginTenXun();
                break;
            case R.id.tv_register:
                RegisterActivity_.intent(this).start();
                break;
            case R.id.tv_password:
                ResetPasswordActivity_.intent(this).start();
                break;
        }
    }

    private void loginTenXun() {
        Log.e("aaa", "登陸蛋疼騰待定訊");

        TIMManager.getInstance().login("test", useSig, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e("aaa", "login failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                Log.e("aaa", "login succ");
                registerPush();
            }
        });
    }

    public void registerPush() {
        final TIMOfflinePushToken param = new TIMOfflinePushToken(0, "");
        String vendor = Build.MANUFACTURER;
        if (vendor.toLowerCase(Locale.ENGLISH).contains("xiaomi")) {
            param.setToken((String) SharedPreferencesHelper.getInstance().getData(Mate.MI_REGID, ""));
            param.setBussid(Mate.TIM_MI_ID);
        } else if (vendor.toLowerCase(Locale.ENGLISH).contains("huawei")) {
            //请求华为推送设备 token
           /* param.setToken(token);
            param.setBussid(bussId);*/
        }

        TIMManager.getInstance().setOfflinePushToken(param, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Log.e("aaamiPush", "setOfflinePushToken  onError　　" + s);

            }

            @Override
            public void onSuccess() {
                Log.e("aaamiPush", "setOfflinePushToken  onSuccess　　" + (String) SharedPreferencesHelper.getInstance().getData(Mate.MI_REGID, ""));
            }
        });
    }

    private void login() {
        String account = et_account.getText().toString();
        String password = et_password.getText().toString();
        if (account.length() != 11) {
            MyToastUtils.showToast("请输入11位手机号");
            return;
        }
        if (password.length() < 6) {
            MyToastUtils.showToast("请输入密码");
            return;
        }
        showMyDialog("正在登录");
        Call<LoginResult> call = apiService.login(new LoginParam(account, password));
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                Log.e("aaa", response.body().toString() + "  " + response.body().getCode());
                if (200 == response.body().getCode()) {
                    myPrefs.token().put("token " + response.body().getToken());
                    myPrefs.password().put(et_password.getText().toString());
                    myPrefs.username().put(et_account.getText().toString());
                    SharedPreferencesHelper.getInstance().saveData("username", et_account.getText().toString());


                    MyToastUtils.showToast("登录成功");
                    if (MainActivity.instance != null) {
                        MainActivity.instance.finish();
                    }
                    MainActivity_.intent(LoginActivity.this).start();
                    // finish();
                } else if (403 == response.body().getCode()) {
                    MyToastUtils.showToast("账号或密码错误");
                }
                hideDialog();
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Exception ex = (Exception) t;
                MyCallback.NetError error = MyCallback.NetError.UNDEFINED;
                if (ex instanceof SocketTimeoutException) {
                    error = MyCallback.NetError.TIME_OUT;
                } else if (ex instanceof UnknownHostException) {
                    // error = NetError.DNS_ERROR;
                    error = MyCallback.NetError.UNDEFINED;
                } else if (ex instanceof ServerException || ex instanceof HttpException) {
                    error = MyCallback.NetError.SERVER_ERROR;
                } else if (ex instanceof FormatterClosedException || ex instanceof NumberFormatException) {
                    error = MyCallback.NetError.DATA_FORMAT_ERROR;
                } else if (!SystemInfoUtils.isNetworkAvailable(MyApplication.getInstance().getmContext())) {
                    error = MyCallback.NetError.NETWORK_DISABLE;
                }
                MyToastUtils.showToast(error.desc);
                hideDialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_ACCESS_FINE_LOCATION, this);
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_CAMERA, this);
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE, this);
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_READ_EXTERNAL_STORAGE, this);
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_READ_PHONE_STATE, this);

        //    PermissionUtils.getNoGrantedPermission(this, false);CODE_WRITE_EXTERNAL_STORAGE


    }

    @Override
    public void onPermissionGranted(int requestCode) {

    }


}
