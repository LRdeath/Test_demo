package com.example.administrator.text1.test_media;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.test.suitebuilder.TestMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RemoteViews;

import com.example.administrator.text1.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.tag;

/**
 * Created by Vzc on 2017/5/12.
 */

public class Test_Media extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView listView;
    private ImageButton start;
    private ImageButton stop;
    private ImageButton pause;
    private Integer cur_uri = null;
    private MusicManger manger;
    private ArrayList<String> li = new ArrayList<>();
    private int[] imgs = {R.mipmap.pause, R.mipmap.start};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_media);
        listView = (ListView) findViewById(R.id.lv_music);
        start = (ImageButton) findViewById(R.id.btn_media_start);
        stop = (ImageButton) findViewById(R.id.btn_media_last);
        pause = (ImageButton) findViewById(R.id.btn_media_next);
        if (manger == null) manger = MusicManger.getInstance(this);
        li.add("null");
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        pause.setOnClickListener(this);
        Observable.just(1)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .map(new Function<Integer, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(Integer integer) throws Exception {

                        ArrayList<String> l =(ArrayList<String>) manger.scanMusic(Test_Media.this.getContentResolver());
                        Log.e("MUSIC", "!!!" + l.toString() + "!!!");
                        System.out.println("!!!" + l.toString() + "!!!");
                        return l;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(subscriber);
        listView.setOnItemClickListener(this);
       initNotifiction();

    }

    Observer<ArrayList<String>> subscriber = new Observer<ArrayList<String>>() {

        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(ArrayList<String> value) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(Test_Media.this, android.R.layout.simple_list_item_1, value);
            Log.e("MUSIC", "!!!" + value.toString() + "!!!");
            listView.setAdapter(adapter);
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    };


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(Test_Media.this, MediaService.class);
        cur_uri = position;
        start.setImageResource(imgs[1]);
        intent.putExtra("URI", position);
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_media_start:
                if (!manger.isPlaying()) {
                    start.setImageResource(imgs[1]);
                    manger.play(manger.getCurtentIndex());
                } else {
                    start.setImageResource(imgs[0]);
                    manger.pause();
                }
                break;
            case R.id.btn_media_last:
                manger.last();
                break;
            case R.id.btn_media_next:
                manger.next();
                break;
        }
    }

    //初始化通知
    private void initNotifiction() {
        //android进行通知处理，需要从系统获取通知管理器NotifitionManager，他是系统的一个service
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //由于通知布局不在app中显示，需要使用远程view这种方式来设置
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notifation_music);
        //设置通知栏 播放、下一首、上一首 的监听
        PendingIntent play_intent = PendingIntent.getBroadcast(this, 0,
                new Intent(MusicBroadcast.MUSIC_PLAY), 0);
        remoteViews.setOnClickPendingIntent(R.id.noti_media_start, play_intent);

        PendingIntent next_intent = PendingIntent.getBroadcast(this, 1,
                new Intent(MusicBroadcast.MUSIC_NEXT), 0);
        remoteViews.setOnClickPendingIntent(R.id.noti_media_next, next_intent);

        PendingIntent last_intent = PendingIntent.getBroadcast(this, 2,
                new Intent(MusicBroadcast.MUSIC_LAST), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.noti_media_last, last_intent);
        //注册命令接收器
        MusicReceiver receiver = new MusicReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MusicBroadcast.MUSIC_PLAY);
        intentFilter.addAction(MusicBroadcast.MUSIC_LAST);
        intentFilter.addAction(MusicBroadcast.MUSIC_NEXT);
        registerReceiver(receiver,intentFilter);

        //装载自定义通知
        PendingIntent notify_intent = PendingIntent.getBroadcast(this, 3,
                new Intent(this, Test_Media.class), 0);//点击通知的跳转逻辑
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder.build();
        notification.contentView  = remoteViews;
        notification.icon = R.mipmap.music;
        notification.flags =Notification.FLAG_ONGOING_EVENT;//设置通知参数

        //绑定Media组件
        manger.setNotification(notification);
        manger.setNfManager(notificationManager);
        manger.setRemotoView(remoteViews);

        notificationManager.notify(0,notification);

    }


}
