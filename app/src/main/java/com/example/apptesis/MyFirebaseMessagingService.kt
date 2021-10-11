package com.example.apptesis

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.apptesis.view.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import javax.annotation.Nonnull

class MyFirebaseMessagingService : FirebaseMessagingService() {

var id = 0;
    override fun onMessageReceived(@Nonnull remoteMessage: RemoteMessage) {
       Log.e("NOTIFY","IM HERE")
        val titulo = remoteMessage.notification!!.title
        val body = remoteMessage.notification!!.body
        Log.e("NOTIFY", remoteMessage.messageId!!)
        //val id = remoteMessage.messageId!!.toInt()
        val channelID = "com.example.apptesis"
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, channelID).apply {
                setAutoCancel(true)
                setSmallIcon(R.mipmap.ic_launcher)
                setVibrate(longArrayOf(1000, 1000, 1000, 1000))
                setPriority(NotificationCompat.PRIORITY_MAX)
                setContentIntent(pendingIntent)
                setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                setContentTitle(titulo)
                setContentText(body)
            }
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelID, "NOTIFY",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(id, builder.build())
        id=+1

        super.onMessageReceived(remoteMessage)

    }


}