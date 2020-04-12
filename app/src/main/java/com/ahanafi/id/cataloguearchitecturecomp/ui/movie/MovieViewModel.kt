package com.ahanafi.id.cataloguearchitecturecomp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahanafi.id.cataloguearchitecturecomp.data.Movie
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData
import com.ahanafi.id.cataloguearchitecturecomp.vo.Resource

class MovieViewModel(
    private val appDataRepository: AppDataRepository
) : ViewModel() {
    fun getMovies(): LiveData<PagedList<ResultData>> = appDataRepository.getAllMovies()
}