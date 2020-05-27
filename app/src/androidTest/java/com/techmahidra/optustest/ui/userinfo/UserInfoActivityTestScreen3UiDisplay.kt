package com.techmahidra.optustest.ui.userinfo


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.techmahidra.optustest.R
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
class UserInfoActivityTestScreen3UiDisplay {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(UserInfoActivity::class.java)

    @Test
    fun userInfoActivityTestScreen3() {
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val textView9 = onView(
            allOf(
                withId(R.id.textViewAlbumId), withText("Album ID: 1"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.swipeRefreshLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView9.check(matches(withText("Album ID: 1")))

        val textView10 = onView(
            allOf(
                withId(R.id.textViewPhotoId), withText("Photo ID: 1"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.swipeRefreshLayout),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView10.check(matches(withText("Photo ID: 1")))

        val imageView3 = onView(
            allOf(
                withId(R.id.imageViewUserImage),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.swipeRefreshLayout),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        imageView3.check(matches(isDisplayed()))

        val textView11 = onView(
            allOf(
                withId(R.id.textViewImageInfo),
                withText("accusamus beatae ad facilis cum similique qui sunt"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.swipeRefreshLayout),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView11.check(matches(withText("accusamus beatae ad facilis cum similique qui sunt")))
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
