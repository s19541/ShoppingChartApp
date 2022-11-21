package com.example.shoppingchartapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppingchartapp.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM Product WHERE id = :id")
    fun getProduct(id: Long): LiveData<Product>

    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Query("DELETE FROM Product WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM Product")
    fun deleteAll()

}