package com.example.bimenu2.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuSubOptionModel(
    var name: String? = "",
    var price: String? = "",
    var description: String? = "",
    var imageUrl: String? = "",
    var id: String? = ""
) : Parcelable
