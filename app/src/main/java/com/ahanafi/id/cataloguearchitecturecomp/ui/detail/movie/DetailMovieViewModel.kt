package com.ahanafi.id.cataloguearchitecturecomp.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ahanafi.id.cataloguearchitecturecomp.data.Movie
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository

class DetailMovieViewModel(
    private val appDataRepository: AppDataRepository
) : ViewModel() {
    private lateinit var movieId : String

    fun setSelectedMovie(movieId : String) {
        this.movieId = movieId
    }

    fun getMovie() : LiveData<Movie> = appDataRepository.getDetailMovie(movieId.toInt())
}