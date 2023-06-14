package com.rubon.feeder.app_level

import android.app.Application
import com.rubon.feeder.app_level.di.AppComponent
import com.rubon.feeder.app_level.di.DaggerAppComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .context(applicationContext)
                .build()
    }

    companion object{
        lateinit var appComponent: AppComponent
    }
}