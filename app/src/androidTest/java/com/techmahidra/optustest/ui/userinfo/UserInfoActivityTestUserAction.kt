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
class UserInfoActivityTestUserAction {

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
    fun userInfoActivityTestUiAction() {
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
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
                    0
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(click())

        try {
            Thread.sleep(7000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val constraintLayout2 = onView(
            allOf(
                withId(R.id.constraintLayUserAlbum),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerViewUserAlbumList),
                        childAtPosition(
                            withId(R.id.constraintLayUserAlbumList),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        constraintLayout2.perform(click())

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
