package com.ahanafi.id.cataloguearchitecturecomp.data.source.remote

import android.os.Handler
import com.ahanafi.id.cataloguearchitecturecomp.data.Movie
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.response.MovieResponse
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.response.TvShowResponse
import com.ahanafi.id.cataloguearchitecturecomp.utils.EspressoIdlingResource
import com.ahanafi.id.cataloguearchitecturecomp.utils.JsonHelper

class RemoteDataSource private constructor(
    private val jsonHelper: JsonHelper
){
    private val handler = Handler()

    companion object{
        private const val SERVICE_LATENCY_IN_MILLIS : Long = 3000

        @Volatile
        private var instance : RemoteDataSource? = null

        fun getInstance(helper: JsonHelper) : RemoteDataSource = instance ?: synchronized(this){
            instance ?: RemoteDataSource(helper)
        }
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllMoviesReceived(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllTvShowsReceived(jsonHelper.loadTvShow())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>)
    }

}