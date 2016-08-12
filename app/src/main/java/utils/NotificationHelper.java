package utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;

import com.android.lavamusic.R;

/**
 * Created by zhongrong.yu on 2016/6/17.
 */
public class NotificationHelper {

public static void popNotification(Context context){

 NotificationManager manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    AudioManager audioManager= (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    Notification notification = new Notification.Builder(context)
            .setAutoCancel(true)
            .setContentTitle("title")
            .setContentText("describe")
            .setSmallIcon(R.drawable.ic_appwidget_music_play)
            .setWhen(System.currentTimeMillis())
            .setDefaults(Notification.DEFAULT_SOUND)
            .build();


    manager.notify(111,notification);
    audioManager.setSpeakerphoneOn(true);

}

}
