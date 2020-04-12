package com.ahanafi.id.cataloguearchitecturecomp.ui.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahanafi.id.cataloguearchitecturecomp.R
import com.ahanafi.id.cataloguearchitecturecomp.adapter.MyPagedListAdapter
import com.ahanafi.id.cataloguearchitecturecomp.data.DataViewModel
import com.ahanafi.id.cataloguearchitecturecomp.databinding.FragmentTvShowBinding
import com.ahanafi.id.cataloguearchitecturecomp.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show, container, false)
        binding.lifecycleOwner = this@TvShowFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataAdapter = MyPagedListAdapter()
        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModel = ViewModelProvider(this, factory).get(DataViewModel::class.java)

        viewModel.getAllTvShows().observe(viewLifecycleOwner, Observer { data ->
            dataAdapter.submitList(data)
            dataAdapter.notifyDataSetChanged()
        })

        binding.rvTvShow.apply {
            adapter = dataAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
        }
    }
}
