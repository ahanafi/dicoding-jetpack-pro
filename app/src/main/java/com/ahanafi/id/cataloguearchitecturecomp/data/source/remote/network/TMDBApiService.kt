package com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network

import com.ahanafi.id.cataloguearchitecturecomp.BuildConfig
import com.ahanafi.id.cataloguearchitecturecomp.BuildConfig.API_KEY
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResponseData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApiService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("page") page: Int = 0
    ): Call<ResponseData>

    @GET("tv/popular")
    fun getPopularTvShow(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("page") page: Int = 0
    ): Call<ResponseData>


    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL

        fun create(): TMDBApiService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitBuilder.create(TMDBApiService::class.java)
        }
    }
}