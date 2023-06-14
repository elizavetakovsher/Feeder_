package com.rubon.feeder.app_level.di

import android.content.Context
import com.rubon.feeder.app_level.di.module.BindModule
import com.rubon.feeder.app_level.di.module.APIModule
import com.rubon.feeder.screen.main.HighlightActivity
import com.rubon.feeder.screen.main.HomeActivity
import com.rubon.feeder.screen.main.MainActivity
import com.rubon.feeder.screen.main.ScheduleActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BindModule::class, APIModule::class])
interface AppComponent {
    fun inject(activity: ScheduleActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: HighlightActivity)
    fun inject(activity: HomeActivity)

    // Метод для передачи контекста
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}