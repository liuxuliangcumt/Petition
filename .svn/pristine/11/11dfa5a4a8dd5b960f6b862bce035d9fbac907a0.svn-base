package com.realpower.petition.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.realpower.petition.R;
import com.realpower.petition.bean.NoticeBean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity
public class NoticeDetailActivity extends BaseActivity {

    @Extra
    NoticeBean.ListBean noticeBean;

    @ViewById
    TextView tv_title2,tv_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        setTitleName("详情");
        tv_title2.setText(noticeBean.getTitle());
        tv_content.setText(noticeBean.getContent());
    }
}
