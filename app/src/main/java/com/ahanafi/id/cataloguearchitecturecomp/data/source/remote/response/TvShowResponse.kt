package com.ahanafi.id.cataloguearchitecturecomp.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowResponse(
    var id : Int? = 0,
    var title : String? = null,
    var releaseDate : String? = null,
    var overview : String? = null,
    var backdropPath : String? = null,
    var posterPath : String? = null,
    var voteCount : Int? = 0,
    var language : String? = null
) : Parcelable