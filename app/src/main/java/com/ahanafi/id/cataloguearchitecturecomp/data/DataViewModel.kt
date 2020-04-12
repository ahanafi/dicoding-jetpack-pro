package com.ahanafi.id.cataloguearchitecturecomp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData

class DataViewModel(
    private val dataRepository: AppDataRepository
) : ViewModel() {
    fun getAllMovies(): LiveData<PagedList<ResultData>> = dataRepository.getAllMovies()
    fun getAllTvShows(): LiveData<PagedList<ResultData>> = dataRepository.getAllTvShows()
}