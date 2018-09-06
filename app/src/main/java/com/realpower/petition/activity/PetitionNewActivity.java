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
import android.widget.ListView;
import android.widget.TextView;

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
import com.realpower.petition.adapter.TypeChildrenAdapter;
import com.realpower.petition.adapter.TypeParentAdapter;
import com.realpower.petition.adapter.VideoAdapter;
import com.realpower.petition.adapter.VoiceAdapter;
import com.realpower.petition.bean.AssociaterBean;
import com.realpower.petition.bean.MyLocalMedia;
import com.realpower.petition.bean.PetitionType;
import com.realpower.petition.bean.VoiceBean;
import com.realpower.petition.listtener.VoicePlayClickListener;
import com.realpower.petition.net.Mate;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.PetitionAddParam;
import com.realpower.petition.net.result.PetitionTypeResult;
import com.realpower.petition.net.result.StringResult;
import com.realpower.petition.net.result.UpLoadResutlText;
import com.realpower.petition.util.MyToastUtils;
import com.realpower.petition.util.SystemInfoUtils;
import com.realpower.petition.util.record.EaseVoiceRecorderView;
import com.realpower.petition.views.ClearEditText;
import com.realpower.petition.views.CustomDialog;
import com.realpower.petition.views.CustomGridVeiw;
import com.realpower.petition.views.CustomListView;
import com.realpower.petition.views.addressview.PickAddressInterface;
import com.realpower.petition.views.addressview.PickAddressView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

@EActivity
public class PetitionNewActivity extends BaseActivity {

    @ViewById(R.id.GV_pic)
    CustomGridVeiw GV_pic;
    @ViewById(R.id.lv_video)
    CustomListView lv_video;
    @ViewById(R.id.lv_voice)
    CustomListView lv_voice;
    PicAdapter picAdapter;
    VideoAdapter videoAdapter;
    private List<LocalMedia> videoData;
    private List<LocalMedia> picData;
    private List<MyLocalMedia> voiceData;
    VoiceAdapter voiceAdapter;
    @ViewById(R.id.tv_address)
    TextView tv_address;

    @ViewById(R.id.tv_jointer)
    TextView tv_jointer;

    private String areaID = "";
    @ViewById(R.id.et_petitionDetail)
    ClearEditText et_petitionDetail;
    @ViewById(R.id.et_phone)
    ClearEditText et_phone;
    @ViewById(R.id.et_title)
    ClearEditText et_title;
    private String videoPath, voicePath, picPath = "";
    private boolean picUpload, voiceUpload, videoUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petition_new);
        setTitleName("添加诉求");
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
                if (position + 1 > videoData.size()) {
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
                new VoicePlayClickListener(voiceData.get(position), (ImageView) view.findViewById(R.id.iv_voice), PetitionNewActivity.this).onClick(view);
            }
        });
        et_phone.setText(myPrefs.username().get());
    }

    @Click({R.id.ll_jointer,
            R.id.tv_address, R.id.tv_type, R.id.btn_ok})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_jointer:
                JointerChooseActivity_.intent(this).startForResult(7);
                break;
            case R.id.tv_type:
                if (typeData == null) {
                    getType();
                } else {
                    showTypeDailog();
                }
                break;
            case R.id.btn_ok:
                beforeAdd();
                break;
            case R.id.tv_address:
                showAddressDialog();
                break;
        }
    }

    private void beforeAdd() {
        String phone = et_phone.getText().toString();
        String petitionDetail = et_petitionDetail.getText().toString();
        String title = et_title.getText().toString();
        if ("".equals(title)) {
            MyToastUtils.showToast("请输入诉求标题");
            return;
        }
        if ("".equals(petitionDetail)) {
            MyToastUtils.showToast("请输入述求详情");
            return;
        }
        if (typeId.length() == 0) {
            MyToastUtils.showToast("请选择诉求类别");
            return;
        }
        if (areaID.length() == 0) {
            MyToastUtils.showToast("请选择事发地点");
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
            List<LocalMedia> voiceStringData = new ArrayList<>();
            for (int i = 0; i < voiceData.size(); i++) {
                voiceStringData.add(voiceData.get(i));
            }
            upLoadFile(voiceStringData, 3);
            return;
        }
        if (!picUpload && !voiceUpload && !videoUpload) {
            String phone = et_phone.getText().toString();
            String petitionDetail = et_petitionDetail.getText().toString();
            String title = et_title.getText().toString();
            petitionAdd(title, petitionDetail, phone);
        }
    }

    private String associateId = "";

    private void petitionAdd(String title, String content, String phone) {
        showMyDialog("请稍后");
        PetitionAddParam petitionAddParam = new PetitionAddParam(
                Integer.parseInt(typeId),
                phone, content, title,
                areaID, associateId,
                picPath, videoPath, voicePath);
        Call<StringResult> call = apiService.petitionAdd(petitionAddParam);
        call.enqueue(new MyCallback<StringResult>() {
            @Override
            public void onSuccessRequest(StringResult result) {
                Log.e("aaa", "petitionAdd success   " + result.toString());
                MyToastUtils.showToast(result.getDesc().getDescription());
                if ("3".equals(result.getDesc().getCode())) {
                    finish();
                }
            }

            @Override
            public void afterRequest() {
                hideDialog();
            }

            @Override
            public void onFailureRequest(Call<StringResult> call, Throwable t) {
                Log.e("aaa", "petitionAdd onFailureRequest   " + t.toString());
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
        httpUtils.send(HttpRequest.HttpMethod.POST, Mate.UPLOAD, params, new RequestCallBack<String>() {
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
                    hideDialog();
                    MyToastUtils.showToast("上传文件失败");
                }
            }

            @Override
            public void onFailure(HttpException e, String msg) {
                Log.e("aaa", "文件上传失败 " + msg + "   " + e.getMessage() + "   " + e.getExceptionCode());
                hideDialog();
                MyToastUtils.showToast("文件上传失败");
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

    private List<PetitionType> typeData = null;

    @Background
    void getType() {

        Call<PetitionTypeResult> call = apiService.getType();
        call.enqueue(new MyCallback<PetitionTypeResult>() {

            @Override
            public void onSuccessRequest(PetitionTypeResult result) {
                if ("1".equals(result.getDesc().getCode())) {
                    typeData = result.getMessage();
                    showTypeDailog();
                } else {
                    MyToastUtils.showToast(result.getDesc().getDescription());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<PetitionTypeResult> call, Throwable t) {
                Log.e("aaa", " onFailure " + t.getMessage() + t.getCause());
            }
        });

    }

    private String typeId = "";

    private void showTypeDailog() {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dialog_petition_type);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.CENTER);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this));
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        ListView lv_children = (ListView) dialog.getCustomView().findViewById(R.id.lv_children);
        ListView lv_parent = (ListView) dialog.getCustomView().findViewById(R.id.lv_parent);
        final TypeParentAdapter parentAdapter = new TypeParentAdapter(this, new ArrayList<PetitionType>());
        lv_parent.setAdapter(parentAdapter);
        final TypeChildrenAdapter childrenAdapter = new TypeChildrenAdapter(this, new ArrayList<PetitionType.ChildrenBean>());
        lv_children.setAdapter(childrenAdapter);
        parentAdapter.setData(typeData);
        childrenAdapter.setData(typeData.get(0).getChildren());
        lv_parent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == typeData.size() - 1) {
                    ((TextView) findViewById(R.id.tv_type)).setText(parentAdapter.getData().get(position).getName());
                    typeId = parentAdapter.getData().get(position).getId();
                    dialog.dismiss();
                } else {
                    childrenAdapter.setData(typeData.get(position).getChildren());
                }
            }
        });
        lv_children.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) findViewById(R.id.tv_type)).setText(childrenAdapter.getData().get(position).getName());
                typeId = childrenAdapter.getData().get(position).getId();
                dialog.dismiss();
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
                        // voiceData.add(voiceBean);
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
        // 例如 LocalMedia 里面返回三种path
        // 1.media.getPath(); 为原图path
        // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
        // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
        // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
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
                case 7: //联名人 id s
                    Bundle bundle = data.getExtras();
                    List<AssociaterBean> chosedData = (List<AssociaterBean>) bundle.getSerializable("choosedata");
                    String name = "";
                    String id = "";
                    for (int i = 0; i < chosedData.size(); i++) {
                        name += chosedData.get(i).getName() + " ";
                        id += chosedData.get(i).getId() + ",";
                    }
                    associateId = id;
                    tv_jointer.setText(name);
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
