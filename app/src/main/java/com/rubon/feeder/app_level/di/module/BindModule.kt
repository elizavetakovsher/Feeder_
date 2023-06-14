package com.rubon.feeder.app_level.di.module

import com.rubon.feeder.data.microcontroller.source.ESPSource
import com.rubon.feeder.data.microcontroller.source.ESPSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface BindModule {
    @Binds
    fun bindESPSource(provider: ESPSourceImpl): ESPSource
}