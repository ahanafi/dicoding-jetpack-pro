package com.ahanafi.id.cataloguearchitecturecomp.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ahanafi.id.cataloguearchitecturecomp.data.Movie
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData

class DetailMovieViewModel(
    private val appDataRepository: AppDataRepository
) : ViewModel() {
    fun getMovie(movieId: Int?): LiveData<ResultData> = appDataRepository.getDetailMovie(movieId)
}