package com.example.bimenu2.models

data class OrderModel(
    var orderName: String? = "",
    var orderPrice: Double? = 0.0,
    var orderDate: String? = "",
    var orderTime: String? = "",
    var restaurantName: String? = ""
)
