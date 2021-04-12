package ru.spbstu.icc.kspt.lab2.Lab6_1_Threads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private val logTag = "MainActivity"

    @Volatile
    var thread: Thread? = null

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
        thread?.interrupt()
        thread = null
        super.onPause()
    }

    override fun onResume() {
        if (thread != null) {
            Log.i(logTag, "Thread is already running")
        } else {
            thread = Thread {
                try {
                        while (true) {
                            Thread.sleep(1000)
                            updateTimer()
                        }
                } catch (e: InterruptedException) {
                    Log.i(logTag, "Exception catched")
                } finally {
                    thread = null
                }
            }.also { it.start() }
        }
        super.onResume()
    }
}
