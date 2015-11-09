package com.example.administrator.text1.test_baidumap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.text1.R;

/**
 * Created by Administrator on 2015/11/6.
 */
public class Test_baidumap extends Activity {
    private Button btn_map;
    private LocationClient locationClient;
    private MyLocationListener myLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_baidumap);
        btn_map = (Button) findViewById(R.id.btn_test_baidumap);
        locationClient = new LocationClient(this);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedLocationDescribe(true);
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        locationClient.setLocOption(option);
        myLocationListener = new MyLocationListener();
        locationClient.registerLocationListener(myLocationListener);

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_map.getText().equals(getString(R.string.map))){
                    btn_map.setText(getString(R.string.map_ing));
                    locationClient.start();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationClient.stop();
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            StringBuffer sb = new StringBuffer(256);
            sb.append(bdLocation.getLocationDescribe());
            if(btn_map != null){
                btn_map.setText(sb.toString());
            }
        }
    }
}
