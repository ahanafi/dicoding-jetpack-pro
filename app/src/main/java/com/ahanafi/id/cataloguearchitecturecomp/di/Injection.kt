package com.ahanafi.id.cataloguearchitecturecomp.di

import android.content.Context
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.data.source.local.LocalDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.data.source.local.room.FavoriteDatabase
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.RemoteDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.TMDBApiService

object Injection {
    fun provideRepository(context: Context): AppDataRepository {
        val database = FavoriteDatabase.getInstance(context)
        val remoteDataRepository = RemoteDataRepository(TMDBApiService.create())
        val localDataRepository = LocalDataRepository.getInstance(database.favoriteDao)

        return AppDataRepository.getInstance(
            remoteDataRepository,
            localDataRepository
        )
    }
}