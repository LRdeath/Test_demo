package com.example.administrator.text1.test_media;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.administrator.text1.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vzc on 2017/5/12.
 */

public class MusicManger {
    private static MusicManger instance;
    private Context mContext;
    private int curtentIndex = 0;
    private MediaPlayer mp;
    private boolean isPause = false;
    private List<String> list_uri = new ArrayList<>();
    private List<String> list_title = new ArrayList<>();
    private NotificationManager notificationManager;
    private Notification notification;
    private RemoteViews mRemoteView;
    private List<String> list_artist = new ArrayList<>();

    private MusicManger(Context context) {
        mContext = context;
        mp = new MediaPlayer();
    }

    public static MusicManger getInstance(Context context) {
        if (instance == null) instance = new MusicManger(context);
        return instance;
    }

    public void play(int position) {
        if (position < 0 || position >= getsize()) return;
        if (list_uri.isEmpty() ) return;
        if (isPause)mp.start();
        else {
            try {
                mp.reset();
                curtentIndex=position;
                mp.setDataSource(list_uri.get(position));
                mp.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }
            mp.start();
        }
        isPause=false;
        if (mRemoteView!=null){
            mRemoteView.setTextViewText(R.id.tv_noti_song,list_title.get(position));
            mRemoteView.setTextViewText(R.id.tv_noti_artist,list_artist.get(position));
            mRemoteView.setImageViewResource(R.id.noti_media_start,R.mipmap.start);
            if (notification!=null&&notificationManager!=null)notificationManager.notify(0,notification);
        }


    }
//暂停
    public void pause() {
        mp.pause();
        isPause = true;
        if (mRemoteView!=null){
            mRemoteView.setImageViewResource(R.id.noti_media_start,R.mipmap.pause);
            if (notification!=null&&notificationManager!=null)notificationManager.notify(0,notification);
        }
    }



    //读取音乐列表
    public List<String> scanMusic(ContentResolver contentresolver) {
        if (!list_title.isEmpty()) {
            list_title.clear();
            list_uri.clear();
        }
        Cursor cursor = contentresolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        assert cursor != null;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            String uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            String author = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
            list_title.add(title);
            list_artist.add(author);
            list_uri.add(uri);
        }
        return list_title;
    }

    //下一首
    public void next() {
        //播放状态和暂停状态可以下一首
        if (mp.isPlaying() || isPause) {
            if (curtentIndex + 1 == getsize()) {
                curtentIndex = 0;
            } else curtentIndex++;
            play(curtentIndex);
        }

    }

    //上一首
    public void last() {
        if (mp.isPlaying() || isPause) {
            if (curtentIndex == 0) {
                curtentIndex = getsize() - 1;
            } else curtentIndex--;
            play(curtentIndex);
        }

    }
    public void stop(){
        mp.stop();
    }

    //读写器
    public int getsize() {
        return list_title.size();
    }
    public boolean isPlaying(){
        return mp.isPlaying();
    }
    public int getCurtentIndex(){return  curtentIndex;}

    public void setNfManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public void setRemotoView(RemoteViews remoteViews) {
        this.mRemoteView = remoteViews;
    }
}
