package com.ahanafi.id.cataloguearchitecturecomp.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ahanafi.id.cataloguearchitecturecomp.data.Movie
import com.ahanafi.id.cataloguearchitecturecomp.data.TvShow
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.utils.MovieDummy
import com.ahanafi.id.cataloguearchitecturecomp.utils.TvShowDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appDataRepository: AppDataRepository

    @Mock
    private lateinit var tvShowObserver: Observer<List<TvShow>>

    @Before
    fun setUp() {
        viewModel= TvShowViewModel(appDataRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = TvShowDummy.generateTvShow()
        val tvShow = MutableLiveData<List<TvShow>>()
        tvShow.value = dummyTvShow

        `when`(appDataRepository.getAllTvShows()).thenReturn(tvShow)
        val tvShowData = viewModel.getTvShows().value
        verify(appDataRepository).getAllTvShows()
        assertNotNull(tvShowData)
        assertEquals(20, tvShowData?.size)

        viewModel.getTvShows().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}