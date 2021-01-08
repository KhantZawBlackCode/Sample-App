package com.khantzaw.myapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    val albumID: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String) : Parcelable
