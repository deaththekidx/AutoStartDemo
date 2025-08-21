package com.example.autostartdemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder

class ForegroundService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()

        val notification: Notification = Notification.Builder(this, "AutoStartChannel")
            .setContentTitle("定时启动器")
            .setContentText("正在启动企业微信…")
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
            .build()

        startForeground(1, notification)

        // 启动企业微信
        val launchIntent = packageManager.getLaunchIntentForPackage("com.tencent.wework")
        launchIntent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(launchIntent)

        stopSelf()
        return START_NOT_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "AutoStartChannel",
                "定时启动器通知",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
