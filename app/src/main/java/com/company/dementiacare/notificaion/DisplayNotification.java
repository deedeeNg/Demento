/*
 *          Displaying notification
 *
 *  Description: A model of the notification messages
 *
 *
 * updated: August 3rd 2022
 */
package com.company.dementiacare.notificaion;

import static android.provider.Settings.System.DEFAULT_NOTIFICATION_URI;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.company.dementiacare.R;

public class DisplayNotification extends ContextWrapper {

    /*
     * Constructor to get the context
     * */
    public DisplayNotification(Context base) {
        super(base);
    }

    /*
     * Function to create Notifications.
     * Input Required for this function- Message Details and the Description to be displayed on the notification
     * */
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createNotification(String messageDetails, String descriptionDetails, String patientDetails,
                                   String colorDetails, String typeDetails, String medUnit) {

        /*
         * Notifications for Android Oreo and above versions (they require channel creations)
         * */

        StringBuilder listOfDetails = new StringBuilder();
        listOfDetails.append(String.format(patientDetails, colorDetails, typeDetails));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // The id of the channel
            String id = "my_channel_01";

            // The user-visible name of the channel.
            CharSequence name = "Demento";

            //This will provide the ringtone and vibration
            int importance = NotificationManager.IMPORTANCE_HIGH;

            //Creating the channel
            NotificationChannel mChannel = null;

            mChannel = new NotificationChannel(id, name, importance);

            /* Configuring the notification channel */
            mChannel.setDescription("The Channel Demento uses notifications access for ring and vibrate.");
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.setSound(mChannel.getSound(), mChannel.getAudioAttributes());
            mChannel.enableVibration(true);
            mChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            /*Notification manager to fetch the context and the notification service*/
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(mChannel);

            // The id of the Notification channel
            String channelId = "my_channel_01";

            // Creating a notification and setting the notification channel.
            Notification notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle("Patient: " + patientDetails)
                    .setContentText("Medicine: " + messageDetails  + System.getProperty("line.separator") +"Dosage: " + descriptionDetails + " " + medUnit +". Click to view details")
                    .setSmallIcon(R.drawable.logo3).setSound(DEFAULT_NOTIFICATION_URI)
                    .setChannelId(channelId)
                    .setAutoCancel(true)
                    .setSound(mChannel.getSound(), mChannel.getAudioAttributes())
                    .build();

            /* Notification manager to fetch the context and the notification service.
            * * */
            notification.contentIntent=  PendingIntent.getActivity(this, 0,
                    new Intent(this, NotificationActivity.class).putExtra("name",messageDetails).putExtra("dosage",descriptionDetails)
                            .putExtra("patient", patientDetails).putExtra("color", colorDetails).putExtra("type", typeDetails).putExtra("unit", medUnit)
                    , PendingIntent.FLAG_CANCEL_CURRENT);
            // Issuing the notification.
            mNotificationManager.notify(001, notification);
        }

        /*
         * Notifications for Android Nougat and below versions (they do not require channel creations)
         */
        else {
            //Creating the notification
            Notification notification =
                    new NotificationCompat.Builder(this.getApplicationContext())
                            .setSmallIcon(R.drawable.logo3)
                            .setContentTitle("Patient: " + patientDetails)
                            .setContentText("Medicine: " + messageDetails  + System.getProperty("line.separator") +"Dosage: " + descriptionDetails + " " + medUnit +". Click to view details")
                            .setAutoCancel(true)
                            .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                            .setSound(DEFAULT_NOTIFICATION_URI)
                            .build();

            /*Intent for a new class by clicking the notification */
            //Intent notificationIntent = new Intent(this, NotificationDisplayMedicine.class);
            notification.contentIntent=  PendingIntent.getActivity(this, 0,
                    new Intent(this, NotificationActivity.class).putExtra("name",messageDetails).putExtra("dosage",descriptionDetails)
                            .putExtra("patient", patientDetails).putExtra("color", colorDetails).putExtra("type", typeDetails).putExtra("unit", medUnit)
                    , PendingIntent.FLAG_CANCEL_CURRENT);

            /*
            *Notification manager to fetch the context and the notification service.
            */
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            /*Issuing the notification*/
            mNotificationManager.notify(001, notification);
        }
        /*Call to Create a Flashlight Notification along with the normal notification*/
        EnableFlashLight enableFlashLight = new EnableFlashLight(this);
        enableFlashLight.enableFlash();
    }
}