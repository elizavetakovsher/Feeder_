package com.rubon.feeder.data.microcontroller

import android.content.Context
import com.rubon.feeder.data.microcontroller.source.ESPSource
import javax.inject.Inject


class MicrocontrollerRepository @Inject constructor(private val source: ESPSource){
    suspend fun tossFood(){
        source.feed()
    }

    suspend fun pourWater(){
        source.pour()
    }

    suspend fun reset_schedule(callback: (Boolean) -> Unit){
        source.reset_schedule(callback)
    }


    suspend fun getonOff(flag: Boolean){
        source.OnOff(flag)
    }
    suspend fun setTime(
        currentHours: Int,
        currentMinutes: Int,
        currentSeconds: Int,
        hours: Int,
        minutes: Int,
        selectedPortion: Int
    ) {
        source.sendTime(currentHours, currentMinutes, currentSeconds, hours,minutes, selectedPortion)
    }

    suspend fun setColor(hue: Float, saturation: Float, value: Float) {
        source.sendColor(hue, saturation, value)
    }
}