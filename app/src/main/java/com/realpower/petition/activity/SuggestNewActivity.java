package com.realpower.petition.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.realpower.petition.R;
import com.realpower.petition.adapter.PicAdapter;
import com.realpower.petition.adapter.VideoAdapter;
import com.realpower.petition.adapter.VoiceAdapter;
import com.realpower.petition.bean.AssociaterBean;
import com.realpower.petition.bean.MyLocalMedia;
import com.realpower.petition.bean.VoiceBean;
import com.realpower.petition.listtener.VoicePlayClickListener;
import com.realpower.petition.net.Mate;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.SuggestionAddParam;
import com.realpower.petition.net.result.StringResult;
import com.realpower.petition.net.result.UpLoadResutlText;
import com.realpower.petition.net.result.UploadResult;
import com.realpower.petition.util.MyToastUtils;
import com.realpower.petition.util.PathUtil;
import com.realpower.petition.util.SystemInfoUtils;
import com.realpower.petition.util.record.EaseVoiceRecorderView;
import com.realpower.petition.views.ClearEditText;
import com.realpower.petition.views.CustomDialog;
import com.realpower.petition.views.CustomGridVeiw;
import com.realpower.petition.views.CustomListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class SuggestNewActivity extends BaseActivity {

    @ViewById(R.id.GV_pic)
    CustomGridVeiw GV_pic;
    @ViewById(R.id.lv_video)
    CustomListView lv_video;
    @ViewById(R.id.lv_voice)
    CustomListView lv_voice;
    @ViewById(R.id.et_phone)
    ClearEditText et_phone;
    @ViewById(R.id.et_title)
    ClearEditText et_title;
    @ViewById(R.id.et_detail)
    ClearEditText et_detail;
    PicAdapter picAdapter;
    VideoAdapter videoAdapter;
    private List<LocalMedia> picData;
    private List<LocalMedia> videoData;
    private List<MyLocalMedia> voiceData;
    VoiceAdapter voiceAdapter;

    private String videoPath, voicePath, picPath = "";
    private boolean picUpload, voiceUpload, videoUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_new);
        setTitleName("添加意见");
    }

    @AfterViews
    void initViews() {
        picData = new ArrayList<>();
        picAdapter = new PicAdapter(this, picData);
        GV_pic.setAdapter(picAdapter);
        picAdapter.setOnDeleteClickLisnter(new PicAdapter.OnDeleteClickLisnter() {
            @Override
            public void onDeleteClick(int position) {
                picData.remove(position);
                picAdapter.notifyDataSetChanged();
            }
        });
        GV_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position + 1 > picData.size()) {
                    getFile(1);
                } else {

                }
            }
        });
        videoData = new ArrayList<>();
        videoAdapter = new VideoAdapter(this, videoData);
        lv_video.setAdapter(videoAdapter);
        videoAdapter.setOnDeleteClickLisnter(new VideoAdapter.OnDeleteClickLisnter() {
            @Override
            public void onDeleteClick(int position) {
                videoData.remove(position);
                videoAdapter.notifyDataSetChanged();
            }
        });
        lv_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position + 1 > picData.size()) {
                    getFile(2);
                } else {

                }
            }
        });
        voiceData = new ArrayList<>();
        voiceAdapter = new VoiceAdapter(this, voiceData);
        lv_voice.setAdapter(voiceAdapter);
        lv_voice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new VoicePlayClickListener(voiceData.get(position), (ImageView) view.findViewById(R.id.iv_voice),
                        SuggestNewActivity.this).onClick(view);
            }
        });
        et_phone.setText(myPrefs.username().get());
    }

    @Click({R.id.btn_ok})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                beforeAdd();
                break;
        }
    }

    private void beforeAdd() {
        String phone = et_phone.getText().toString();
        String petitionDetail = et_detail.getText().toString();
        String title = et_title.getText().toString();
        if ("".equals(title)) {
            MyToastUtils.showToast("请输入意见标题");
            return;
        }
        if ("".equals(petitionDetail)) {
            MyToastUtils.showToast("请输入意见详情");
            return;
        }
        if (!phone.startsWith("1") || phone.length() != 11) {
            MyToastUtils.showToast("请输入11位有效手机号");
            return;
        }
        picUpload = picData.size() == 0 ? false : true;
        videoUpload = videoData.size() == 0 ? false : true;
        voiceUpload = voiceData.size() == 0 ? false : true;
        showMyDialog("请稍后");
        needUploadFile();
    }

    private void needUploadFile() {
        if (picData.size() != 0 && picUpload) {
            upLoadFile(picData, 1);
            return;
        }
        if (videoData.size() != 0 && videoUpload) {
            upLoadFile(videoData, 2);
            return;
        }
        if (voiceData.size() != 0 && voiceUpload) {
            List<String> voiceStringData = new ArrayList<>();
            for (int i = 0; i < voiceData.size(); i++) {
                voiceStringData.add(voiceData.get(i).getPath());
            }
            //  upLoadFile(voiceStringData, 3);
            return;
        }
        if (!picUpload && !voiceUpload && !videoUpload) {
            String phone = et_phone.getText().toString();
            String petitionDetail = et_detail.getText().toString();
            String title = et_title.getText().toString();
            opinionAdd(title, petitionDetail, phone);
        }
    }

    private void opinionAdd(String title, String content, String phone) {
        showMyDialog("请稍后");
        Call<StringResult> call = apiService.suggestionAdd(new SuggestionAddParam(title, content, phone,
                picPath, videoPath, voicePath));
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                if ("1".equals(result.getStatus())) {
                    MyToastUtils.showToast("新建成功");
                    finish();
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

    HttpUtils httpUtils;

    /**
     * @param paths 上传文件路径
     * @param type  文件类型 1 图片  2 视频 3 语音
     */
    private void upLoadFile(List<LocalMedia> paths, final int type) {
        //  upLoadFile(PathUtil.getRealPathFromUri(this, uri));
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        RequestParams params = new RequestParams();
        params.addBodyParameter("enctype", "multipart/form-data");
        params.addHeader("Authorization", myPrefs.token().get());
        for (int i = 0; i < paths.size(); i++) {
            File file = new File(paths.get(i).getPath());
            params.addBodyParameter(file.getName(), file);
        }
        if (type == 1) {
            params.addBodyParameter("type", "pic");
        } else if (type == 2) {
            params.addBodyParameter("type", "video");
        } else if (type == 3) {
            params.addBodyParameter("type", "voice");
        }
        showMyDialog("正在上传文件", false);
        httpUtils.send(HttpRequest.HttpMethod.POST, Mate.S_UPLAOD, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Gson gson = new Gson();
                UpLoadResutlText result = gson.fromJson(responseInfo.result, UpLoadResutlText.class);
                if ("1".equals(result.getDesc().getCode())) {
                    switch (type) {
                        case 1:
                            picPath = result.getMessage().getUrl();
                            Log.e("aaa", "图片上传成功  " + picPath);
                            picUpload = false;
                            break;
                        case 2:
                            videoPath = result.getMessage().getUrl();
                            videoUpload = false;
                            break;
                        case 3:
                            voicePath = result.getMessage().getUrl();
                            voiceUpload = false;
                            Log.e("aaa", "语音 " + voicePath);
                            break;
                    }
                    needUploadFile();
                } else {
                    Log.e("aaa", "上传文件失败  " + result.toString());
                    MyToastUtils.showToast("上传文件失败");
                }
                hideDialog();
            }

            @Override
            public void onFailure(HttpException e, String msg) {
                Log.e("aaa", "文件上传失败 " + msg + "   " + e.getMessage() + "   " + e.getExceptionCode());
                hideDialog();
            }

        });

    }

    private void getFile(int i) {

        int selectNum = 0;
        switch (i) {
            case 1:
                selectNum = 9 - picData.size();
                break;
            case 2:
                selectNum = 3 - videoData.size();
                break;
            case 3:

                break;
        }
        PictureSelector.create(this).openGallery(i)
                .maxSelectNum(selectNum)
                .forResult(i);


    }

    private void showVoiceDialog() {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_voice);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this));
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        LinearLayout ll_speak = (LinearLayout) dialog.getCustomView().findViewById(R.id.ll_speak);
        LinearLayout ll_choose = (LinearLayout) dialog.getCustomView().findViewById(R.id.ll_choose);
        ll_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/*");
                startActivityForResult(intent, 2);
                dialog.dismiss();
            }
        });
        ll_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVoiceInputDialog();
                dialog.dismiss();
            }
        });
    }

    private void showVoiceInputDialog() {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_voice_input);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this) * 0.6);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        final EaseVoiceRecorderView recorderView = (EaseVoiceRecorderView) dialog.getCustomView().findViewById(R.id.recorderView);
        ImageView iv_cancel = (ImageView) dialog.getCustomView().findViewById(R.id.iv_cancel);
        Button btn_start = (Button) dialog.getCustomView().findViewById(R.id.btn_start);
        LinearLayout ll_parant = (LinearLayout) dialog.getCustomView().findViewById(R.id.ll_parant);
        ll_parant.getBackground().mutate().setAlpha(125);
        btn_start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                return recorderView.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderView.EaseVoiceRecorderCallback() {
                    @Override
                    public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                        Log.e("aaa", "onVoiceRecordComplete录音文件  " + voiceFilePath);
                        VoiceBean voiceBean = new VoiceBean(voiceFilePath, voiceTimeLength + "");
                        //voiceData.add(voiceBean);
                        voiceAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1://tupian
                    picData.addAll(PictureSelector.obtainMultipleResult(data));
                    picAdapter.notifyDataSetChanged();
                    break;
                case 2: //视频
                    videoData.addAll(PictureSelector.obtainMultipleResult(data));
                    videoAdapter.notifyDataSetChanged();
                    break;

                case 3: //音频
                    //voiceData.addAll((Collection<? extends MyLocalMedia>) PictureSelector.obtainMultipleResult(data));
                    voiceAdapter.notifyDataSetChanged();
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (VoicePlayClickListener.currentPlayListener != null && VoicePlayClickListener.isPlaying) {
            VoicePlayClickListener.currentPlayListener.stopPlayVoice();
        }
    }
}
