package com.example.autostartdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val serviceIntent = Intent(it, ForegroundService::class.java)
            it.startForegroundService(serviceIntent)
        }
    }
}
