package com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.network.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorites")
data class ResultData(
    @PrimaryKey
    var id: Int? = 0,
    var title: String? = null,
    var name: String? = null,
    var releaseDate: String? = null,
    var firstAirDate: String? = null,
    var overview: String? = null,
    var backdropPath: String? = null,
    var posterPath: String? = null,
    var voteCount: Int? = 0,
    var language: String? = null
) : Parcelable {

    fun getRealName() : String?{
        return  if(title.isNullOrEmpty()) {
            name
        } else {
            title
        }
    }

    fun getDate() : String? {
        return if(releaseDate.isNullOrEmpty()) {
            firstAirDate
        } else {
            releaseDate
        }
    }
}
