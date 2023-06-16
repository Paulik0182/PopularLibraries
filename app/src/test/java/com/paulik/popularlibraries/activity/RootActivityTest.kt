package com.paulik.popularlibraries.activity

import android.os.Build
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.ui.root.RootActivity
import junit.framework.TestCase
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class RootActivityTest {

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
        /** state - состояние активити*/
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityLinearLayout_NotNull() {
        /** Проверка на наличее вью*/
        scenario.onActivity {
            val totalCountLinearLayout = it.findViewById<LinearLayout>(
                R.id.linear_layout_container
            )
            TestCase.assertNotNull(totalCountLinearLayout)
        }
    }

    @Test
    fun activityFrameLayout_NotNull() {
        scenario.onActivity {
            val totalCountFrameLayout = it.findViewById<FrameLayout>(
                R.id.fragment_container_frame_layout
            )
            TestCase.assertNotNull(totalCountFrameLayout)
        }
    }

    @Test
    fun activityBottomNavBar_NotNull() {
        scenario.onActivity {
            val totalCountNav = it.findViewById<BottomNavigationView>(
                R.id.bottom_nav_bar
            )
            TestCase.assertNotNull(totalCountNav)
        }
    }

    // тест, который проверяет, что bottom navigation bar находится на экране
    @Test
    fun bottomNavBar_AssertDisplayed() {
        scenario.onActivity {
            Espresso.onView(ViewMatchers.withId(R.id.bottom_nav_bar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    @Test
    fun activityCreateIntent_NotNull() {
        val intent = RootActivity()
        TestCase.assertNotNull(intent)
    }

    // тест, который проверяет, что при переходе на экран настроек открывается правильный фрагмент
    @Test
    fun openSettingsFragment() {
        scenario.onActivity {
            it.runOnUiThread {
                it.onBottomNaviBar()
                it.binding.bottomNavBar.selectedItemId = R.id.settings_item
            }
            onView(
                allOf(
                    withText(R.string.settings),
                    withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
                )
            )
                .check(matches(isDisplayed()))
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}