package com.example.lab5_2

import android.app.ActivityManager
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)

class NavigationTest {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(FirstActivity::class.java)

    @Test
    fun startActivityTest() {
        checkView(Screen.FIRST_ACTIVITY)
        onView(withId(R.id.toSecondButton1)).perform(click())
        checkView(Screen.SECOND_ACTIVITY)
        onView(withId(R.id.toFirstButton2)).perform(click())
        checkView(Screen.FIRST_ACTIVITY)
        onView(withId(R.id.toSecondButton1)).perform(click())
        onView(withId(R.id.toThirdButton2)).perform(click())
        checkView(Screen.THIRD_ACTIVITY)
        onView(withId(R.id.toSecondButton3)).perform(click())
        checkView(Screen.SECOND_ACTIVITY)
        onView(withId(R.id.toThirdButton2)).perform(click())
        onView(withId(R.id.toFirstButton3)).perform(click())
        checkView(Screen.FIRST_ACTIVITY)
        onView(withId(R.id.activity_about_dest)).perform(click())
        checkView(Screen.ACTIVITY_ABOUT)
    }

    @Test
    fun backStackTest() {

        checkView(Screen.FIRST_ACTIVITY)
        onView(withId(R.id.toSecondButton1)).perform(click()) // -> 2
        checkView(Screen.SECOND_ACTIVITY)
        pressBack() // -> 1
        checkView(Screen.FIRST_ACTIVITY)

        onView(withId(R.id.toSecondButton1)).perform(click()) // -> 2
        onView(withId(R.id.toThirdButton2)).perform(click()) // -> 3
        checkView(Screen.THIRD_ACTIVITY)
        onView(withId(R.id.activity_about_dest)).perform(click()) // -> about
        checkView(Screen.ACTIVITY_ABOUT)
        pressBack() // -> 3
        checkView(Screen.THIRD_ACTIVITY)
        pressBack() // -> 2
        checkView(Screen.SECOND_ACTIVITY)
        pressBack() // -> 1
        checkView(Screen.FIRST_ACTIVITY)

        onView(withId(R.id.toSecondButton1)).perform(click()) // -> 2
        onView(withId(R.id.toThirdButton2)).perform(click()) // -> 3
        onView(withId(R.id.toSecondButton3)).perform(click()) // -> 2
        onView(withId(R.id.toThirdButton2)).perform(click()) // -> 3
        pressBack() // -> 2
        pressBack() // -> 1
        checkView(Screen.FIRST_ACTIVITY)

        //Check if the app closes successfully
        pressBackUnconditionally()
        Thread.sleep(1000)
        assertTrue(activityTestRule.scenario.state == Lifecycle.State.DESTROYED)
    }


    private fun checkView(screen: Screen) {
        when (screen) {
            Screen.FIRST_ACTIVITY ->
                onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("Activity 1")))
            Screen.SECOND_ACTIVITY ->
                onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("Activity 2")))
            Screen.THIRD_ACTIVITY ->
                onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("Activity 3")))
            Screen.ACTIVITY_ABOUT ->
                onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("Activity about")))
        }
    }
}
