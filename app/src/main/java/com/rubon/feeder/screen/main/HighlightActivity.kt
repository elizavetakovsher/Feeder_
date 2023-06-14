package com.rubon.feeder.screen.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.rubon.feeder.R
import com.rubon.feeder.app_level.App
import javax.inject.Inject

class HighlightActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HighlightViewModel


    private lateinit var hueSeekBar: SeekBar
    private lateinit var saturationSeekBar: SeekBar
    private lateinit var valueSeekBar: SeekBar
    private lateinit var colorIndicator: View
    private lateinit var okButton: Button
    private lateinit var toggleButton: ToggleButton
    private var flag: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highlight)
        App.appComponent.inject(this)

        hueSeekBar = findViewById(R.id.hue_seekbar)
        saturationSeekBar = findViewById(R.id.saturation_seekbar)
        valueSeekBar = findViewById(R.id.value_seekbar)
        colorIndicator = findViewById(R.id.color_indicator)
        okButton = findViewById(R.id.ok_button)
        toggleButton = findViewById(R.id.led_toggle_button)


        hueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener)
        saturationSeekBar.setOnSeekBarChangeListener(seekBarChangeListener)
        valueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener)


        okButton.setOnClickListener {
            val hsv = floatArrayOf(
                hueSeekBar.progress.toFloat(),
                saturationSeekBar.progress.toFloat() / 100f,
                valueSeekBar.progress / 100f
            )
            hsv[1] =  hsv[1] * 100
            hsv[2] =  hsv[2] * 100
            val (colorHue, colorSaturation, colorValue) = hsv
            Log.d("HighlightActivity", "colorHue: $colorHue")
            Log.d("HighlightActivity", "colorSaturation: $colorSaturation")
            Log.d("HighlightActivity", "colorValue: $colorValue")
            viewModel.setColor(colorHue, colorSaturation, colorValue)

            val selectedColor = Color.HSVToColor(hsv)
            val colorHex = String.format("#%06X", 0xFFFFFF and selectedColor)

            Toast.makeText(this, "Выбранный цвет: $colorHex", Toast.LENGTH_SHORT).show()
        }


        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            flag = isChecked
            Log.d("HighlightActivity", "isChecked: $isChecked")
            viewModel.getonOff(isChecked)}
    }

    private val seekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            val hsv = FloatArray(3)
            hsv[0] = hueSeekBar.progress.toFloat()
            hsv[1] = saturationSeekBar.progress  / 100f
            hsv[2] = valueSeekBar.progress  / 100f
            val selectedColor = Color.HSVToColor(hsv)
            colorIndicator.setBackgroundColor(selectedColor)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }
}






