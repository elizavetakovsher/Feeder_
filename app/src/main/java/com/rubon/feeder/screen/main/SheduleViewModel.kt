package com.rubon.feeder.screen.main

import android.content.Context
import com.rubon.feeder.data.microcontroller.MicrocontrollerRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleViewModel @Inject constructor(private val microcontrollerManager: MicrocontrollerRepository) {
    fun setTime(currentHours: Int, currentMinutes: Int, currentSeconds: Int, hours: Int, minutes: Int,  selectedPortion: Int)  {
        GlobalScope.launch {
            microcontrollerManager.setTime( currentHours, currentMinutes, currentSeconds, hours, minutes,  selectedPortion)
        }
    }

    fun reset_schedule(callback: (Boolean) -> Unit) {
        GlobalScope.launch {
            microcontrollerManager.reset_schedule(callback)
        }
    }
}