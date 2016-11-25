package com.example.administrator.text1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/10/22.
 */
public class Test_Asynctask extends Activity {
    private Button start;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_asynctask);
        dialog = new ProgressDialog(this);
        start = (Button) findViewById(R.id.btn_asynctask);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new myAsynctask().execute();
            }
        });
    }

    class myAsynctask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            double i = 0;
            while (i <= 100) {
                i += 0.01;
                int a = (int) i;
                publishProgress(a);
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            dialog.setMessage("当前下载进度：" + values[0] + "%");
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            dialog.cancel();
            if(aBoolean){
                Toast.makeText(Test_Asynctask.this,"下载成功",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
