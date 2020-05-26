package com.techmahidra.optustest.ui.userinfo


import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.techmahidra.optustest.R
import com.techmahidra.optustest.ui.userinfo.CustomAssertions.Companion.hasItemCount
import com.techmahidra.optustest.ui.userinfo.CustomMatchers.Companion.withItemCount
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UserInfoActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(UserInfoActivity::class.java)
    @Before
    fun setUp() {
        val intent = Intent()
        mActivityTestRule.launchActivity(intent)
    }

    @Test
    fun onLaunchActionBarTitleIsDisplayed() {
        val actionBar: ActionBar? = mActivityTestRule.activity.supportActionBar
        Assert.assertNotNull(actionBar?.title)
    }


    @Test
    fun appLaunchSuccessfully() {
        ActivityScenario.launch(UserInfoActivity::class.java)
    }

    @Test
    fun recyclerViewTestScrolling() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewUserInfoList))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun recyclerViewTestScrollingToPositionEndIndex() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewUserInfoList)).perform(ViewActions.swipeUp())
    }

    @Test
    fun recyclerViewTestScrollingToPositionTop() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewUserInfoList)).perform(ViewActions.swipeDown())
    }

    @Test
    fun recyclerViewClick(){
        onView(withId(R.id.recyclerViewUserInfoList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click()))
    }

    @Test
    fun countPrograms() {
        onView(withId(R.id.recyclerViewUserInfoList))
            .check(matches(withItemCount(10)))
    }

    @Test
    fun countProgramsWithViewAssertion() {
        onView(withId(R.id.recyclerViewUserInfoList))
            .check(hasItemCount(10))
    }



    @Test
    fun userInfoActivityTest() {
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

        val textView2 = onView(
            allOf(
                withId(R.id.textViewUserId), withText("Id:  1"),
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
        textView2.check(matches(withText("Id:  1")))

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

        val viewGroup = onView(
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
        viewGroup.check(matches(isDisplayed()))

        val constraintLayout = onView(
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
                    4
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(click())
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
