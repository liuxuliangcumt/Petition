package com.realpower.petition.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.realpower.petition.R;
import com.realpower.petition.net.ApiService;
import com.realpower.petition.net.Mate;
import com.realpower.petition.net.OkHttpClientUtil;
import com.realpower.petition.net.PetitionPref_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/30.
 */
@EActivity
public class BaseActivity extends AppCompatActivity {
    @Pref
    public static PetitionPref_ myPrefs;
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Mate.API)
            .addConverterFactory(GsonConverterFactory.create())
            // .client(OkHttpClientUtil.getOkHttpClient("token"))
            .build();
    public static ApiService apiService = retrofit.create(ApiService.class);

    public void addClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Mate.API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClientUtil.getOkHttpClient(myPrefs.token().get()))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void setTitleName(String name) {
        TextView textView = (TextView) findViewById(R.id.tv_title);
        if (textView != null) {
            textView.setText(name);
        }
    }

    public void setRightName(String name) {
        TextView textView = (TextView) findViewById(R.id.tv_right);
        if (textView != null) {
            textView.setText(name);
        }
    }

    @Click(R.id.iv_back)
    public void onIvBackPressed() {
        onBackPressed();
    }

    public ProgressDialog pDialog;

    public void showMyDialog(String message) {
        if (pDialog == null) {
            pDialog = new ProgressDialog(this);
            pDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    hideDialog();
                }
            });

        }
        if ("".equals(message)) {
            pDialog.setMessage("请稍后");
        } else {
            pDialog.setMessage(message);
        }
        pDialog.show();
        handler.sendEmptyMessageDelayed(1, 500);
    }

    public void showMyDialog(String message, boolean isTachOut) {//isTachOut 设置返回键和触摸外部 进度条消失 false不消失
        if (pDialog == null) {
            pDialog = new ProgressDialog(this);
            pDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    hideDialog();
                }
            });

        }
        if ("".equals(message)) {
            pDialog.setMessage("请稍后");
        } else {
            pDialog.setMessage(message);
        }
        pDialog.show();
        handler.sendEmptyMessageDelayed(1, 500);
        pDialog.setCanceledOnTouchOutside(isTachOut);
        pDialog.setCancelable(isTachOut);//设置进度条是否可以按退回键取消
    }

    @Override
    protected void onDestroy() {
        if (pDialog != null) {
            pDialog.dismiss();
            //  handler.removeMessages(1);
            hideDialog();
        }
        super.onDestroy();
    }

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    if (pDialog != null && isAskEnd && pDialog.isShowing()) {
                        pDialog.dismiss();
                        isAskEnd = false;
                        handler.removeMessages(1);
                        Log.e("aaa", "pdialog隐藏");
                    } else {
                        handler.sendEmptyMessageDelayed(1, 500);
                        Log.e("aaa", "pdialog隐藏yanshi延时");
                    }
                    break;
            }
            return false;
        }
    });
    public boolean isAskEnd = false;

    public void hideDialog() {
        isAskEnd = true;
    }
}
