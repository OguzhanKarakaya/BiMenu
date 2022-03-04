package com.example.bimenu2.models

data class CustomerModel(
    var phoneNumber: String? = "",
    var orderModel: ArrayList<OrderModel>? = null,
    var locationModel: LocationModel? = null
)
