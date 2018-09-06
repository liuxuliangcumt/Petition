package com.realpower.petition.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.realpower.petition.R;
import com.realpower.petition.bean.NewsBean;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.Receiver;
import org.androidannotations.annotations.ViewById;

//新闻详情
@EActivity
public class NewDetailActivity extends BaseActivity {

    @Extra
    NewsBean.ListBean bean;

    @ViewById
    TextView tv_time, tv_title2,tv_content;

    @ViewById
    ImageView iv_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        tv_title2.setText(bean.getTitle());
        setTitleName(bean.getTitle());
        tv_time.setText(bean.getCreateTime());
        tv_content.setText(bean.getContent());
    }
}
