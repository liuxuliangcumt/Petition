package com.realpower.petition.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.luck.picture.lib.entity.LocalMedia;
import com.realpower.petition.R;
import com.realpower.petition.adapter.PicAdapter;
import com.realpower.petition.adapter.VideoAdapter;
import com.realpower.petition.adapter.VoiceAdapter;
import com.realpower.petition.bean.MyLocalMedia;
import com.realpower.petition.bean.SuggestionDetailBean;
import com.realpower.petition.bean.VoiceBean;
import com.realpower.petition.listtener.VoicePlayClickListener;
import com.realpower.petition.net.Mate;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.IdParam;
import com.realpower.petition.net.param.SugestionCommentParam;
import com.realpower.petition.net.param.SuggestionIdParam;
import com.realpower.petition.net.result.StringResult;
import com.realpower.petition.net.result.SuggestionDetailResult;
import com.realpower.petition.util.MyToastUtils;
import com.realpower.petition.util.SystemInfoUtils;
import com.realpower.petition.views.CustomDialog;
import com.realpower.petition.views.CustomGridVeiw;
import com.realpower.petition.views.CustomListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class SuggestDetailActivity extends BaseActivity {

    @Extra
    int suggestionId;
    @ViewById(R.id.btn_acount)
    AppCompatButton btn_acount;

    @ViewById(R.id.tv_numb)
    AppCompatTextView tv_numb;

    @ViewById(R.id.tv_phone)
    AppCompatTextView tv_phone;
    @ViewById(R.id.tv_detail)
    AppCompatTextView tv_detail;
    @ViewById(R.id.tv_suggestTitle)
    AppCompatTextView tv_title;


    @ViewById(R.id.tv_voicename)
    TextView tv_voicename;

    @ViewById(R.id.tv_picname)
    TextView tv_picname;

    @ViewById(R.id.tv_videoname)
    TextView tv_videoname;

    @ViewById(R.id.tv_state)
    AppCompatTextView tv_state;

    @ViewById(R.id.tv_resultName)
    TextView tv_resultName;

    @ViewById(R.id.tv_reply)
    AppCompatTextView tv_reply;

    @ViewById(R.id.gv_pic)
    CustomGridVeiw gv_pic;

    @ViewById(R.id.lv_video)
    CustomListView lv_video;

    @ViewById(R.id.lv_voice)
    CustomListView lv_voice;
    private PicAdapter picAdapter;
    private List<LocalMedia> picData;
    private VoiceAdapter voiceAdapter;
    private List<MyLocalMedia> voiceData;
    private List<LocalMedia> videoData;
    private VideoAdapter videoAdapter;
    @ViewById(R.id.tv_commenContent)
    TextView tv_commenContent;
    @ViewById
    TextView tv_acount;
    @ViewById
    RatingBar RB_grad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_detail);
    }

    @AfterViews
    void initViews() {
        setTitleName("建议详情");
        picData = new ArrayList<>();
        picAdapter = new PicAdapter(this, picData);
        gv_pic.setAdapter(picAdapter);
        videoData = new ArrayList<>();
        videoAdapter = new VideoAdapter(this, videoData);
        lv_video.setAdapter(videoAdapter);
        voiceData = new ArrayList<>();
        voiceAdapter = new VoiceAdapter(this, voiceData);
        lv_voice.setAdapter(voiceAdapter);
        lv_voice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new VoicePlayClickListener(voiceData.get(position), (ImageView) view.findViewById(R.id.iv_voice),
                        SuggestDetailActivity.this).onClick(view);
            }
        });
        lv_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoPlayActivity_.intent(SuggestDetailActivity.this).path(videoData.get(position).getPath()).start();
            }
        });
        picAdapter.isDetail = true;
        videoAdapter.isDetail = true;
        voiceAdapter.isDetail = true;
        getData();
    }

    private void getData() {
        Call<SuggestionDetailResult> call = apiService.suggestionDetail(new IdParam(suggestionId + ""));
        call.enqueue(new MyCallback<SuggestionDetailResult>() {
            @Override
            public void onSuccessRequest(SuggestionDetailResult result) {
                if ("1".equals(result.getStatus())) {
                    setDetailData(result.getMessage());
                } else {
                    MyToastUtils.showToast(result.getDesc().getDescription());
                }

            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<SuggestionDetailResult> call, Throwable t) {

            }
        });

    }

    private int state;

    void setDetailData(SuggestionDetailBean message) {
        tv_detail.setText(message.getSuggestionInfo());
        tv_numb.setText("NO:" + message.getSuggestionNum());
        tv_phone.setText(message.getSuggestionContactInformation());
        state = message.getCurrentStatus();
        if (state == 1) {
            tv_state.setText("待解决");
            btn_acount.setText("一键催办");
            btn_acount.setBackground(getResources().getDrawable(R.drawable.bg_login_btn_yellow));
            tv_state.setTextColor(getResources().getColor(R.color.ce53c0e));
        } else if (state == 2) {
            btn_acount.setText("一键催办");
            tv_state.setText("解决中");
            btn_acount.setBackground(getResources().getDrawable(R.drawable.bg_login_btn_yellow));
            tv_state.setTextColor(getResources().getColor(R.color.ce59c0e));
        } else if (state == 3) {
            btn_acount.setText("处理打分");
            tv_state.setText("已解决");
            btn_acount.setBackground(getResources().getDrawable(R.drawable.bg_login_btn));
            tv_state.setTextColor(getResources().getColor(R.color.colorPrimary));
            if (message.getSuggestionCommentContent().length() != 0) {
                state = 4;
                btn_acount.setText("返回");
                RB_grad.setRating(message.getSuggestionCommentLevel());
                tv_commenContent.setText(message.getSuggestionCommentContent());
                tv_acount.setText(message.getSuggestionCommentLevel() + "分");
                findViewById(R.id.ll_commen).setVisibility(View.VISIBLE);
            }
        }
        tv_title.setText(message.getSuggestionTitle());
        if (message.getSuggestionResult().length() == 0) {
            tv_reply.setVisibility(View.GONE);
            tv_resultName.setVisibility(View.GONE);
        } else {
            tv_reply.setText(message.getSuggestionResult());
        }

        if (message.getSuggestionImageUrl().length() <= 2) {//规避'暂无'
            tv_picname.setVisibility(View.GONE);
            gv_pic.setVisibility(View.GONE);
        } else {
            String[] path = message.getSuggestionImageUrl().split(",");
            for (int i = 0; i < path.length; i++) {
              //  picData.add(Mate.PIC_PATH + path[i]);
            }
            picAdapter.notifyDataSetChanged();
        }


        if (message.getSuggestionVoiceUrl().length() <= 2) {
            lv_voice.setVisibility(View.GONE);
            tv_voicename.setVisibility(View.GONE);
        } else {
            String[] path = message.getSuggestionVoiceUrl().split(",");
            for (int i = 0; i < path.length; i++) {
                VoiceBean bean = new VoiceBean();
                bean.setDetail(true);
                bean.setLoadPath(path[i]);
              //  voiceData.add(bean);
            }
            voiceAdapter.notifyDataSetChanged();
            lv_voice.setVisibility(View.VISIBLE);
            tv_voicename.setVisibility(View.VISIBLE);
        }
        if (message.getSuggestionVideoUrl().length() <= 2) {
            lv_video.setVisibility(View.GONE);
            tv_videoname.setVisibility(View.GONE);
        } else {
            String[] path = message.getSuggestionVideoUrl().split(",");
            for (int i = 0; i < path.length; i++) {
                //videoData.add(Mate.PIC_PATH + path[i]);
            }
            videoAdapter.notifyDataSetChanged();
        }

    }

    @Click({R.id.btn_acount})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_acount:
                if (state == 1 || state == 2) {
                    remindeSuggestion();
                } else if (state == 3) {
                    showGradedDailog();//打分
                } else if (state == 4) {
                    finish();
                }
                break;
        }
    }

    private void remindeSuggestion() {
        Call<StringResult> call = apiService.remindeSuggestion(new SuggestionIdParam(suggestionId));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    MyToastUtils.showToast("催办成功");
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

    private void showGradedDailog() {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_graded);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.CENTER);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this) * 0.80);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        RatingBar bar = (RatingBar) dialog.getCustomView().findViewById(R.id.RB_graded);
        TextView tv_title = (TextView) dialog.getCustomView().findViewById(R.id.tv_title);
        tv_title.setText("意见反馈打分");
        TextView tv_cancel = (TextView) dialog.getCustomView().findViewById(R.id.tv_cancel);
        final RatingBar RB_graded = (RatingBar) dialog.getCustomView().findViewById(R.id.RB_graded);
        final EditText et_content = (EditText) dialog.getCustomView().findViewById(R.id.et_content);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView tv_ok = (TextView) dialog.getCustomView().findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_content.getText().toString().length() != 0)
                    commentSuggestion(dialog, et_content.getText().toString(), RB_graded.getRating());
            }
        });
    }

    private void commentSuggestion(final CustomDialog dialog, String s, float rating) {
        showMyDialog("请稍后");
        Call<StringResult> call = apiService.commentSuggestion(new SugestionCommentParam(s, (int) rating, suggestionId));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("3".equals(result.getDesc().getCode())) {
                    dialog.dismiss();
                    MyToastUtils.showToast("打分成功");
                    getData();
                } else {
                    MyToastUtils.showToast(result.getDesc().getDescription());
                }

            }

            @Override
            public void afterRequest() {
                hideDialog();

            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (VoicePlayClickListener.currentPlayListener != null && VoicePlayClickListener.isPlaying) {
            VoicePlayClickListener.currentPlayListener.stopPlayVoice();
        }
    }

    @Override
    public void onBackPressed() {
        if (VoicePlayClickListener.currentPlayListener != null && VoicePlayClickListener.isPlaying) {
            VoicePlayClickListener.currentPlayListener.stopPlayVoice();
        }
        super.onBackPressed();
    }
}
