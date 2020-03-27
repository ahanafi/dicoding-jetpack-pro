package com.ahanafi.id.cataloguearchitecturecomp.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.ahanafi.id.cataloguearchitecturecomp.R
import com.ahanafi.id.cataloguearchitecturecomp.utils.EspressoIdlingResource
import com.ahanafi.id.cataloguearchitecturecomp.utils.MovieDummy
import com.ahanafi.id.cataloguearchitecturecomp.utils.TvShowDummy
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val movieDummy = MovieDummy.generateMovies()
    private val tvShowDummy = TvShowDummy.generateTvShow()

    @get:Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieDummy.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(movieDummy[0].title)))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(withText(movieDummy[0].releaseDate)))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(movieDummy[0].overview)))
    }

    @Test
    fun loadTvShows() {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShowDummy.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(tvShowDummy[0].title)))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_release_date)).check(matches(withText(tvShowDummy[0].releaseDate)))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
        //onView(withId(R.id.tv_overview)).check(matches(withText(tvShowDummy[0].overview)))
    }

    @Test
    fun swipPage() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
    }

    @Test
    fun checkTabLayoutDisplayed() {
        onView(withId(R.id.tabs))
            .perform(click())
            .check(matches(isDisplayed()))
    }
}