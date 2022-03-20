package com.example.bimenu2.models

data class MenuOptionModel(
    var restaurantId: String? = "",
    var optionName: String? = "",
    var menuSubOptionModel: ArrayList<MenuSubOptionModel>? = null
)
