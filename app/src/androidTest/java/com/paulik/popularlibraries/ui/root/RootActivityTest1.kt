package com.paulik.popularlibraries.ui.root


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.paulik.popularlibraries.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RootActivityTest1 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(RootActivity::class.java)

    @Test
    fun rootActivityTest1() {
        val button = onView(
            allOf(
                withId(R.id.counter_mvp_button), withText("C������ �� MVP"),
                withParent(withParent(withId(R.id.fragment_starting))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val button2 = onView(
            allOf(
                withId(R.id.counter_mvp_button), withText("C������ �� MVP"),
                withParent(withParent(withId(R.id.fragment_starting))),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))

        val materialButton = onView(
            allOf(
                withId(R.id.counter_mvp_button), withText("C������ �� MVP"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment_starting),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.one_counter_text_view), withText("������� 1"),
                withParent(
                    allOf(
                        withId(R.id.fragment_counter),
                        withParent(withId(R.id.fragment_container_frame_layout))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("������� 1")))

        val button3 = onView(
            allOf(
                withId(R.id.one_counter_button), withText("0"),
                withParent(
                    allOf(
                        withId(R.id.fragment_counter),
                        withParent(withId(R.id.fragment_container_frame_layout))
                    )
                ),
                isDisplayed()
            )
        )
        button3.check(matches(isDisplayed()))
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
