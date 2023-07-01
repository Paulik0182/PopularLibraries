package com.paulik.popularlibraries.espresso.fragment

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.ui.root.RootActivity
import com.paulik.popularlibraries.ui.root.StartingFragment
import com.paulik.popularlibraries.ui.users.adapter.UsersAdapter
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserFragmentRecyclerViewTest {

    private lateinit var activityScenario: ActivityScenario<RootActivity>
    private lateinit var fragment: StartingFragment

    @Before
    fun setup() {
        activityScenario = ActivityScenario.launch(RootActivity::class.java)

        // Проверяем, что фрагмент стартовой страницы был отображен на экране
        onView(withId(R.id.fragment_starting)).check(ViewAssertions.matches(isDisplayed()))

        //создали фрагмент (нашли фрагмент)
        activityScenario.onActivity { activity ->
            fragment = StartingFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_frame_layout, fragment, null)
                .commitNow()
        }

        // Нажимаем на кнопку "пользователя gitHub"
        onView(withId(R.id.users_git_hub_button)).perform(click())
        // Проверяем, что фрагмент пользователя gitHub был отображен на экране
        onView(withId(R.id.fragment_users_git_hub)).check(ViewAssertions.matches(isDisplayed()))

        Thread.sleep(3_000) // ожидание в течение 3 секунд
    }

    @Test
    fun testFragmentNotNull() {
        TestCase.assertNotNull(fragment)
    }

    @Test
    fun searchValues_ScrollTo() {

        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions.scrollTo<UsersAdapter.UserViewHolder>(
                    hasDescendant(withText("atmos"))
                )
            )
    }

    @Test
    fun searchItem_PerformClickAtPosition() {

        /**клик на элемент RecyclerView на заданную позицию*/
        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UsersAdapter.UserViewHolder>(
                    0,
                    click()
                )
            )
    }

    @Test
    fun SearchItem_PerformClickOnItem() {

        /** выполняет прокрутку элементов RecyclerView до видимости элемента, который имеет
         * внутри себя дочерний элемент (hasDescendant) с текстом "errfree"*/
        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions.scrollTo<UsersAdapter.UserViewHolder>(
                    hasDescendant(withText("caged"))
                )
            )
        /**кликает на элемент в RecyclerView, который имеет внутри себя дочерний элемент
         * (hasDescendant) с текстом "atmos".*/
        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<UsersAdapter.UserViewHolder>(
                    hasDescendant(withText("macournoyer")),
                    click()
                )
            )
    }

    @After
    fun close() {
        activityScenario.close()
    }
}
