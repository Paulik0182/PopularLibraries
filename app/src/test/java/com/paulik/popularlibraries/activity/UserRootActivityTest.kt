package com.paulik.popularlibraries.activity

import android.os.Build
import android.widget.FrameLayout
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.ui.users.UserRootActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class UserRootActivityTest {

    private lateinit var scenario: ActivityScenario<UserRootActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(UserRootActivity::class.java)
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
    fun activityFrameLayout_NotNull() {
        /** Проверка на наличее вью*/
        scenario.onActivity {
            val totalCountFrameLayout = it.findViewById<FrameLayout>(R.id.container)
            TestCase.assertNotNull(totalCountFrameLayout)
        }
    }

    @Test
    fun activityCreateIntent_NotNull() {
        val intent = UserRootActivity()
        TestCase.assertNotNull(intent)
    }

    @After
    fun close() {
        scenario.close()
    }
}