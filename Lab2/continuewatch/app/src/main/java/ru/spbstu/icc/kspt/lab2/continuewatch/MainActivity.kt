package ru.spbstu.icc.kspt.lab2.continuewatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var countEnable = true
    var created = false
    var secondsElapsed: Int = 0

    var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            textSecondsElapsed.post {
                if (countEnable) textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("MainActivity", "state onSaveInstanceState")
        outState?.putInt("time", secondsElapsed)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        secondsElapsed = savedInstanceState!!.getInt("time")
        Log.i("MainActivity", "state onRestoreInstanceState")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!created) {
            created = true
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            backgroundThread.start()
        }
    }

    override fun onStart() {
        super.onStart()
        countEnable = true;
    }

    override fun onStop() {
        super.onStop()
        countEnable = false;
    }
}
