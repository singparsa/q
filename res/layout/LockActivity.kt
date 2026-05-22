package com.example.focuslock

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class LockActivity : AppCompatActivity() {

    private lateinit var timerText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock)

        timerText = findViewById(R.id.timerText)

        hideSystemUI()

        val minutes = intent.getLongExtra("TIME", 1)
        val totalMillis = minutes * 60 * 1000

        startLockTask()

        object : CountDownTimer(totalMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val min = seconds / 60
                val sec = seconds % 60

                timerText.text = String.format("%02d:%02d", min, sec)
            }

            override fun onFinish() {
                stopLockTask()

                val intent = Intent(this@LockActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)

                finish()
            }

        }.start()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
    }

    override fun onBackPressed() {
        // 뒤로가기 막기
    }
}
