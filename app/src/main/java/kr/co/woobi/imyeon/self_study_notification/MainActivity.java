package kr.co.woobi.imyeon.self_study_notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createNotification(View view) {
        show();
    }

    private void show() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("알림 제목");
        builder.setContentText("알림 세부 텍스트");
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent,PendingIntent.FLAG_UPDATE_CURRENT );
        builder.setContentIntent(pendingIntent);

        Bitmap largeIcon=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        builder.setLargeIcon(largeIcon);
        builder.setColor(Color.RED);
        Uri ringtoneUri=RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(ringtoneUri);
        long[] vibrate = {0, 100, 200, 300};
        builder.setVibrate(vibrate);
        builder.setAutoCancel(true);

        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }
        manager.notify(1, builder.build());
    }

    public void removeNotification(View view) {
        hide();
    }

    private void hide() {
        NotificationManagerCompat.from(this).cancel(1);
    }
}
