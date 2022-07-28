package com.mohammadhashem.lastnews.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SourceX(
    var id: String,
    var name: String
):Parcelable