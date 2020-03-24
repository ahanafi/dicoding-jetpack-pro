package com.ahanafi.id.cataloguearchitecturecomp.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ahanafi.id.cataloguearchitecturecomp.data.Movie
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository
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
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appDataRepository: AppDataRepository

    @Mock
    private lateinit var movieObserver: Observer<List<Movie>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(appDataRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = MovieDummy.generateMovies()
        val movies = MutableLiveData<List<Movie>>()
        movies.value = dummyMovies

        `when`(appDataRepository.getAllMovies()).thenReturn(movies)
        val movieData = viewModel.getMovies().value
        verify(appDataRepository).getAllMovies()
        assertNotNull(movieData)
        assertEquals(20, movieData?.size)

        viewModel.getMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovies)
    }
}