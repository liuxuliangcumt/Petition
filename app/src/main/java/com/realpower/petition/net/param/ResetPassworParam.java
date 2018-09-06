package com.realpower.petition.net.param;

/**
 * Created by Administrator on 2017/12/22.
 */

public class ResetPassworParam {

    /**
     * code : string
     * phone : string
     * monitoredPwd : string
     */

    private String code;
    private String phone;
    private String monitoredPwd;

    public ResetPassworParam(String code, String phone, String monitoredPwd) {
        this.code = code;
        this.phone = phone;
        this.monitoredPwd = monitoredPwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMonitoredPwd() {
        return monitoredPwd;
    }

    public void setMonitoredPwd(String monitoredPwd) {
        this.monitoredPwd = monitoredPwd;
    }
}
