package com.realpower.petition.activity;

import android.support.v7.app.AppCompatActivity;
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
import com.realpower.petition.bean.PetitionDetailBean;
import com.realpower.petition.bean.VoiceBean;
import com.realpower.petition.listtener.VoicePlayClickListener;
import com.realpower.petition.net.Mate;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.AppealIdParam;
import com.realpower.petition.net.param.IdParam;
import com.realpower.petition.net.param.PetitionCommentParam;
import com.realpower.petition.net.result.PetitionDetailResult;
import com.realpower.petition.net.result.StringResult;
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
public class PetitionDetailActivity extends BaseActivity {
    @Extra
    int petitionId;

    @ViewById(R.id.btn_acount)
    AppCompatButton btn_acount;

    @ViewById(R.id.tv_state)
    AppCompatTextView tv_state;

    @ViewById(R.id.tv_numb)
    AppCompatTextView tv_numb;
    @ViewById(R.id.tv_type)
    AppCompatTextView tv_type;
    @ViewById(R.id.tv_phone)
    AppCompatTextView tv_phone;
    @ViewById(R.id.tv_detail)
    AppCompatTextView tv_detail;
    @ViewById(R.id.tv_petition_title)
    AppCompatTextView tv_title;
    @ViewById(R.id.tv_person)
    AppCompatTextView tv_person;

    @ViewById(R.id.tv_address)
    AppCompatTextView tv_address;

    @ViewById(R.id.tv_voicename)
    TextView tv_voicename;

    @ViewById(R.id.tv_picname)
    TextView tv_picname;
    @ViewById(R.id.tv_videoname)
    TextView tv_videoname;

    @ViewById
    TextView tv_acount;
    @ViewById(R.id.gv_pic)
    CustomGridVeiw gv_pic;

    @ViewById(R.id.lv_video)
    CustomListView lv_video;

    @ViewById(R.id.lv_voice)
    CustomListView lv_voice;
    @ViewById(R.id.tv_commenContent)
    TextView tv_commenContent;
    @ViewById
    RatingBar RB_grad;
    private PicAdapter picAdapter;
    private List<LocalMedia> picData;
    private VoiceAdapter voiceAdapter;
    private List<MyLocalMedia> voiceData;
    private List<LocalMedia> videoData;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petition_detail);
        setTitleName("诉求详情");
    }

    @AfterViews
    void initViews() {
        picData = new ArrayList<>();
        picAdapter = new PicAdapter(this, picData);
        gv_pic.setAdapter(picAdapter);
        videoData = new ArrayList<>();
        videoAdapter = new VideoAdapter(this, videoData);
        lv_video.setAdapter(videoAdapter);
        voiceData = new ArrayList<>();
        voiceAdapter = new VoiceAdapter(this, voiceData);
        lv_voice.setAdapter(voiceAdapter);
        picAdapter.isDetail = true;
        videoAdapter.isDetail = true;
        voiceAdapter.isDetail = true;
        lv_voice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("aaa", "音频地址 :  " + voiceData.get(position).getPath());
                if (VoicePlayClickListener.currentPlayListener != null && VoicePlayClickListener.isPlaying) {
                    VoicePlayClickListener.currentPlayListener.stopPlayVoice();
                }
                new VoicePlayClickListener(voiceData.get(position), (ImageView) view.findViewById(R.id.iv_voice),
                        PetitionDetailActivity.this).onClick(view);
            }
        });
        lv_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoPlayActivity_.intent(PetitionDetailActivity.this).path(videoData.get(position).getPath()).start();
            }
        });
        showMyDialog("请稍后");
        getDetail();
    }

    private void getDetail() {
        Call<PetitionDetailResult> call = apiService.shuquDetail(new IdParam(petitionId + ""));
        call.enqueue(new MyCallback<PetitionDetailResult>() {
            @Override
            public void onSuccessRequest(PetitionDetailResult result) {
                Log.e("aaa", " 诉求详情 onSuccessRequest");
                if ("1".equals(result.getStatus())) {
                    setDetailData(result.getMessage());
                } else {
                    MyToastUtils.showToast("");
                }
            }

            @Override
            public void afterRequest() {
                hideDialog();
            }

            @Override
            public void onFailureRequest(Call<PetitionDetailResult> call, Throwable t) {
                Log.e("aaa", " 诉求详情 onFailure " + t.toString());

            }
        });
    }

    private int state;

    private void setDetailData(PetitionDetailBean message) {
        tv_detail.setText(message.getAppealInfo());
        tv_numb.setText("NO:" + message.getAppealNum());
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
            if (message.getAppealStatus() == 1) {
                state = 4;
                btn_acount.setText("返回");
                RB_grad.setRating(message.getAppealCommentLevel());
                tv_commenContent.setText(message.getAppealCommentContent());
                tv_acount.setText(message.getAppealCommentLevel() + "分");
                findViewById(R.id.ll_commen).setVisibility(View.VISIBLE);
            }
        }
        tv_title.setText(message.getAppealTitle());
        tv_type.setText(message.getCategory());
        tv_address.setText(message.getAddress());
        tv_person.setText(message.getAssociateName());
        tv_phone.setText(message.getAppealContactInformation());
        if (message.getAppealImageUrl().length() <= 2) {
            tv_picname.setVisibility(View.GONE);
            gv_pic.setVisibility(View.GONE);
        } else {
            String[] path = message.getAppealImageUrl().split(",");
            for (int i = 0; i < path.length; i++) {
                LocalMedia media = new LocalMedia();
                media.setPath((Mate.PIC_PATH + path[i]));
                picData.add(media);
                //picData.add(Mate.PIC_PATH + path[i]);
            }
            picAdapter.notifyDataSetChanged();
        }
        if (message.getAppealVoiceUrl().length() <= 2) {
            lv_voice.setVisibility(View.GONE);
            tv_voicename.setVisibility(View.GONE);
        } else {
            String[] path = message.getAppealVoiceUrl().split(",");
           /* for (int i = 0; i < path.length; i++) {
                VoiceBean bean = new VoiceBean();
                bean.setPath(Mate.PIC_PATH + path[i]);
                voiceData.add(bean);
            }*/
            for (int i = 0; i < path.length; i++) {
                VoiceBean bean = new VoiceBean();
                bean.setDetail(true);
                bean.setLoadPath(path[i]);
                // voiceData.add(bean);
            }
            voiceAdapter.notifyDataSetChanged();
        }
        if (message.getAppealVideoUrl().length() <= 2) {
            lv_video.setVisibility(View.GONE);
            tv_videoname.setVisibility(View.GONE);
        } else {
            String[] path = message.getAppealVideoUrl().split(",");
            for (int i = 0; i < path.length; i++) {
                LocalMedia media = new LocalMedia();
                media.setPath((Mate.PIC_PATH + path[i]));
                videoData.add(media);
            }
            videoAdapter.notifyDataSetChanged();
        }

    }

    @Click({R.id.btn_acount})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_acount:
                if (state == 1 || state == 2) {
                    remindePtition();
                } else if (state == 3) {
                    showGradedDailog();//打分
                } else if (state == 4) {
                    finish();
                }
                break;
            case R.id.tv_right:
                PetitionNewActivity_.intent(this).start();
                break;
        }
    }

    private void remindePtition() {
        Call<StringResult> call = apiService.remindePtition(new AppealIdParam(petitionId));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    MyToastUtils.showToast("催办成功,尽快为您解决诉求");
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
                    commentPetition(dialog, et_content.getText().toString(), RB_graded.getRating());
            }
        });
    }

    private void commentPetition(final CustomDialog dialog, String s, float rating) {
        showMyDialog("请稍后");
        Call<StringResult> call = apiService.commentPetition(new PetitionCommentParam(s, (int) rating, petitionId));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("3".equals(result.getDesc().getCode())) {
                    dialog.dismiss();
                    MyToastUtils.showToast("打分成功");
                    getDetail();
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
