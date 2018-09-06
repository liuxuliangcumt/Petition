package com.realpower.petition.net.param;

import com.baidu.mapapi.map.BaiduMap;

/**
 * Created by Administrator on 2017/12/26.
 */

public class AddGpsParam {
    private String latitude;
    private String longitude;
    private String gpsTime;
    private String phone;

    public AddGpsParam(String latitude, String longitude,
                       String phone) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
    }

}
