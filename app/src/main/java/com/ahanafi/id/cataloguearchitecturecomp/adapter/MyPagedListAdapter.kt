package com.ahanafi.id.cataloguearchitecturecomp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahanafi.id.cataloguearchitecturecomp.BuildConfig.POSTER_URL
import com.ahanafi.id.cataloguearchitecturecomp.R
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData
import com.ahanafi.id.cataloguearchitecturecomp.databinding.ItemsDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MyPagedListAdapter :
    PagedListAdapter<ResultData, MyPagedListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultData>() {
            override fun areItemsTheSame(oldItem: ResultData, newItem: ResultData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ResultData, newItem: ResultData): Boolean =
                oldItem == newItem
        }
    }

    inner class MyViewHolder(
        private val binding: ItemsDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        internal fun bind(resultData: ResultData) {
            binding.apply {
                tvItemName.text = resultData.getRealName()
                tvDescription.text = resultData.overview
                tvItemRelease.text = resultData.getDate()

                val posterPath = POSTER_URL + resultData.posterPath

                Glide.with(binding.root).load(posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.img_bg_card))
                    .into(imgItemPhoto)

            }
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): MyPagedListAdapter.MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
        val binding = ItemsDataBinding.inflate(view, viewGroup, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPagedListAdapter.MyViewHolder, position: Int) {
        val resultData = getItem(position)
        /*resultData?.let { data ->
            holder.bind(data)
        }*/
        if(resultData != null) {
            holder.bind(resultData)
        } else {
            Log.d("DATA", "KOSONG")
        }
    }
}
