package com.ahanafi.id.cataloguearchitecturecomp.ui.detail.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ahanafi.id.cataloguearchitecturecomp.R
import com.ahanafi.id.cataloguearchitecturecomp.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory).get(DetailMovieViewModel::class.java)
        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE_ID)
            if(movieId != null) {
                viewModel.setSelectedMovie(movieId)
                viewModel.getMovie().observe(this, Observer { movie ->
                    title = movie.title

                    tv_title.text = movie.title
                    tv_release_date.text = movie.releaseDate
                    tv_vote.text = movie.voteCount.toString()
                    tv_overview.text = movie.overview

                    val posterPath = "https://image.tmdb.org/t/p/w185" + movie.posterPath
                    Glide.with(this)
                        .load(posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.img_bg_card).error(R.drawable.img_bg_card))
                        .into(poster_image)

                    val backdropPath = "https://image.tmdb.org/t/p/w185" + movie.backdropPath
                    Glide.with(this)
                        .load(backdropPath).apply {
                            RequestOptions.placeholderOf(R.drawable.img_bg_card).error(R.drawable.img_bg_card)
                            bitmapTransform(BlurTransformation(50, 8))
                        }
                        .into(backdrop_image)
                })
            }

        }
    }
}
