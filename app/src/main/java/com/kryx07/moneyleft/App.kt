package com.kryx07.moneyleft

import android.app.Application
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var app: App
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        app = this
    }
}