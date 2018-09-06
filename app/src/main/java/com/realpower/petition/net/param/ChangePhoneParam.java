package com.realpower.petition.net.param;

/**
 * Created by Administrator on 2017/12/23.
 */

public class ChangePhoneParam {

    /**
     * areaId : 0
     * code : string
     * monitoredAddress : string
     * monitoredAssessStatus : 0
     * monitoredId : 0
     * monitoredIdcard : string
     * monitoredPhone : string
     * monitoredPwd : string
     * monitoredRealname : string
     * permanentAddress : string
     * phone : string
     * sysUserId : 0
     */
    private String monitoredPhone;//原手机号
    private String code;
    private String monitoredPwd;
    private String phone;

    public ChangePhoneParam(String monitoredPhone, String code, String monitoredPwd, String phone) {
        this.monitoredPhone = monitoredPhone;
        this.code = code;
        this.monitoredPwd = monitoredPwd;
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMonitoredPwd() {
        return monitoredPwd;
    }

    public void setMonitoredPwd(String monitoredPwd) {
        this.monitoredPwd = monitoredPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
