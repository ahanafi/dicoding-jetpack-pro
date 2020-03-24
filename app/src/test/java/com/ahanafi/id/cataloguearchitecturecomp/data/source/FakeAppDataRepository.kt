package com.ahanafi.id.cataloguearchitecturecomp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahanafi.id.cataloguearchitecturecomp.data.Movie
import com.ahanafi.id.cataloguearchitecturecomp.data.TvShow
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.RemoteDataSource
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.response.MovieResponse
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.response.TvShowResponse

class FakeAppDataRepository(
    private val remoteDataSource: RemoteDataSource
) : AppDataSource {
    override fun getAllMovies(): LiveData<List<Movie>> {
        val movieResult = MutableLiveData<List<Movie>>()

        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                val movieList = ArrayList<Movie>()
                for (response in movieResponse) {
                    val movie = Movie(
                        response.id, response.title, response.releaseDate, response.overview,
                        response.backdropPath,response.posterPath, response.voteCount, response.language
                    )
                    movieList.add(movie)
                }
                movieResult.postValue(movieList)
            }
        })

        return movieResult
    }

    override fun getDetailMovie(movieId : Int): LiveData<Movie> {
        val movieResult = MutableLiveData<Movie>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback{
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                lateinit var movie : Movie
                for(response in movieResponse) {
                    if(movieId == response.id) {
                        movie = Movie(
                            response.id, response.title, response.releaseDate, response.overview,
                            response.backdropPath,response.posterPath, response.voteCount, response.language
                        )
                    }
                    movieResult.postValue(movie)
                    break
                }
            }
        })
        return movieResult
    }

    override fun getAllTvShows(): LiveData<List<TvShow>> {
        val tvShowResult = MutableLiveData<List<TvShow>>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShow>()
                for (response in tvShowResponse) {
                    val tvShow = TvShow(
                        response.id, response.title, response.releaseDate, response.overview,
                        response.backdropPath,response.posterPath, response.voteCount, response.language
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResult.postValue(tvShowList)
            }
        })

        return tvShowResult
    }

    override fun getDetailTvShow(tvShowId : Int): LiveData<TvShow> {
        val tvShowResult = MutableLiveData<TvShow>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>) {
                lateinit var tvShow : TvShow
                for (response in tvShowResponse) {
                    if(response.id == tvShowId) {
                        tvShow = TvShow(
                            response.id, response.title, response.releaseDate, response.overview,
                            response.backdropPath,response.posterPath, response.voteCount, response.language
                        )
                    }
                    tvShowResult.postValue(tvShow)
                    break
                }
            }
        })

        return tvShowResult
    }
}