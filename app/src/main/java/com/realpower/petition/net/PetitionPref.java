package com.realpower.petition.net;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.DefaultLong;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by Administrator on 2017/12/20.
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface PetitionPref {
    @DefaultString("")
    String token();//请求头 token

    @DefaultString("")
    String password();//登录密码

    @DefaultString("")
    String username();//登录账户

    @DefaultString("")
    String isShowAlive();
}