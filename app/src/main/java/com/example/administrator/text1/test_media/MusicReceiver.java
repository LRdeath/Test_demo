package com.example.administrator.text1.test_media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.Switch;

/**
 * Created by Vzc on 2017/5/16.
 */

public class MusicReceiver extends BroadcastReceiver {
    private  MusicManger manger ;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (manger==null)manger=MusicManger.getInstance(context);
        String action = intent.getAction();
        switch (action){
            case MusicBroadcast.MUSIC_PLAY:
                if (manger.isPlaying())manger.pause();
                else manger.play(manger.getCurtentIndex());
                break;
            case MusicBroadcast.MUSIC_NEXT:
                manger.next();
                break;
            case MusicBroadcast.MUSIC_LAST:
                manger.last();
                break;
            default:break;
        }
    }
}
