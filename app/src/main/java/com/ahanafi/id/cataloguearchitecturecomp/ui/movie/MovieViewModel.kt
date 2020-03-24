package com.ahanafi.id.cataloguearchitecturecomp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ahanafi.id.cataloguearchitecturecomp.data.Movie
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository

class MovieViewModel(
    private val appDataRepository: AppDataRepository
) : ViewModel() {
    fun getMovies() : LiveData<List<Movie>> = appDataRepository.getAllMovies()
}