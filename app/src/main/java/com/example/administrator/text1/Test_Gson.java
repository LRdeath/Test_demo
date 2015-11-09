package com.example.administrator.text1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.text1.retrofit.Restwork;
import com.example.administrator.text1.retrofit.Weather;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Test_Gson extends ActionBarActivity implements View.OnClickListener {
    private Button btn_get;
    private Button btn_parse;
    private TextView tv_get;
    private TextView tv_parse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_retrofit);
        btn_get = (Button) findViewById(R.id.btn_getjson);
        btn_parse = (Button) findViewById(R.id.btn_parse_json);
        tv_get = (TextView) findViewById(R.id.tv_json);
        tv_parse = (TextView) findViewById(R.id.tv_show);
        btn_get.setOnClickListener(this);
        btn_parse.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_getjson:
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("http://www.weather.com.cn/adat/sk")
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .build();
                Restwork restwork = restAdapter.create(Restwork.class);
                restwork.mweather(new Callback<Weather>() {
                    @Override
                    public void success(Weather weather, Response response) {
                       if (weather.getWeatherinfo()!= null){
                        tv_get.setText(weather.getWeatherinfo().getCity()+weather.getWeatherinfo().getTemp());
                       }else tv_get.setText("Error:NULL");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        tv_get.setText(error.getMessage().toString());
                    }
                });

                break;
            case R.id.btn_parse_json:
                break;
        }
    }
}
