package com.ahanafi.id.cataloguearchitecturecomp.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ahanafi.id.cataloguearchitecturecomp.data.TvShow
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData

class DetailTvShowViewModel(
    private val appDataRepository: AppDataRepository
) : ViewModel() {
    fun getTvShow(tvShowId: Int?): LiveData<ResultData> = appDataRepository.getDetailTvShow(tvShowId)
}