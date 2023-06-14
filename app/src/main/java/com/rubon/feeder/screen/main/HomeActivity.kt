package com.rubon.feeder.screen.main

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rubon.feeder.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        // Настройка обработчика нажатия на кнопку b_home
        val b_home = findViewById<Button>(R.id.b_home)
        b_home.setOnClickListener {
            // Создание намерения (Intent) для перехода на другую активити
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) // Запуск другой активити
        }

        // Настройка обработчика нажатия на кнопку b_instruction
        val b_instruction = findViewById<Button>(R.id.b_instruction)
        b_instruction.setOnClickListener {
            showInstructionPopup() // Вызов метода для отображения всплывающего окна с инструкцией
        }
    }

    private fun showInstructionPopup() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Инструкция")
        builder.setMessage("ПОДКЛЮЧЕНИЕ К ТОЧКЕ ДОСТУПА УСТРОЙСТВА\n\n" +
                "1. Откройте настройки сети Wi-Fi в вашем смартфоне.\n\n" +
                "2. Нажмите \"Создать новую сеть Wi-Fi\".\n\n" +
                "3. Введите название сети - Feeder.\n\n" +
                "4. Выберите тип защиты новой сети - WPA/WPA2 PSK.\n\n" +
                "5. Введите пароль к сети - 12345678.\n\n" +
                "6. Подтвердите создание новой сети.\n\n" +
                "7. После завершения создания новой сети, подключитесь к ней.")

        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}