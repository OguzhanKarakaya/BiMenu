package com.example.bimenu2.models

data class MenuOptionModel(
    var menuSubOptionModel: MenuSubOptionModel? = null,
    var optionName: String? = "",
    var restaurantId: String? = ""
)
