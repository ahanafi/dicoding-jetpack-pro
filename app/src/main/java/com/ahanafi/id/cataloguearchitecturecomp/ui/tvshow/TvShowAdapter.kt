package com.ahanafi.id.cataloguearchitecturecomp.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahanafi.id.cataloguearchitecturecomp.R
import com.ahanafi.id.cataloguearchitecturecomp.data.TvShow
import com.ahanafi.id.cataloguearchitecturecomp.ui.detail.tvshow.DetailTvShowActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.items_data.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal fun bind(tvShow: TvShow) {
            with(itemView) {
                tv_item_name.text = tvShow.title
                tv_item_release.text = tvShow.releaseDate
                tv_description.text = tvShow.overview
                setOnClickListener {
                    val intent = Intent(context, DetailTvShowActivity::class.java).apply {
                        putExtra(DetailTvShowActivity.EXTRA_TV_SHOW_ID, tvShow.id)
                    }
                    context.startActivity(intent)
                }

                val posterPath = "https://image.tmdb.org/t/p/w185" + tvShow.posterPath
                Glide.with(context).load(posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.img_bg_card))
                    .into(img_item_photo)
            }
        }
    }

    private val listTvShow = ArrayList<TvShow>()

    fun setTvShow(tvShow: List<TvShow>?) {
        if (tvShow == null) return
        listTvShow.clear()
        listTvShow.addAll(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_data, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int = listTvShow.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }
}