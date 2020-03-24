package com.ahanafi.id.cataloguearchitecturecomp.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ahanafi.id.cataloguearchitecturecomp.data.TvShow
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository

class TvShowViewModel(
    private val appDataRepository: AppDataRepository
) : ViewModel() {
    fun getTvShows() : LiveData<List<TvShow>> = appDataRepository.getAllTvShows()
}