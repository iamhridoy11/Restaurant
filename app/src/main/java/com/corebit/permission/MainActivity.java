package com.corebit.permission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String CHANNEL_ID = "My notification_channel";
    public static final int NOTIFICATION_ID = 101;
    private Button tap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tap = (Button) findViewById(R.id.button2);
        tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tapIntent = new Intent(MainActivity.this, Permission.class);
                startActivity(tapIntent);
            }
        });
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(MainActivity.NOTIFICATION_ID);
    }

    public void displayNotification(View view) {
        createNotificationChannel();

        Intent permissionIntent = new Intent(this, Permission.class);
        permissionIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent permissionPendingIntent = PendingIntent.getActivity(this, 0, permissionIntent, PendingIntent.FLAG_ONE_SHOT);





        Intent permiIntent = new Intent(this,Permission.class);
        permiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent permiPendingIntent = PendingIntent.getActivity(this, 0, permiIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent noIntent = new Intent(this, MainActivity.class);
        noIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent noPendingIntent = PendingIntent.getActivity(this, 0, noIntent, PendingIntent.FLAG_ONE_SHOT);









        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_confirmation_number_black_24dp);
        builder.setContentTitle("Restaurant Found!!");
        builder.setContentText("Do You Want to explore?");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);
        builder.setContentIntent(permissionPendingIntent);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.resturent);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));


        builder.addAction(R.drawable.ic_yes, "Yes", permiPendingIntent);
        builder.addAction(R.drawable.ic_cancel, "No", noPendingIntent);


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel()
    {
       if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
       {
           CharSequence name = "Personal Notifications";
           String description = "Include all the personal notification";

           int importance = NotificationManager.IMPORTANCE_DEFAULT;

           NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);
           notificationChannel.setDescription(description);
           NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
           notificationManager.createNotificationChannel(notificationChannel);
       }

    }


}
