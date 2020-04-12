package com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.TMDBApiService
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData

class MovieDataSourceFactory(
    private val tmbdApiService: TMDBApiService
) : DataSource.Factory<Int, ResultData>() {

    private val liveDataMovie = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, ResultData> {
        val movieData = MovieDataSource(tmbdApiService)
        liveDataMovie.postValue(movieData)
        return movieData
    }
}