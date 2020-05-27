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
class UserInfoActivityTestScreen1UiDisplay {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(UserInfoActivity::class.java)

    @Test
    fun userInfoActivityTestScreen1() {

        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val textView = onView(
            allOf(
                withText("User Info"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("User Info")))
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val viewGroup2 = onView(
            allOf(
                withId(R.id.constraintLayUserInfo),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerViewUserInfoList),
                        childAtPosition(
                            withId(R.id.constraintLayUserInfoList),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        viewGroup2.check(matches(isDisplayed()))
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val textView2 = onView(
            allOf(
                withId(R.id.textViewUserId), withText("ID: 1"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayUserInfo),
                        childAtPosition(
                            withId(R.id.recyclerViewUserInfoList),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("ID: 1")))
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val textView3 = onView(
            allOf(
                withId(R.id.textViewUserName), withText("Name: Leanne Graham"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayUserInfo),
                        childAtPosition(
                            withId(R.id.recyclerViewUserInfoList),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Name: Leanne Graham")))
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val textView4 = onView(
            allOf(
                withId(R.id.textViewUserEmail), withText("Email: Sincere@april.biz"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayUserInfo),
                        childAtPosition(
                            withId(R.id.recyclerViewUserInfoList),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Email: Sincere@april.biz")))
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val textView5 = onView(
            allOf(
                withId(R.id.textViewUserPhone), withText("Phone: 1-770-736-8031 x56442"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayUserInfo),
                        childAtPosition(
                            withId(R.id.recyclerViewUserInfoList),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Phone: 1-770-736-8031 x56442")))
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val textView6 = onView(
            allOf(
                withId(R.id.textViewUserPhone), withText("Phone: 1-770-736-8031 x56442"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayUserInfo),
                        childAtPosition(
                            withId(R.id.recyclerViewUserInfoList),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("Phone: 1-770-736-8031 x56442")))
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
