package com.ahanafi.id.cataloguearchitecturecomp.ui.detail.tvshow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ahanafi.id.cataloguearchitecturecomp.R
import com.ahanafi.id.cataloguearchitecturecomp.databinding.ActivityDetailTvShowBinding
import com.ahanafi.id.cataloguearchitecturecomp.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowBinding
    private lateinit var viewModel: DetailTvShowViewModel

    companion object {
        const val EXTRA_TV_SHOW_ID = "extra_tv_show_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tv_show)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(DetailTvShowViewModel::class.java)
        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_TV_SHOW_ID, 0)
            if (tvShowId != 0) {
                viewModel.getTvShow(tvShowId).observe(this, Observer { tvShow ->
                    title = tvShow.title

                    tv_title.text = tvShow.title
                    tv_release_date.text = tvShow.releaseDate
                    tv_vote.text = tvShow.voteCount.toString()
                    tv_overview.text = tvShow.overview

                    val posterPath = "https://image.tmdb.org/t/p/w185" + tvShow.posterPath
                    Glide.with(this)
                        .load(posterPath)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.img_bg_card)
                                .error(R.drawable.img_bg_card)
                        )
                        .into(poster_image)

                    val backdropPath = "https://image.tmdb.org/t/p/w185" + tvShow.backdropPath
                    Glide.with(this)
                        .load(backdropPath).apply {
                            RequestOptions.placeholderOf(R.drawable.img_bg_card)
                                .error(R.drawable.img_bg_card)
                            RequestOptions.bitmapTransform(BlurTransformation(50, 8))
                        }
                        .into(backdrop_image)
                })

            }
        }

    }
}
