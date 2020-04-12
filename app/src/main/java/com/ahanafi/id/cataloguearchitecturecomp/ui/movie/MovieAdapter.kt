package com.ahanafi.id.cataloguearchitecturecomp.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahanafi.id.cataloguearchitecturecomp.BuildConfig
import com.ahanafi.id.cataloguearchitecturecomp.R
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData
import com.ahanafi.id.cataloguearchitecturecomp.ui.detail.movie.DetailMovieActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.items_data.view.*

class MovieAdapter internal constructor() :
    PagedListAdapter<ResultData, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private const val POSTER_URL = BuildConfig.POSTER_URL
        private const val BACKDROP_URL = BuildConfig.BACKDROP_URL

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultData>() {
            override fun areItemsTheSame(oldItem: ResultData, newItem: ResultData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ResultData, newItem: ResultData): Boolean =
                oldItem == newItem
        }

    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: ResultData) {
            with(itemView) {
                tv_item_name.text = movie.getRealName()
                tv_item_release.text = movie.getDate()
                tv_description.text = movie.overview
                setOnClickListener {
                    val intent = Intent(context, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movie.id)
                    }
                    context.startActivity(intent)
                }

                val posterPath = POSTER_URL + movie.posterPath

                Glide.with(context).load(posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.img_bg_card))
                    .into(img_item_photo)
            }
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): MovieViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.items_data, viewGroup, false)
        return MovieViewHolder(view)
    }


    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }
}