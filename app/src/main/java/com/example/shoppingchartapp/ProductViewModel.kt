package com.example.shoppingchartapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppingchartapp.model.Product
import com.google.firebase.database.FirebaseDatabase

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    var firebaseDatabase: FirebaseDatabase
    val allProducts: MutableLiveData<HashMap<String, Product>>

    init{
        firebaseDatabase = FirebaseDatabase.getInstance()
        repository = Repository(firebaseDatabase)
        allProducts = repository.allProducts
    }

    suspend fun insert(product: Product) = repository.insert(product)

    suspend fun update(product: Product) = repository.update(product)

    suspend fun delete(product: Product) = repository.delete(product)
}