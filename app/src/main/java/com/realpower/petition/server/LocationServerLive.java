package com.realpower.petition.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.realpower.petition.keepalive.AbsWorkService;
import com.realpower.petition.net.ApiService;
import com.realpower.petition.net.Mate;
import com.realpower.petition.net.param.AddGpsParam;
import com.realpower.petition.net.result.StringResult;
import com.realpower.petition.util.SystemInfoUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationServerLive extends AbsWorkService {
    public static boolean sShouldStopService;

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Mate.API)
            .addConverterFactory(GsonConverterFactory.create())
            // .client(OkHttpClientUtil.getOkHttpClient("token"))
            .build();
    public static ApiService apiService = retrofit.create(ApiService.class);

    private BaiduSDKReceiver mBaiduReceiver;
    private String username = "";
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e("aaa", "LocationServer  Handler msg====1");
                    if (mLocClient != null) {
                        mLocClient.requestLocation();
                    }
                    handler.sendEmptyMessageDelayed(1, 1000 * 5 * 60);
                    break;
            }
            return false;
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 8C:0D:76:B7:22:0E    58:02:03:04:05:06
        Log.e("aaa", "LocationServer onCreate");
        isStartUpdate = false;
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mBaiduReceiver = new BaiduSDKReceiver();
        registerReceiver(mBaiduReceiver, iFilter);
        initLocation();
    }

    @Override
    public void onDestroy() {
        isStartUpdate = false;
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }


    @Override
    public Boolean shouldStopService(Intent intent, int flags, int startId) {
        return null;
    }

    @Override
    public void startWork(Intent intent, int flags, int startId) {

    }

    @Override
    public void stopWork(Intent intent, int flags, int startId) {

    }

    @Override
    public Boolean isWorkRunning(Intent intent, int flags, int startId) {
        return null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent, Void alwaysNull) {
        return null;
    }

    @Override
    public void onServiceKilled(Intent rootIntent) {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("aaa", "LocationServer onStartCommand  " + SystemInfoUtils.getNewMac() + "  \n  "
                + SystemInfoUtils.getLocalMacAddressFromIp());
        username = intent.getStringExtra("username");
        if (null != username) {
            if (!isStartUpdate || username.length() == 11) {
                isStartUpdate = true;
                Log.e("aaa", "onStartCommand 进入 handler 发送消息" + handler.toString());
                // handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessage(1);
            }
        }
        Log.e("aaa", "LocationServer onStartCommand   username  " + username);
        return super.onStartCommand(intent, flags, startId);
    }

    private static boolean isStartUpdate = false;

    public LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();

    private void initLocation() {
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// open gps
        //        option.setCoorType("gcj02");
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();

        Log.e("aaa", "LocationServer   initLocation");
    }

    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.e("aaa", "定位监听 ");

            if (location == null) {
                return;
            }
            LatLng llA = new LatLng(location.getLatitude(), location.getLongitude());

            //40.046984   116.305778   40.046859   116.30556
            Log.e("aaa", "LocationServer信访通定位成功  "
                    + llA.longitude + "," + llA.latitude +
                    "  纠偏钱  " + location.getLongitude() + "   " + location.getLatitude());
            if (llA.latitude != 4.9E-324D) {
                upDataGps(llA.latitude, llA.longitude);
            } else {
                Log.e("aaa", "定位失败");
            }

        }
    }

    private void upDataGps(double latitude, double longitude) {

        Call<StringResult> call = apiService.addGps(new AddGpsParam(latitude + "", longitude + "", username));
        call.enqueue(new Callback<StringResult>() {
            @Override
            public void onResponse(Call<StringResult> call, Response<StringResult> response) {

            }

            @Override
            public void onFailure(Call<StringResult> call, Throwable t) {

            }

        });

    }


    public class BaiduSDKReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            String st1 = "网络出错";
            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {

                String st2 = "检查网络错误";
                // MyToastUtils.showToast(st2);
            } else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {

                Log.e("aaa", "百度地图 " + st1);
            }
        }
    }


}