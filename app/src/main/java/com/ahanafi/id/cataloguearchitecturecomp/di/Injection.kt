package com.ahanafi.id.cataloguearchitecturecomp.di

import android.content.Context
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.RemoteDataSource
import com.ahanafi.id.cataloguearchitecturecomp.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context) : AppDataRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return AppDataRepository.getInstance(remoteDataSource)
    }
}