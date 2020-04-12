package com.ahanafi.id.cataloguearchitecturecomp.data.source.remote

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.movie.MovieDataSourceFactory
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.TMDBApiService
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.tvshow.TvShowDataSourceFactory

class RemoteDataRepository(
    tmdbApiService: TMDBApiService
) {
    private val movieDataFactory = MovieDataSourceFactory(tmdbApiService)
    private val tvShowDataFactory = TvShowDataSourceFactory(tmdbApiService)


    private val pagingConfig = PagedList.Config.Builder()
        .setPageSize(5)
        .setInitialLoadSizeHint(10)
        .setEnablePlaceholders(false)
        .build()


    fun getAllMovies(): LiveData<PagedList<ResultData>> =
        LivePagedListBuilder(movieDataFactory, pagingConfig).build()

    fun getAllTvShows(): LiveData<PagedList<ResultData>> =
        LivePagedListBuilder(tvShowDataFactory, pagingConfig).build()
}