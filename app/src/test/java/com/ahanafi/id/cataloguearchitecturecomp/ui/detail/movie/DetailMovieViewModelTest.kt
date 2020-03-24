package com.ahanafi.id.cataloguearchitecturecomp.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ahanafi.id.cataloguearchitecturecomp.data.Movie
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.data.source.FakeAppDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.utils.MovieDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = MovieDummy.generateMovies()[0]
    private val movieId = dummyMovie.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appDataRepository: AppDataRepository

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(appDataRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<Movie>()
        movie.value = dummyMovie

        `when`(appDataRepository.getDetailMovie(movieId.toInt())).thenReturn(movie)
        val movieData = viewModel.getMovie().value as Movie
        verify(appDataRepository).getDetailMovie(movieId.toInt())

        assertNotNull(movieData)
        assertEquals(movieData.id, dummyMovie.id)
        assertEquals(movieData.title, dummyMovie.title)
        assertEquals(movieData.releaseDate, dummyMovie.releaseDate)
        assertEquals(movieData.language, dummyMovie.language)
        assertEquals(movieData.overview, dummyMovie.overview)
        assertEquals(movieData.voteCount, dummyMovie.voteCount)
        assertEquals(movieData.posterPath, dummyMovie.posterPath)
        assertEquals(movieData.backdropPath, dummyMovie.backdropPath)

    }
}