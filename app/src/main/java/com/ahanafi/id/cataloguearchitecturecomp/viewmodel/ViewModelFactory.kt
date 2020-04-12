package com.ahanafi.id.cataloguearchitecturecomp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahanafi.id.cataloguearchitecturecomp.data.DataViewModel
import com.ahanafi.id.cataloguearchitecturecomp.data.source.AppDataRepository
import com.ahanafi.id.cataloguearchitecturecomp.di.Injection
import com.ahanafi.id.cataloguearchitecturecomp.ui.detail.movie.DetailMovieViewModel
import com.ahanafi.id.cataloguearchitecturecomp.ui.detail.tvshow.DetailTvShowViewModel
import com.ahanafi.id.cataloguearchitecturecomp.ui.movie.MovieViewModel
import com.ahanafi.id.cataloguearchitecturecomp.ui.tvshow.TvShowViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val appDataRepository: AppDataRepository
) : ViewModelProvider.NewInstanceFactory() {

    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context) = INSTANCE ?: synchronized(this){
            INSTANCE ?: ViewModelFactory(Injection.provideRepository(context))
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(appDataRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(appDataRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(appDataRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                DetailTvShowViewModel(appDataRepository) as T
            }
            modelClass.isAssignableFrom(DataViewModel::class.java) -> {
                DataViewModel(appDataRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}