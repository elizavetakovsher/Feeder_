package com.rubon.feeder.screen.main

import android.content.Context
import com.rubon.feeder.data.microcontroller.MicrocontrollerRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HighlightViewModel  @Inject constructor(private val microcontrollerManager: MicrocontrollerRepository) {
    fun setColor(hue: Float, saturation: Float, value: Float) {
        GlobalScope.launch {
            microcontrollerManager.setColor(hue, saturation, value)
        }
    }

    fun getonOff(flag: Boolean) {
        GlobalScope.launch {
            microcontrollerManager.getonOff(flag)
        }
    }
}