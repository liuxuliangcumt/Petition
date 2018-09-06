package com.realpower.petition.bean;

/**
 * Created by Administrator on 2017/12/22.
 */

public class SuggestionDetailBean {

    /**
     * suggestionNum : 9527
     * suggestionContactInformation : string
     * suggestionImageUrl : string
     * suggestionVoiceUrl : string
     * currentStatus : 1
     * suggestionId : 1
     * suggestionFinishedUrl : string
     * suggestionInfo : string
     * suggestionTitle : string
     * suggestionResult : string
     * suggestionVideoUrl : string
     */

    private String suggestionNum;
    private String suggestionContactInformation;

    private int currentStatus;
    private int suggestionId;
    private String suggestionFinishedUrl;
    private String suggestionTitle;
    private String suggestionInfo;
    private String suggestionImageUrl = "";
    private String suggestionVideoUrl = "";
    private String suggestionVoiceUrl = "";
    private String suggestionResult="";
    /**
     * suggestionReminders : 0
     * areaId : 130502001
     * suggestionCommentContent : 泪流满面胡沙满鬓风云变幻无常见面试图案件事故宫殿下午餐馆子弹簧钢铁路局面临时尚雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯泪流满面胡沙满鬓风云变幻无常见面试图案件事故宫殿下午餐馆子弹簧钢铁路局面临时尚雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯罗技嘉年华硕大泪流满面胡沙满鬓风云变幻无常见面试图案件事故宫殿下午餐馆子弹簧钢铁路局面临时尚雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯泪流满面胡沙满鬓风云变幻无常见面试图案件事故宫殿下午餐馆子弹簧钢铁路局面临时尚雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯雯
     * suggestionCommentLevel : 4
     */

    private int suggestionReminders;
    private int areaId;
    private String suggestionCommentContent="";
    private int suggestionCommentLevel;

    public String getSuggestionVoiceUrl() {
        return suggestionVoiceUrl;
    }

    public void setSuggestionVoiceUrl(String suggestionVoiceUrl) {
        this.suggestionVoiceUrl = suggestionVoiceUrl;
    }

    public String getSuggestionImageUrl() {
        return suggestionImageUrl;
    }

    public void setSuggestionImageUrl(String suggestionImageUrl) {
        this.suggestionImageUrl = suggestionImageUrl;
    }

    public String getSuggestionNum() {
        return suggestionNum;
    }

    public void setSuggestionNum(String suggestionNum) {
        this.suggestionNum = suggestionNum;
    }

    public String getSuggestionContactInformation() {
        return suggestionContactInformation;
    }

    public void setSuggestionContactInformation(String suggestionContactInformation) {
        this.suggestionContactInformation = suggestionContactInformation;
    }


    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }

    public int getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(int suggestionId) {
        this.suggestionId = suggestionId;
    }

    public String getSuggestionFinishedUrl() {
        return suggestionFinishedUrl;
    }

    public void setSuggestionFinishedUrl(String suggestionFinishedUrl) {
        this.suggestionFinishedUrl = suggestionFinishedUrl;
    }

    public String getSuggestionInfo() {
        return suggestionInfo;
    }

    public void setSuggestionInfo(String suggestionInfo) {
        this.suggestionInfo = suggestionInfo;
    }

    public String getSuggestionTitle() {
        return suggestionTitle;
    }

    public void setSuggestionTitle(String suggestionTitle) {
        this.suggestionTitle = suggestionTitle;
    }

    public String getSuggestionResult() {
        return suggestionResult;
    }

    public void setSuggestionResult(String suggestionResult) {
        this.suggestionResult = suggestionResult;
    }

    public String getSuggestionVideoUrl() {
        return suggestionVideoUrl;
    }

    public void setSuggestionVideoUrl(String suggestionVideoUrl) {
        this.suggestionVideoUrl = suggestionVideoUrl;
    }

    public int getSuggestionReminders() {
        return suggestionReminders;
    }

    public void setSuggestionReminders(int suggestionReminders) {
        this.suggestionReminders = suggestionReminders;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getSuggestionCommentContent() {
        return suggestionCommentContent;
    }

    public void setSuggestionCommentContent(String suggestionCommentContent) {
        this.suggestionCommentContent = suggestionCommentContent;
    }

    public int getSuggestionCommentLevel() {
        return suggestionCommentLevel;
    }

    public void setSuggestionCommentLevel(int suggestionCommentLevel) {
        this.suggestionCommentLevel = suggestionCommentLevel;
    }
}
