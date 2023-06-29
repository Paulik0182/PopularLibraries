package com.paulik.popularlibraries.ui.root

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.paulik.popularlibraries.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FragmentUserGitHubTestRecorder {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(RootActivity::class.java)

    @Test
    fun rootActivityTestRecorder() {
        val materialButton = onView(
            allOf(
                withId(R.id.users_git_hub_button),
                withText("Users GitHub"),
                childAtPosition(
                    childAtPosition(withId(R.id.fragment_starting), 0),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val recyclerView = onView(
            allOf(
                withId(R.id.recycler_view),
                childAtPosition(withId(R.id.content_layout), 0)
            )
        )

        Thread.sleep(3_000) // ожидание в течение 3 секунд
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val titleTextView = onView(
            allOf(
                withId(R.id.title_text_view),
                withText("30daysoflaptops.github.io"),
                withParent(
                    withParent(IsInstanceOf.instanceOf(androidx.cardview.widget.CardView::class.java))
                ),
                isDisplayed()
            )
        )

        Thread.sleep(3_000) // ожидание в течение 3 секунд
        titleTextView.check(matches(withText("30daysoflaptops.github.io")))
        titleTextView.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
