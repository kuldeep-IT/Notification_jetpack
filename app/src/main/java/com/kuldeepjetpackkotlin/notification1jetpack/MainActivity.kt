package com.kuldeepjetpackkotlin.notification1jetpack

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val channelId = "com.kuldeepjetpackkotlin.notification1jetpack.Notification"
    private var notificationManager: NotificationManager? = null

    private val KEY_REPLY = "KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

     createNotificationChannel(channelId, "HEY payAttention", "Have a nice day!")

        button.setOnClickListener {

            displayNotification()

        }

    }

    private fun displayNotification() {

        val notificationId = 45 //random

        val tapOnNotification = Intent(this, SecondActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            tapOnNotification,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        //reply action
        val remoteInput: RemoteInput = RemoteInput.Builder(KEY_REPLY).run {

            setLabel("Insert your message")
            build()

        }

        val replyAction: NotificationCompat.Action = NotificationCompat.Action.Builder(

            0,
            "Reply",
            pendingIntent
        ).addRemoteInput(remoteInput)
            .build()


        //action 1
        val intent2 = Intent(this, DetailsActivity::class.java)
        val pendingIntent2: PendingIntent = PendingIntent.getActivity(
            this,
            1,
            intent2,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action1:NotificationCompat.Action = NotificationCompat.Action
            .Builder(0,"Details",pendingIntent2).build()

        //action 2
        val intent3 = Intent(this, SettingActivity::class.java)
        val pendingIntent3: PendingIntent = PendingIntent.getActivity(
            this,
            1,
            intent3,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action2:NotificationCompat.Action = NotificationCompat.Action
            .Builder(0,"Settings",pendingIntent3).build()

        //action 3
        val intent4 = Intent(this, SecondActivity::class.java)
        val pendingIntent4: PendingIntent = PendingIntent.getActivity(
            this,
            1,
            intent4,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action3:NotificationCompat.Action = NotificationCompat.Action
            .Builder(0,"Second",pendingIntent4).build()



        val notification = NotificationCompat.Builder(this,channelId)
            .setContentTitle("HEYAAAAA")
            .setContentText("WHATS UP")
            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setContentIntent(pendingIntent)
            .addAction(action1)
            .addAction(action2)
//            .addAction(action3)
            .addAction(replyAction)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager?.notify(notificationId,notification)
    }

    private fun createNotificationChannel(id: String, name: String, channelDiscription:String)
    {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(id, name, importance).apply {

                description = channelDiscription
            }

            notificationManager?.createNotificationChannel(channel)

        }

    }
}