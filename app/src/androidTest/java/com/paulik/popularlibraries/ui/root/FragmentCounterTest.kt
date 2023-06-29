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
class FragmentCounterTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(RootActivity::class.java)

    @Test
    fun oneCounterTest() {

        val materialButton = onView(
            allOf(
                withId(R.id.counter_mvp_button),
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
                withId(R.id.one_counter_text_view),
                withParent(
                    allOf(
                        withId(R.id.fragment_counter),
                        withParent(withId(R.id.fragment_container_frame_layout))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Счетчик 1")))

        val oneCounterButton = onView(
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
        oneCounterButton.check(matches(isDisplayed()))
    }

    @Test
    fun twoCounterTest() {

        val materialButton = onView(
            allOf(
                withId(R.id.counter_mvp_button),
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
                withId(R.id.two_counter_text_view),
                withParent(
                    allOf(
                        withId(R.id.fragment_counter),
                        withParent(withId(R.id.fragment_container_frame_layout))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Счетчик 2")))

        val twoCounterButton = onView(
            allOf(
                withId(R.id.two_counter_button), withText("0"),
                withParent(
                    allOf(
                        withId(R.id.fragment_counter),
                        withParent(withId(R.id.fragment_container_frame_layout))
                    )
                ),
                isDisplayed()
            )
        )
        twoCounterButton.check(matches(isDisplayed()))
    }

    @Test
    fun threeCounterTest() {

        val materialButton = onView(
            allOf(
                withId(R.id.counter_mvp_button),
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
                withId(R.id.three_counter_text_view),
                withParent(
                    allOf(
                        withId(R.id.fragment_counter),
                        withParent(withId(R.id.fragment_container_frame_layout))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Счетчик 3")))

        val threeCounterButton = onView(
            allOf(
                withId(R.id.three_counter_button), withText("0"),
                withParent(
                    allOf(
                        withId(R.id.fragment_counter),
                        withParent(withId(R.id.fragment_container_frame_layout))
                    )
                ),
                isDisplayed()
            )
        )
        threeCounterButton.check(matches(isDisplayed()))
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
