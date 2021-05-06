package com.bangkit.faniabdullah_jetpack.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun showMovie() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    10
                )
            )
    }

    @Test
    fun showTvShowsPopular() {
        onView(withId(R.id.navigation_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    10
                )
            )
    }

    @Test
    fun showsDetailMovie() {
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )

        onView(withId(R.id.poster_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_detail))
            .check(matches(isDisplayed()))

        pressBack()

    }

    @Test
    fun showsDetailTvShows() {
        onView(withId(R.id.navigation_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )

        onView(withId(R.id.poster_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_detail))
            .check(matches(isDisplayed()))

        pressBack()
    }

    @Test
    fun showsFavorites() {
        onView(withId(R.id.navigation_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )

        onView(withId(R.id.poster_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_detail))
            .check(matches(isDisplayed()))

        pressBack()
    }
}