package com.ahanafi.id.cataloguearchitecturecomp.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites WHERE name = :title")
    fun getFavoriteMovies(title: String): DataSource.Factory<Int, ResultData>

    @Query("SELECT * FROM favorites WHERE title = :title")
    fun getFavoriteTvShows(title: String): DataSource.Factory<Int, ResultData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(resultData: ResultData)

    @Delete
    fun delete(resultData: ResultData)
}