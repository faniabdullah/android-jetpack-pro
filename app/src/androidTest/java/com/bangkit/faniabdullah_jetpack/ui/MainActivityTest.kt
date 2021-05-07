package com.bangkit.faniabdullah_jetpack.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
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
import com.bangkit.faniabdullah_jetpack.data.source.local.room.MovieDao
import com.bangkit.faniabdullah_jetpack.data.source.local.room.MovieDatabase
import com.bangkit.faniabdullah_jetpack.utils.EspressoIdlingResource
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import java.io.IOException


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private lateinit var db: MovieDatabase
    private lateinit var movieDao: MovieDao

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, MovieDatabase::class.java).build()
        movieDao = db.movieDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
        db.close()
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
    fun showDetailMovie() {
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
    fun showDetailTvShows() {
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
    @Throws(Exception::class)
    fun ShowEmptyFavorite() {
        movieDao.deleteAllMovie()
        movieDao.deleteAllTvShows()
        onView(withId(R.id.navigation_favorite)).perform(click())

    }

    @Test
    fun ShowsFavoritesMovieAndTvShows() {
        onView(withId(R.id.navigation_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )
        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.addToFavorite)).perform(click())
        pressBack()

        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )
        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.addToFavorite)).perform(click())
        pressBack()

        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.rv_movie_favorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows_favorite))
            .check(matches(isDisplayed()))
        pressBack()
    }

    @Test
    fun InsertUpdatesFavorites() {

        onView(withId(R.id.navigation_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )
        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.addToFavorite)).perform(click())
        pressBack()

        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )
        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.addToFavorite)).perform(click())
        pressBack()

        onView(withId(R.id.navigation_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )
        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.addToFavorite)).perform(click())
        pressBack()

        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()
                )
            )
        onView(withId(R.id.tv_movie_detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.addToFavorite)).perform(click())
        pressBack()
    }

}