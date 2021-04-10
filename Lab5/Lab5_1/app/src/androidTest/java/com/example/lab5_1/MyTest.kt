package com.example.lab5_1

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.TypeTextAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test

@LargeTest
@RunWith(AndroidJUnit4::class)
class MyTest {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun onStateChangeTest() {
        val button = onView(withId(R.id.button))
        val text = onView(withId(R.id.editText))

        text.check(ViewAssertions.matches(withText("Enter your message here")))
        button.check(ViewAssertions.matches(withText("Taps: 0")))
        button.perform(click())
        button.check(ViewAssertions.matches(withText("Taps: 1")))
        text.perform(replaceText("test text"))

        rotateDeviceLeft()
        Thread.sleep(1000)

        text.check(ViewAssertions.matches(withText("test text")))
        button.check(ViewAssertions.matches(withText("Taps: 0")))
    }
}

private fun rotateDeviceLeft() {
    val device = UiDevice.getInstance(
        InstrumentationRegistry.getInstrumentation())
    device.setOrientationLeft()
}

private fun restoreRotationDevice() {
    val device = UiDevice.getInstance(
        InstrumentationRegistry.getInstrumentation())
    device.setOrientationNatural()
}
