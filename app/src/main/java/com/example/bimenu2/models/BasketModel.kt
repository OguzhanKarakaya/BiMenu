package com.example.bimenu2.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BasketModel(
    @SerializedName("menuSubOption") var menuSubOption: MenuSubOptionModel? = null
) : Parcelable
