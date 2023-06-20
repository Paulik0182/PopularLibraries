package com.paulik.popularlibraries.espresso.activity

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.ui.root.RootActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RootActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<RootActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(RootActivity::class.java)
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun testBottomNavigationBar() {

        // Нажимаем на кнопку "Настройки" в нижней панели приложения
        onView(withId(R.id.settings_item)).perform(click())

        // Проверяем, что фрагмент настроек был отображен на экране
        onView(withId(R.id.fragment_settings)).check(matches(isDisplayed()))

        // Нажимаем на кнопку "стартовой страницы" в нижней панели приложения
        onView(withId(R.id.starting_item)).perform(click())

        // Проверяем, что фрагмент стартовой страницы был отображен на экране
        onView(withId(R.id.fragment_starting)).check(matches(isDisplayed()))
    }

    @After
    fun close() {
        scenario.close()
    }
}