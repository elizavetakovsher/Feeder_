package com.rubon.feeder.data.microcontroller.source

import android.content.Context
import retrofit2.Call
import retrofit2.http.*

interface ESPApi {
    @GET("/feed")
    fun getFeed(): Call<Response<Any?>>

    @GET("/pour")
    fun getPour(): Call<Response<Any?>>

    @GET("/reset_schedule")
    fun  reset_schedule(): Call<Response<Any?>>

    @GET("/send-time")
    fun sendTime(
        @Query("currentHours") currentHours: Int,
        @Query("currentMinutes") currentMinutes: Int,
        @Query(" currentSeconds") currentSeconds: Int,
        @Query("hours") hours: Int,
        @Query("minutes") minutes: Int,
        @Query("Portion") selectedPortion: Int
    ): Call<TimeRequest>

    @GET("/send-color")
    fun sendColor(@Query("hue") hue: Float, @Query("saturation") saturation: Float, @Query("value") value: Float): Call<ColorValues>

    @GET("/on-off")
    fun getOnOff( @Query("flag") flag: Boolean): Call<Response<Any?>>

}