package com.ahanafi.id.cataloguearchitecturecomp.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.ahanafi.id.cataloguearchitecturecomp.data.source.local.room.FavoriteDao
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData

class LocalDataRepository(
    private val favoriteDao: FavoriteDao
){
    companion object{
        private var INSTANCE: LocalDataRepository? = null

        fun getInstance(favoriteDao: FavoriteDao): LocalDataRepository = INSTANCE ?: LocalDataRepository(favoriteDao)
    }

    fun getFavoriteMovies(): DataSource.Factory<Int, ResultData> = favoriteDao.getFavoriteMovies("")

    fun getFavoriteTvShows(): DataSource.Factory<Int, ResultData> = favoriteDao.getFavoriteTvShows("")

    fun addToFavorite(resultData: ResultData) = favoriteDao.insert(resultData)

    fun removeFromFavorite(resultData: ResultData) = favoriteDao.delete(resultData)
}