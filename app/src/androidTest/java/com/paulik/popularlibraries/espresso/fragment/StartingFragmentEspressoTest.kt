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
import com.paulik.popularlibraries.ui.root.StartingFragment
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StartingFragmentEspressoTest {

    private lateinit var activityScenario: ActivityScenario<RootActivity>
    private lateinit var fragment: StartingFragment

    @Before
    fun setup() {
        // создали активити на которой будет раздут фрагмент
        activityScenario = ActivityScenario.launch(RootActivity::class.java)

        // Проверяем, что фрагмент стартовой страницы был отображен на экране
        onView(withId(R.id.fragment_starting)).check(matches(ViewMatchers.isDisplayed()))

        //создали фрагмент (нашли фрагмент)
        activityScenario.onActivity { activity ->
            fragment = StartingFragment()
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
        onView(withId(R.id.counter_mvp_button))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.users_git_hub_button))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.counter_mvp_button))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun counterButton_IsWorking() {
        // Нажимаем на кнопку "счетчика"
        onView(withId(R.id.counter_mvp_button)).perform(click())
        // Проверяем, что фрагмент счетчика был отображен на экране
        onView(withId(R.id.fragment_counter)).check(matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun userButton_IsWorking() {
        // Нажимаем на кнопку "пользователя gitHub"
        onView(withId(R.id.users_git_hub_button)).perform(click())
        // Проверяем, что фрагмент пользователя gitHub был отображен на экране
        onView(withId(R.id.fragment_users_git_hub)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun convertorButton_IsWorking() {
        // Нажимаем на кнопку "конвертора изображения"
        onView(withId(R.id.convertor_image_button)).perform(click())
        // Проверяем, что фрагмент конвертора был отображен на экране
        onView(withId(R.id.fragment_convertor)).check(matches(ViewMatchers.isDisplayed()))
    }

    @After
    fun close() {
        activityScenario.close()
    }
}