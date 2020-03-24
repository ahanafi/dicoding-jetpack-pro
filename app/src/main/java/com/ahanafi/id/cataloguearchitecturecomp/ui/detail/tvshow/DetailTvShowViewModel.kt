package com.ahanafi.id.cataloguearchitecturecomp.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ahanafi.id.cataloguearchitecturecomp.data.TvShow
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository

class DetailTvShowViewModel(
    private val appDataRepository: AppDataRepository
) : ViewModel() {
    private lateinit var tvShowId : String

    fun setSelectedTvShow(tvShowId : String) {
        this.tvShowId = tvShowId
    }

    fun getTvShow() : LiveData<TvShow> = appDataRepository.getDetailTvShow(tvShowId.toInt())
}