package com.sample.nudgetesting

import android.app.Application
import java.lang.ref.WeakReference
import com.netcore.android.Smartech

class MyApplicationClass :Application() {
    override fun onCreate() {
        super.onCreate()
        Smartech.getInstance(WeakReference(applicationContext)).initializeSdk(this)
    }
}