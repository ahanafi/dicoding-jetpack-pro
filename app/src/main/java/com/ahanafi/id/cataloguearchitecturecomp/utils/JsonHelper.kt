package com.ahanafi.id.cataloguearchitecturecomp.utils

import android.content.Context
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.MovieResponse
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.TvShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieResponse> {
        val listMovie = ArrayList<MovieResponse>()
        try {
            val movieJsonFile = parsingFileToString("movie.json").toString()
            val resultObject = JSONObject(movieJsonFile)
            val listDataMovie = resultObject.getJSONArray("results")

            for (i in 0 until listDataMovie.length()) {
                val movieData = listDataMovie.getJSONObject(i)
                val movieResponse = MovieResponse()

                movieResponse.id = movieData.getInt("id")
                movieResponse.title = movieData.getString("title")
                movieResponse.language = movieData.getString("original_language")
                movieResponse.backdropPath = movieData.getString("backdrop_path")
                movieResponse.overview = movieData.getString("overview")
                movieResponse.posterPath = movieData.getString("poster_path")
                movieResponse.releaseDate = movieData.getString("release_date")
                movieResponse.voteCount = movieData.getInt("vote_count")

                listMovie.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return listMovie
    }

    fun loadTvShow(): List<TvShowResponse> {
        val listTvShow = ArrayList<TvShowResponse>()
        try {
            val tvShowJsonFile = parsingFileToString("tvshow.json").toString()
            val resultObject = JSONObject(tvShowJsonFile)
            val listDataTvShow = resultObject.getJSONArray("results")

            for (i in 0 until listDataTvShow.length()) {
                val movieData = listDataTvShow.getJSONObject(i)
                val tvShowResponse = TvShowResponse()

                tvShowResponse.id = movieData.getInt("id")
                tvShowResponse.title = movieData.getString("name")
                tvShowResponse.language = movieData.getString("original_language")
                tvShowResponse.backdropPath = movieData.getString("backdrop_path")
                tvShowResponse.overview = movieData.getString("overview")
                tvShowResponse.posterPath = movieData.getString("poster_path")
                tvShowResponse.releaseDate = movieData.getString("first_air_date")
                tvShowResponse.voteCount = movieData.getInt("vote_count")

                listTvShow.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return listTvShow
    }

}