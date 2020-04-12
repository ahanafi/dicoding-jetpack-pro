package com.ahanafi.id.cataloguearchitecturecomp.data.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ahanafi.id.cataloguearchitecturecomp.data.source.local.LocalDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.ApiResponse
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.RemoteDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.TMDBApiService
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResponseData
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData
import com.ahanafi.id.cataloguearchitecturecomp.utils.AppExecutors
import com.ahanafi.id.cataloguearchitecturecomp.vo.Resource

class AppDataRepository(
    private val remoteDataRepository: RemoteDataRepository,
    private val localDataRepository: LocalDataRepository
) : AppDataSource {

    companion object {
        @Volatile
        private var instance: AppDataRepository? = null

        fun getInstance(
            remoteData : RemoteDataRepository,
            localData : LocalDataRepository
        ): AppDataRepository =
            instance ?: synchronized(this) {
                instance ?: AppDataRepository(
                    remoteData, localData
                )
            }
    }

    override fun getAllMovies(): LiveData<PagedList<ResultData>> = remoteDataRepository.getAllMovies()

    override fun getDetailMovie(movieId: Int?): LiveData<ResultData> {
        TODO("Not yet implemented")
    }

    override fun getAllTvShows(): LiveData<PagedList<ResultData>> =
        remoteDataRepository.getAllTvShows()

    override fun getDetailTvShow(tvShowId: Int?): LiveData<ResultData> {
        TODO("Not yet implemented")
    }

    override fun addToFavorite(data: ResultData) = localDataRepository.addToFavorite(data)

    override fun removeFromFavorite(data: ResultData) = localDataRepository.removeFromFavorite(data)

    override fun getFavoriteMovies(): DataSource.Factory<Int, ResultData> =
        localDataRepository.getFavoriteMovies()

    override fun getFavoriteTvShows(): DataSource.Factory<Int, ResultData> =
        localDataRepository.getFavoriteTvShows()

}