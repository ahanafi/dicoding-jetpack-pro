package com.ahanafi.id.cataloguearchitecturecomp.ui.main

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ahanafi.id.cataloguearchitecturecomp.R
import com.ahanafi.id.cataloguearchitecturecomp.ui.movie.MovieFragment
import com.ahanafi.id.cataloguearchitecturecomp.ui.tvshow.TvShowFragment

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_movie, R.string.tab_tv_show)
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> MovieFragment()
        1 -> TvShowFragment()
        else -> Fragment()
    }

    override fun getPageTitle(position: Int): CharSequence? =
        context.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2
}