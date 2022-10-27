package com.example.shoppingchartapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.shoppingchartapp.model.Product

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    val allProducts: LiveData<List<Product>>

    init{
        repository = Repository(ProductDB.getDatabase(application)!!.productDao())
        allProducts = repository.allProducts
    }

    fun insert(product: Product) = repository.insert(product)

    fun update(product: Product) = repository.update(product)

    fun delete(id: Long) = repository.delete(id)

    fun deleteAll() = repository.deleteAll()
}