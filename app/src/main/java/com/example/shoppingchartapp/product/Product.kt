package com.example.shoppingchartapp.product

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class Product(
    var id: String,
    var name: String,
    var price: Double,
    var quantity: Int,
    var bought: Boolean
    ) : Serializable