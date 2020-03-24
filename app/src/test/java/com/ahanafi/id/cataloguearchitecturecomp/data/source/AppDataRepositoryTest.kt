package com.ahanafi.id.cataloguearchitecturecomp.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.RemoteDataSource
import com.ahanafi.id.cataloguearchitecturecomp.utils.LiveDataTestUtil
import com.ahanafi.id.cataloguearchitecturecomp.utils.MovieDummy
import com.ahanafi.id.cataloguearchitecturecomp.utils.TvShowDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

class AppDataRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val appDataRepository = FakeAppDataRepository(remote)

    private val movieResponses = MovieDummy.generateDataMovieResponses()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = TvShowDummy.generateRemoteDataTvShow()
    private val tvShowId = tvShowResponses[0].id

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val movie = LiveDataTestUtil.getValue(appDataRepository.getAllMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(movie)
        assertEquals(movieResponses.size.toLong(), movie.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())

        val movieData = LiveDataTestUtil.getValue(appDataRepository.getDetailMovie(movieId!!))
        verify(remote).getAllMovies((any()))
        assertNotNull(movieData)
        assertEquals(movieResponses[0].id, movieData.id)
    }

    @Test
    fun getAllTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getAllTvShows(any())

        val tvShowData = LiveDataTestUtil.getValue(appDataRepository.getAllTvShows())

        verify(remote).getAllTvShows(any())
        assertNotNull(tvShowData)
        assertEquals(tvShowResponses.size.toLong(), tvShowData.size.toLong())
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getAllTvShows(any())

        val tvShowData = LiveDataTestUtil.getValue(appDataRepository.getDetailTvShow(tvShowId!!))
        verify(remote).getAllTvShows((any()))
        assertNotNull(tvShowData)
        assertEquals(tvShowResponses[0].id, tvShowData.id)
    }
}