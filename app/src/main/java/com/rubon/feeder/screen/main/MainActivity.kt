package com.rubon.feeder.screen.main

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rubon.feeder.R
import com.rubon.feeder.app_level.App
import javax.inject.Inject



class MainActivity :  AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.appComponent.inject(this)


        val bFeed = findViewById<Button>(R.id.b_feed)
        bFeed.setOnClickListener {
            try {
                viewModel.tossFood()
            } catch (e: Exception) {
                e.printStackTrace()
                val errorMessage = "Ошибка при кормлении: ${e.message}"
                showToast(errorMessage)
            }
        }

        val bPour = findViewById<Button>(R.id.b_pour)
        bPour.setOnClickListener {
            try {
                viewModel.pourWater()
            } catch (e: Exception) {
                e.printStackTrace()
                val errorMessage = "Ошибка при наливании воды: ${e.message}"
                showToast(errorMessage)
            }
        }

        val bgraph = findViewById<Button>(R.id.b_graph)
        bgraph.setOnClickListener {
            // Создание намерения (Intent) для перехода на другую активити
            val intent = Intent(this, ScheduleActivity::class.java)
            startActivity(intent) // Запуск другой активити
        }
        // Настройка обработчика нажатия на кнопку
        val highlight = findViewById<Button>(R.id.b_highlight)
        highlight.setOnClickListener {
            // Создание намерения (Intent) для перехода на другую активити
            val intent = Intent(this, HighlightActivity::class.java)
            startActivity(intent) // Запуск другой активити
        }
        }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    }


