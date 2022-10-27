package com.example.shoppingchartapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey var id: Long = 0,
    var name: String,
    var price: Float,
    var quantity: Int,
    var bought: Boolean
    )