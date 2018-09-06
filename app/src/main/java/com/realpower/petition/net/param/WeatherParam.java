package com.realpower.petition.net.param;

/**
 * Created by ruipu on 2018/6/26.
 */

public class WeatherParam {
    String city;

    public WeatherParam(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
