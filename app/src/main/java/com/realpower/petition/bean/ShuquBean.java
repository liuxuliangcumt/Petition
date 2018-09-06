package com.realpower.petition.bean;

/**
 * Created by Administrator on 2017/12/18.
 */

public class ShuquBean {

    /**
     * appealId : 10
     * appealCategory : 0
     * appealInfo : 打击标题党
     * appealNum : 395001
     * appealContactInformation : 17310373503
     * currentStatus : 1
     */

    private int appealId;
    private int appealCategory;
    private String appealInfo;
    private String appealNum;
    private String appealContactInformation;
    private int currentStatus;
    /**
     * appealTitle : 这是测试诉求标题
     * createtime : 2017-12-20 13:54:01
     * appealAddressCode : 130502001
     */

    private String appealTitle;
    private String createtime;

    public int getAppealId() {
        return appealId;
    }

    public void setAppealId(int appealId) {
        this.appealId = appealId;
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

    public String getAppealNum() {
        return appealNum;
    }

    public void setAppealNum(String appealNum) {
        this.appealNum = appealNum;
    }

    public String getAppealContactInformation() {
        return appealContactInformation;
    }

    public void setAppealContactInformation(String appealContactInformation) {
        this.appealContactInformation = appealContactInformation;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }

    @Override
    public String toString() {
        return "ShuquBean{" +
                "appealId=" + appealId +
                ", appealCategory=" + appealCategory +
                ", appealInfo='" + appealInfo + '\'' +
                ", appealNum='" + appealNum + '\'' +
                ", appealContactInformation='" + appealContactInformation + '\'' +
                ", currentStatus=" + currentStatus +
                '}';
    }

    public String getAppealTitle() {
        return appealTitle;
    }

    public void setAppealTitle(String appealTitle) {
        this.appealTitle = appealTitle;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
