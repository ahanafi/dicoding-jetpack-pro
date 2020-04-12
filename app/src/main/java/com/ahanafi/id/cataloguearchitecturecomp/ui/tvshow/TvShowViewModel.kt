package com.ahanafi.id.cataloguearchitecturecomp.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahanafi.id.cataloguearchitecturecomp.data.TvShow
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData

class TvShowViewModel(
    private val appDataRepository: AppDataRepository
) : ViewModel() {
    fun getTvShows(): LiveData<PagedList<ResultData>> = appDataRepository.getAllTvShows()
}