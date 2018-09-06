package com.realpower.petition.bean;

/**
 * Created by Administrator on 2017/12/20.
 */

public class PetitionDetailBean {

    /**
     * appealContactInformation : string
     * appealTitle : string
     * appealCategory : 0
     * appealInfo : string
     * appealVoiceUrl : string
     * currentStatus : 1 待解决  2 解决中  3 已解决
     * appealNum : 395001
     * appealAddressCode : string
     * appealAssociateId : string
     * appealImageUrl : string
     * appealId : 12
     * appealVideoUrl : string
     */

    /**
     * createtime : 2018-01-25 17:46:58
     * appealStatus : 1//0 未打分  1 已经打分
     * appealCommentContent : 来看看
     * appealReminders : 1
     * appealRemindersLasttime : 1516874502000
     * appealCommentLevel : 5
     */

    private String createtime;
    private int appealStatus;
    private String appealCommentContent;
    private int appealReminders;
    private long appealRemindersLasttime;
    private int appealCommentLevel;
    private String appealContactInformation;
    private String appealTitle;
    private int appealCategory;
    private String appealInfo;
    private String appealVoiceUrl = "";
    private int currentStatus;
    private String appealNum;
    private String appealAddressCode;
    private String appealAssociateId;
    private String appealImageUrl = "";
    private int appealId;
    private String appealVideoUrl = "";

    /**
     * createtime : 2017-12-16 16:50:35
     * address : 邢台市桥东区南长街街道
     * associateName : string, 刘旭亮
     * category : 山林土地权属纠纷土地权属纠纷
     */

    private String address;
    private String category;
    /**
     * createtime : 2017-12-16 16:50:35
     * associateName : string, 刘旭亮
     */

    private String associateName;

    public String getAppealContactInformation() {
        return appealContactInformation;
    }

    public void setAppealContactInformation(String appealContactInformation) {
        this.appealContactInformation = appealContactInformation;
    }

    public String getAppealTitle() {
        return appealTitle;
    }

    public void setAppealTitle(String appealTitle) {
        this.appealTitle = appealTitle;
    }

    public int getAppealCategory() {
        return appealCategory;
    }

    public void setAppealCategory(int appealCategory) {
        this.appealCategory = appealCategory;
    }

    public String getAppealInfo() {
        return appealInfo;
    }

    public void setAppealInfo(String appealInfo) {
        this.appealInfo = appealInfo;
    }

    public String getAppealVoiceUrl() {
        return appealVoiceUrl;
    }

    public void setAppealVoiceUrl(String appealVoiceUrl) {
        this.appealVoiceUrl = appealVoiceUrl;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getAppealNum() {
        return appealNum;
    }

    public void setAppealNum(String appealNum) {
        this.appealNum = appealNum;
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

    public String getAppealImageUrl() {
        return appealImageUrl;
    }

    public void setAppealImageUrl(String appealImageUrl) {
        this.appealImageUrl = appealImageUrl;
    }

    public int getAppealId() {
        return appealId;
    }

    public void setAppealId(int appealId) {
        this.appealId = appealId;
    }

    public String getAppealVideoUrl() {
        return appealVideoUrl;
    }

    public void setAppealVideoUrl(String appealVideoUrl) {
        this.appealVideoUrl = appealVideoUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getAppealStatus() {
        return appealStatus;
    }

    public void setAppealStatus(int appealStatus) {
        this.appealStatus = appealStatus;
    }

    public String getAppealCommentContent() {
        return appealCommentContent;
    }

    public void setAppealCommentContent(String appealCommentContent) {
        this.appealCommentContent = appealCommentContent;
    }

    public int getAppealReminders() {
        return appealReminders;
    }

    public void setAppealReminders(int appealReminders) {
        this.appealReminders = appealReminders;
    }

    public long getAppealRemindersLasttime() {
        return appealRemindersLasttime;
    }

    public void setAppealRemindersLasttime(long appealRemindersLasttime) {
        this.appealRemindersLasttime = appealRemindersLasttime;
    }

    public int getAppealCommentLevel() {
        return appealCommentLevel;
    }

    public void setAppealCommentLevel(int appealCommentLevel) {
        this.appealCommentLevel = appealCommentLevel;
    }
}
