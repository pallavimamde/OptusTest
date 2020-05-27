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
class UserInfoActivityTestScreen2UiDisplay {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(UserInfoActivity::class.java)

    @Test
    fun userInfoActivityTestScreen2() {
        try {
            Thread.sleep(8000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val textView7 = onView(
            allOf(
                withId(R.id.textViewAlbumIdTitle), withText("Album ID: 1"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayUserAlbumList),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView7.check(matches(withText("Album ID: 1")))

        val viewGroup3 = onView(
            allOf(
                withId(R.id.constraintLayUserAlbum),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerViewUserAlbumList),
                        childAtPosition(
                            withId(R.id.constraintLayUserAlbumList),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        viewGroup3.check(matches(isDisplayed()))

        val textView8 = onView(
            allOf(
                withId(R.id.textViewThumbnailInfo),
                withText("accusamus beatae ad facilis cum similique qui sunt"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayUserAlbum),
                        childAtPosition(
                            withId(R.id.recyclerViewUserAlbumList),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView8.check(matches(withText("accusamus beatae ad facilis cum similique qui sunt")))

        val imageView = onView(
            allOf(
                withId(R.id.imageViewThumbnail),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayUserAlbum),
                        childAtPosition(
                            withId(R.id.recyclerViewUserAlbumList),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val imageView2 = onView(
            allOf(
                withId(R.id.imageViewThumbnail),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayUserAlbum),
                        childAtPosition(
                            withId(R.id.recyclerViewUserAlbumList),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))
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
