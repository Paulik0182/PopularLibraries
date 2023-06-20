package com.paulik.popularlibraries.espresso.fragment

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.ui.root.RootActivity
import com.paulik.popularlibraries.ui.settings.SettingsFragment
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsFragmentEspressoTest {

    private lateinit var activityScenario: ActivityScenario<RootActivity>
    private lateinit var fragment: SettingsFragment

    @Before
    fun setup() {
        // создали активити на которой будет раздут фрагмент
        activityScenario = ActivityScenario.launch(RootActivity::class.java)

        // Нажимаем на кнопку "Настройки" в нижней панели приложения
        onView(withId(R.id.settings_item)).perform(click())
        // Проверяем, что фрагмент настройки был отображен на экране
        onView(withId(R.id.fragment_settings)).check(matches(ViewMatchers.isDisplayed()))

        //создали фрагмент (нашли фрагмент)
        activityScenario.onActivity { activity ->
            fragment = SettingsFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_frame_layout, fragment, null)
                .commitNow()
        }
    }

    @Test
    fun testFragmentNotNull() {
        assertNotNull(fragment)
    }

    @Test
    fun fragmentButtons_AreEffectiveVisible() {

        onView(withId(R.id.about_app_button))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun fragmentButton_IsWorking() {

        // Нажимаем на кнопку "О приложении"
        onView(withId(R.id.about_app_button)).perform(click())
        // Проверяем, что фрагмент О приложении был отображен на экране
        onView(withId(R.id.fragment_about_app)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun fragmentSettingsButton_AreEffectiveVisible() {
        // Нажимаем на кнопку "О приложении"
        onView(withId(R.id.about_app_button)).perform(click())

        onView(withId(R.id.about_app_text_view))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.cod_version_text_view))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.version_text_view))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun fragmentSettings_Text() {
        // Нажимаем на кнопку "О приложении"
        onView(withId(R.id.about_app_button)).perform(click())

        onView(withId(R.id.about_app_text_view))
            .check(matches(ViewMatchers.withText(R.string.about_app_text)))
    }

    @After
    fun close() {
        activityScenario.close()
    }
}