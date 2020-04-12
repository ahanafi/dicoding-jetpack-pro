package com.ahanafi.id.cataloguearchitecturecomp.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response.ResultData

@Database(
    entities = [ResultData::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract val favoriteDao : FavoriteDao

    companion object{
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        fun getInstance(context: Context): FavoriteDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "Favorite.db"
                ).build()
            }
    }
}