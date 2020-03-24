package com.ahanafi.id.cataloguearchitecturecomp.data.source

import androidx.lifecycle.LiveData
import com.ahanafi.id.cataloguearchitecturecomp.data.Movie
import com.ahanafi.id.cataloguearchitecturecomp.data.TvShow

interface AppDataSource {
    fun getAllMovies() : LiveData<List<Movie>>

    fun getDetailMovie(movieId : Int) : LiveData<Movie>

    fun getAllTvShows() : LiveData<List<TvShow>>

    fun getDetailTvShow(tvShowId : Int) : LiveData<TvShow>
}