package ru.spbstu.icc.kspt.lab2.Lab6_1_AsyncTask

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var secondsElapsed: Int = 0
    val logTag = "MainActivity"
    private lateinit var task: MyTask

    class MyTask(private val parent: MainActivity): AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg params: Unit?) {
            try {
                while (!isCancelled) {
                    Thread.sleep(1000)
                    parent.updateTimer()
                    Log.i("MainActivity", "1 second passed")
                }
            } catch (e: InterruptedException) {
                Log.i(MainActivity().logTag, "Thread interrupt exception catched")
            }
        }
    }

    fun updateTimer() = textSecondsElapsed.post {
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
        task.cancel(false)
    }

    override fun onResume() {
        super.onResume()
        task = MyTask(this)
        task.execute()
    }
}
