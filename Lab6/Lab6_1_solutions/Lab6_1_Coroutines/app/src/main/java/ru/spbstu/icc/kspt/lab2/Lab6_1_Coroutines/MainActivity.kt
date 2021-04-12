package ru.spbstu.icc.kspt.lab2.Lab6_1_Coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0

    private val scope = CoroutineScope(Dispatchers.Main)
    private lateinit var job: Job

    private fun updateTimer() = textSecondsElapsed.post {
        textSecondsElapsed.text = getString(R.string.timer_text, secondsElapsed++)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("time", secondsElapsed)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        secondsElapsed = savedInstanceState.getInt("time")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }

    override fun onResume() {
        super.onResume()
        job = scope.launch {
            while (true) {
                delay(1000)
                updateTimer()
            }
        }
    }
}
