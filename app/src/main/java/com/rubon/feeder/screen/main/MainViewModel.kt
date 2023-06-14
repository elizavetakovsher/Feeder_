package com.rubon.feeder.screen.main

import android.content.Context
import com.rubon.feeder.data.microcontroller.MicrocontrollerRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val microcontrollerManager: MicrocontrollerRepository){
    fun tossFood() {
        GlobalScope.launch {
            microcontrollerManager.tossFood()
        }
    }

    fun pourWater() {
        GlobalScope.launch {
            microcontrollerManager.pourWater()
        }
    }
}