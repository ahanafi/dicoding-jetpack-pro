package com.ahanafi.id.cataloguearchitecturecomp.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ahanafi.id.cataloguearchitecturecomp.data.TvShow
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.utils.TvShowDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {

    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = TvShowDummy.generateTvShow()[0]
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appDataRepository: AppDataRepository

    @Mock
    private lateinit var tvShowObserver: Observer<TvShow>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(appDataRepository)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShow>()
        tvShow.value = dummyTvShow

        `when`(appDataRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)

        val tvShowData = viewModel.getTvShow(tvShowId).value as TvShow
        verify(appDataRepository).getDetailTvShow(tvShowId)
        assertNotNull(tvShow)
        assertEquals(tvShowData.id, dummyTvShow.id)
        assertEquals(tvShowData.title, dummyTvShow.title)
        assertEquals(tvShowData.releaseDate, dummyTvShow.releaseDate)
        assertEquals(tvShowData.language, dummyTvShow.language)
        assertEquals(tvShowData.overview, dummyTvShow.overview)
        assertEquals(tvShowData.voteCount, dummyTvShow.voteCount)
        assertEquals(tvShowData.posterPath, dummyTvShow.posterPath)
        assertEquals(tvShowData.backdropPath, dummyTvShow.backdropPath)

        viewModel.getTvShow(tvShowId).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}