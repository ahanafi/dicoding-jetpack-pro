package com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.TMDBApiService
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData
import com.ahanafi.id.cataloguearchitecturecomp.vo.Resource

class TvShowDataSourceFactory (
    private val tmdbApiService: TMDBApiService
) : DataSource.Factory<Int, ResultData>() {
    private val liveDataTvShow = MutableLiveData<TvShowDataSource>()

    override fun create(): DataSource<Int, ResultData> {
        val tvShowData = TvShowDataSource(tmdbApiService)
        liveDataTvShow.postValue(tvShowData)
        return tvShowData
    }


}