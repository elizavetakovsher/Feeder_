package com.rubon.feeder.screen.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rubon.feeder.R
import com.rubon.feeder.app_level.App
import dagger.Module
import java.util.Calendar
import javax.inject.Inject


class ScheduleActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModel: ScheduleViewModel

    private lateinit var selectedTimeTextView: TextView
    private lateinit var hourPicker: NumberPicker
    private lateinit var minutePicker: NumberPicker
    private lateinit var portionPicker: NumberPicker
    private lateinit var setTimeButton: Button
    private lateinit var resetScheduleButton: Button
    private lateinit var selectedPortionTextView: TextView

    private var selectedHour: Int = 0
    private var selectedMinute: Int = 0
    private var selectedPortion: Int = 0

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        App.appComponent.inject(this)

        selectedTimeTextView = findViewById(R.id.selected_time_textview)
        resetScheduleButton = findViewById(R.id.reset_schedule_button)
        hourPicker = findViewById(R.id.hour_picker)
        minutePicker = findViewById(R.id.minute_picker)
        portionPicker = findViewById(R.id.portion_picker)
        setTimeButton = findViewById(R.id.set_time_button)
        selectedPortionTextView = findViewById(R.id.selected_portion_textview)

        // Установка максимального и минимального значения для часов
        hourPicker.minValue = 0
        hourPicker.maxValue = 23

        // Установка максимального и минимального значения для минут
        minutePicker.minValue = 0
        minutePicker.maxValue = 59

        // Установка максимального и минимального значения для порций
        portionPicker.minValue = 1
        portionPicker.maxValue = 6

        // Инициализация SharedPreferences
        sharedPreferences = getSharedPreferences("SchedulePrefs", Context.MODE_PRIVATE)

        // Восстановление сохраненных значений
        selectedHour = sharedPreferences.getInt("selectedHour", 0)
        selectedMinute = sharedPreferences.getInt("selectedMinute", 0)
        selectedPortion = sharedPreferences.getInt("selectedPortion", 0)

        val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
        selectedTimeTextView.text = selectedTime

        val selectedPortionText = selectedPortion.toString()
        selectedPortionTextView.text = selectedPortionText

        hourPicker.value = selectedHour
        minutePicker.value = selectedMinute
        portionPicker.value = selectedPortion

        setTimeButton.setOnClickListener {
            selectedHour = hourPicker.value
            selectedMinute = minutePicker.value
            selectedPortion = portionPicker.value

            val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            selectedTimeTextView.text = selectedTime

            val selectedPortionText = selectedPortion.toString()
            selectedPortionTextView.text = selectedPortionText

            val currentTime = Calendar.getInstance()
            val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val currentMinute = currentTime.get(Calendar.MINUTE)
            val currentSecond = currentTime.get(Calendar.SECOND)

            // Сохранение значений в SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putInt("selectedHour", selectedHour)
            editor.putInt("selectedMinute", selectedMinute)
            editor.putInt("selectedPortion", selectedPortion)
            editor.apply()

            Log.d("ScheduleActivity", "currentHour: $currentHour")
            Log.d("ScheduleActivity", "currentMinute: $currentMinute")
            Log.d("ScheduleActivity", "currentSecond: $currentSecond")
            Log.d("ScheduleActivity", "selectedPortion: $selectedPortion")
            // Передача значений в ViewModel
            viewModel.setTime(
                currentHour,
                currentMinute,
                currentSecond,
                selectedHour,
                selectedMinute,
                selectedPortion
            )

        }

        resetScheduleButton.setOnClickListener {
            viewModel.reset_schedule() { isSuccess ->
                if (isSuccess) {
                    selectedHour = 0
                    selectedMinute = 0
                    selectedPortion = 0

                    selectedTimeTextView.text = "" // Очищаем текстовое поле
                    selectedPortionTextView.text = "" // Очищаем текстовое поле

                    hourPicker.value = selectedHour
                    minutePicker.value = selectedMinute
                    portionPicker.value = selectedPortion

                    // Очистка сохраненных значений в SharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.remove("selectedHour")
                    editor.remove("selectedMinute")
                    editor.remove("selectedPortion")
                    editor.apply()
                }
            }
        }
    }
}