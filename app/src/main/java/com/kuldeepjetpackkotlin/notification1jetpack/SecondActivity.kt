package com.kuldeepjetpackkotlin.notification1jetpack

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        receiveInput()
    }

    private fun receiveInput() {

        val KEY_REPLY = "KEY"

        val intent = this.intent

        val remoteInput = RemoteInput.getResultsFromIntent(intent)

        if (remoteInput!= null)
        {
            val inputString = remoteInput.getCharSequence(KEY_REPLY).toString()
            textV.text = inputString

            //for replied notification

            val channelId = "com.kuldeepjetpackkotlin.notification1jetpack.Notification"
            val notificationId = 45

            val repliedNotification = NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("Your reply received")
                .build()

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(notificationId,repliedNotification)
        }

    }
}