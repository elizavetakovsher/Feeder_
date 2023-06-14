package com.rubon.feeder.data.microcontroller.source

import android.content.Context
import android.os.Looper
import android.widget.Toast
import com.rubon.feeder.screen.main.HighlightActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import retrofit2.awaitResponse
import java.util.logging.Handler
import javax.inject.Inject



interface ESPSource {
    suspend fun feed()
    suspend fun pour()
    suspend fun reset_schedule(callback: (Boolean) -> Unit)
    suspend fun OnOff(flag: Boolean)
    suspend fun sendTime(currentHours: Int, currentMinutes: Int, currentSeconds: Int, hours: Int, minutes: Int, selectedPortion: Int)
    suspend fun sendColor( hue: Float, saturation: Float, value: Float)
}

class ESPSourceImpl @Inject constructor(private val api: ESPApi, private val context: Context): ESPSource {
    override suspend fun feed() {
        try {
            val result = api.getFeed().awaitResponse()
            if (result.isSuccessful) {
                // Обработка успешного ответа от ESP
                val Message = "Успешно!"
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, Message, Toast.LENGTH_SHORT).show()
                }
            } else {
                throw Exception("Wrong response from ESP")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            val errorMessage = "Ошибка подключения к устройству: ${e.message}"
            // Вывод сообщения об ошибке пользователю, например, с использованием Toast или Snackbar
            // Или обновление UI для отображения ошибки
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override suspend fun pour() {
        try {
            val result = api.getPour().awaitResponse()
            if (result.isSuccessful) {
                // Обработка успешного ответа от ESP
                val Message = "Успешно!"
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, Message, Toast.LENGTH_SHORT).show()
                }
            } else {
                throw Exception("Wrong response from ESP")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            val errorMessage = "Ошибка подключения к устройству: ${e.message}"
            // Вывод сообщения об ошибке пользователю, например, с использованием Toast или Snackbar
            // Или обновление UI для отображения ошибки
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

        override suspend fun reset_schedule(callback: (Boolean) -> Unit) {
            try {
                val result = api.reset_schedule().awaitResponse()
                if (result.isSuccessful) {
                    // Обработка успешного ответа от ESP
                    val Message = "Успешно!"
                    GlobalScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, Message, Toast.LENGTH_SHORT).show()
                        callback(true)
                    }
                } else {
                    throw Exception("Wrong response from ESP")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                val errorMessage = "Ошибка подключения к устройству: ${e.message}"
                // Вывод сообщения об ошибке пользователю, например, с использованием Toast или Snackbar
                // Или обновление UI для отображения ошибки
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    callback(false)
                }
            }
        }

    override suspend fun OnOff(flag: Boolean) {
        try {
            val result = api.getOnOff(flag).awaitResponse()
            if (result.isSuccessful) {
                // Обработка успешного ответа от ESP
                val Message = "Успешно!"
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, Message, Toast.LENGTH_SHORT).show()
                }
            } else {
                throw Exception("Wrong response from ESP")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            val errorMessage = "Ошибка подключения к устройству: ${e.message}"
            // Вывод сообщения об ошибке пользователю, например, с использованием Toast или Snackbar
            // Или обновление UI для отображения ошибки
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override suspend fun sendTime(currentHours: Int, currentMinutes: Int, currentSeconds: Int, hours: Int, minutes: Int, selectedPortion: Int)  {
        try {
            val response = api.sendTime(currentHours, currentMinutes, currentSeconds,hours, minutes, selectedPortion).awaitResponse()
            if (response.isSuccessful) {
                // Обработка успешного ответа от ESP
                val Message = "Успешно!"
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, Message, Toast.LENGTH_SHORT).show()
                }
            } else {
                throw Exception("Неверный ответ от ESP")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            val errorMessage = "Ошибка подключения к устройству: ${e.message}"
            // Вывод сообщения об ошибке пользователю, например, с использованием Toast или Snackbar
            // Или обновление UI для отображения ошибки
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

        }
    }

    override suspend fun sendColor(hue: Float, saturation: Float, value: Float) {
        try {
            val response = api.sendColor(hue, saturation, value).awaitResponse()
            if (response.isSuccessful) {
                // Обработка успешного ответа от ESP
                val Message = "Успешно!"
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, Message, Toast.LENGTH_SHORT).show()
                }
            } else {
                throw Exception("Неверный ответ от ESP")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            val errorMessage = "Ошибка подключения к устройству: ${e.message}"
            // Вывод сообщения об ошибке пользователю, например, с использованием Toast или Snackbar
            // Или обновление UI для отображения ошибки
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }


    }

}