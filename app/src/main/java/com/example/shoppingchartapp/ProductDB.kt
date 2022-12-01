package com.example.shoppingchartapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppingchartapp.model.Product

abstract class ProductDB : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object{
        private var instance: ProductDB ?= null

        fun getDatabase(context: Context): ProductDB?{
            if(instance != null)
                return instance
            instance = Room.databaseBuilder(
                context,
                ProductDB::class.java,
                "Product Database"
            ).fallbackToDestructiveMigration().build()

            return instance
        }
    }
}