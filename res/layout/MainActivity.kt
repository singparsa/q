package com.example.focuslock

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeInput = findViewById<EditText>(R.id.timeInput)
        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            val minutes = timeInput.text.toString().toLongOrNull() ?: 0

            if (minutes > 0) {
                val intent = Intent(this, LockActivity::class.java)
                intent.putExtra("TIME", minutes)
                startActivity(intent)
            }
        }
    }
}
