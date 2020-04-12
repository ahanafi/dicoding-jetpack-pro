package com.ahanafi.id.cataloguearchitecturecomp.ui.detail.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ahanafi.id.cataloguearchitecturecomp.R
import com.ahanafi.id.cataloguearchitecturecomp.databinding.ActivityDetailMovieBinding
import com.ahanafi.id.cataloguearchitecturecomp.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(DetailMovieViewModel::class.java)

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE_ID, 0)
            if (movieId != 0) {
                viewModel.getMovie(movieId).observe(this, Observer { movie ->
                    title = movie.title
                    tv_title.text = movie.title
                    tv_release_date.text = movie.releaseDate
                    tv_vote.text = movie.voteCount.toString()
                    tv_overview.text = movie.overview

                    val posterPath = "https://image.tmdb.org/t/p/w185" + movie.posterPath
                    Glide.with(this)
                        .load(posterPath)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.img_bg_card)
                                .error(R.drawable.img_bg_card)
                        )
                        .into(poster_image)

                    val backdropPath = "https://image.tmdb.org/t/p/w185" + movie.backdropPath
                    Glide.with(this)
                        .load(backdropPath).apply {
                            RequestOptions.placeholderOf(R.drawable.img_bg_card)
                                .error(R.drawable.img_bg_card)
                            bitmapTransform(BlurTransformation(50, 8))
                        }
                        .into(backdrop_image)
                })
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_favorite, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_favorite) {
            Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}
