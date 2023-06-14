package com.rubon.feeder.data.microcontroller.source

data class TimeRequest(
    val hours: Int,
    val minutes: Int,
    val currentHours: Int,
    val currentMinutes: Int,
    val currentSeconds: Int,
    val selectedPortion: Int
)