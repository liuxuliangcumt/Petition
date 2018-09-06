package com.realpower.petition.net.param;

/**
 * Created by Administrator on 2017/12/16.
 */

public class PetitionAddParam {

    /**
     * 诉求对象 {
     * appealAssociateId (string): 诉求关联联名人 ,
     * appealCategory (integer): 信访分类 ,
     * appealContactInformation (string): 诉求联系方式 ,
     * appealImageUrl (string): 图片链接 ,
     * appealInfo (string): 诉求详细信息 ,
     * appealVideoUrl (string): 视频链接 ,
     * appealVoiceUrl (string): 音频链接 ,
     * monitoredId (string): 被监控人id
     * appealAddressCode 发生地
     * }
     * 图片、视频、语音以及联名人id非必填；desc中1 必填参数为空，2新建失败，3新建成功
     */

    private String appealAssociateId;
    private int appealCategory;
    private String appealContactInformation;
    private String appealImageUrl;
    private String appealInfo;
    private String appealVideoUrl;
    private String appealVoiceUrl;
    private String appealTitle;
    private String appealAddressCode;

    public PetitionAddParam(int appealCategory,
                            String phone,
                            String appealInfo,
                            String title,
                            String appealAddressCode,
                            String appealAssociateId,
                            String appealImageUrl, String appealVideoUrl, String appealVoiceUrl ) {
        this.appealCategory = appealCategory;
        this.appealContactInformation = phone;
        this.appealInfo = appealInfo;
        this.appealTitle = title;
        this.appealAddressCode = appealAddressCode;
        this.appealAssociateId = appealAssociateId;
        this.appealImageUrl = appealImageUrl;
        this.appealVideoUrl = appealVideoUrl;
        this.appealVoiceUrl = appealVoiceUrl;
    }

    public String getAppealTitle() {
        return appealTitle;
    }

    public void setAppealTitle(String appealTitle) {
        this.appealTitle = appealTitle;
    }


    public String getTitle() {
        return appealTitle;
    }

    public void setTitle(String title) {
        this.appealTitle = title;
    }

    public String getAppealAddressCode() {
        return appealAddressCode;
    }

    public void setAppealAddressCode(String appealAddressCode) {
        this.appealAddressCode = appealAddressCode;
    }


    public String getAppealAssociateId() {
        return appealAssociateId;
    }

    public void setAppealAssociateId(String appealAssociateId) {
        this.appealAssociateId = appealAssociateId;
    }

    public int getAppealCategory() {
        return appealCategory;
    }

    public void setAppealCategory(int appealCategory) {
        this.appealCategory = appealCategory;
    }

    public String getAppealContactInformation() {
        return appealContactInformation;
    }

    public void setAppealContactInformation(String appealContactInformation) {
        this.appealContactInformation = appealContactInformation;
    }

    public String getAppealImageUrl() {
        return appealImageUrl;
    }

    public void setAppealImageUrl(String appealImageUrl) {
        this.appealImageUrl = appealImageUrl;
    }

    public String getAppealInfo() {
        return appealInfo;
    }

    public void setAppealInfo(String appealInfo) {
        this.appealInfo = appealInfo;
    }

    public String getAppealVideoUrl() {
        return appealVideoUrl;
    }

    public void setAppealVideoUrl(String appealVideoUrl) {
        this.appealVideoUrl = appealVideoUrl;
    }

    public String getAppealVoiceUrl() {
        return appealVoiceUrl;
    }

    public void setAppealVoiceUrl(String appealVoiceUrl) {
        this.appealVoiceUrl = appealVoiceUrl;
    }

}
