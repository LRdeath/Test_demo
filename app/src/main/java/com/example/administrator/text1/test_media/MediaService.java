package com.example.administrator.text1.test_media;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by Vzc on 2017/5/12.
 */

public class MediaService extends Service{
    MusicManger manger;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (manger==null){
            manger = MusicManger.getInstance(this);
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int uri = intent.getIntExtra("URI",0);
        manger.play(uri);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manger.stop();
        manger=null;
    }


}
