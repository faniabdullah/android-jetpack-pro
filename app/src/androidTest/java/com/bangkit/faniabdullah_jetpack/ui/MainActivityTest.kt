package com.bangkit.faniabdullah_jetpack.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.utils.DataDummy
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val dummyMovieNowPlaying = DataDummy.generateDummyDataMovieNowPlaying()
    private val dummyTvShowPopular = DataDummy.generateDummyDataTvShowsPopular()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun showMovie() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    dummyMovieNowPlaying.size
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
                    dummyTvShowPopular.size
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
                    3,
                    click()
                )
            )

        onView(withId(R.id.poster_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_detail))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(withText(dummyMovieNowPlaying[3].original_title)))
        onView(withId(R.id.tv_overview_detail))
            .check(matches(withText(dummyMovieNowPlaying[3].overview)))
        onView(withId(R.id.tv_info_movie))
            .check(matches(withText(dummyMovieNowPlaying[3].vote_average.toString())))

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
                    3,
                    click()
                )
            )

        onView(withId(R.id.poster_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_detail))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(withText(dummyTvShowPopular[3].original_title)))
        onView(withId(R.id.tv_overview_detail))
            .check(matches(withText(dummyTvShowPopular[3].overview)))
        onView(withId(R.id.tv_info_movie))
            .check(matches(withText(dummyTvShowPopular[3].vote_average.toString())))

        pressBack()

    }
}