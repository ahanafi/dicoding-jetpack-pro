package com.ahanafi.id.cataloguearchitecturecomp.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahanafi.id.cataloguearchitecturecomp.R
import com.ahanafi.id.cataloguearchitecturecomp.data.Movie
import com.ahanafi.id.cataloguearchitecturecomp.ui.detail.movie.DetailMovieActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.items_data.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                tv_item_name.text = movie.title
                tv_item_release.text = movie.releaseDate
                tv_description.text = movie.overview
                setOnClickListener{
                    val intent = Intent(context, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movie.id.toString())
                    }
                    context.startActivity(intent)
                }

                val posterPath = "https://image.tmdb.org/t/p/w185" + movie.posterPath
                Glide.with(context).load(posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.img_bg_card))
                    .into(img_item_photo)
            }
        }
    }

    private val listMovies = ArrayList<Movie>()

    fun setMovies(movie: List<Movie>) {
        listMovies.clear()
        listMovies.addAll(movie)
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): MovieViewHolder {
       val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.items_data, viewGroup, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }
}