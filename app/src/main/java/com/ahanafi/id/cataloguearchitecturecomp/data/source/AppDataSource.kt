package com.ahanafi.id.cataloguearchitecturecomp.data.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.ahanafi.id.cataloguearchitecturecomp.data.Movie
import com.ahanafi.id.cataloguearchitecturecomp.data.TvShow
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.ApiResponse
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData

interface AppDataSource {
    fun getAllMovies(): LiveData<PagedList<ResultData>>

    fun getDetailMovie(movieId: Int?): LiveData<ResultData>

    fun getAllTvShows(): LiveData<PagedList<ResultData>>

    fun getDetailTvShow(tvShowId: Int?): LiveData<ResultData>

    fun addToFavorite(data: ResultData)

    fun removeFromFavorite(data: ResultData)

    fun getFavoriteMovies() : DataSource.Factory<Int, ResultData>

    fun getFavoriteTvShows() : DataSource.Factory<Int, ResultData>
}