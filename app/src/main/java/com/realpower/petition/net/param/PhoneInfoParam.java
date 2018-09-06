package com.realpower.petition.net.param;

/**
 * Created by Administrator on 2017/12/27.
 */

public class PhoneInfoParam {
    /**
     * infoId : 0
     * monitoredId : string
     * phoneEmia : string
     * phoneMic : string
     * phoneNum : string
     */
    public PhoneInfoParam(String phoneEmia, String phoneMic) {
        this.phoneEmia = phoneEmia;
        this.phoneMic = phoneMic;
    }

    private String phoneEmia;
    private String phoneMic;
    private String phoneNum;

    public String getPhoneEmia() {
        return phoneEmia;
    }

    public void setPhoneEmia(String phoneEmia) {
        this.phoneEmia = phoneEmia;
    }

    public String getPhoneMic() {
        return phoneMic;
    }

    public void setPhoneMic(String phoneMic) {
        this.phoneMic = phoneMic;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
