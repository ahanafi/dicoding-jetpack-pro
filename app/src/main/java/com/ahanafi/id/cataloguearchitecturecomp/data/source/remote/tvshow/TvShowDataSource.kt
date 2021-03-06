package com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.tvshow

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.TMDBApiService
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResponseData
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData
import com.ahanafi.id.cataloguearchitecturecomp.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowDataSource(
    private val tmdbApiService: TMDBApiService
) : PageKeyedDataSource<Int, ResultData>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ResultData>
    ) {
        EspressoIdlingResource.increment()
        tmdbApiService.getPopularTvShow(page = 1).enqueue(
            object : Callback<ResponseData> {
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Log.d("Error", "loadInitial TvShow")
                }

                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    val resultData = response.body()?.results ?: emptyList()
                    callback.onResult(resultData, null, 2)
                }

            }
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ResultData>) {
        EspressoIdlingResource.increment()
        tmdbApiService.getPopularTvShow(page = params.key).enqueue(
            object : Callback<ResponseData> {
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Log.d("Error", "loadAfter TvShow")
                }

                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    val resultData = response.body()?.results ?: emptyList()
                    callback.onResult(resultData, params.key + 1)
                }
            }
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ResultData>) {
    }

}