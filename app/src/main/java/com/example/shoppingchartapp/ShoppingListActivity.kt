package com.example.shoppingchartapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingchartapp.databinding.ActivityShoppingListBinding
import com.example.shoppingchartapp.model.Product
import java.util.Observer

class ShoppingListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productViewModel = ProductViewModel(application)

        val adapter = ProductAdapter(productViewModel)

        binding.productsRv.layoutManager = LinearLayoutManager(this)
        binding.productsRv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.productsRv.adapter = adapter
        productViewModel.allProducts.observe(this, androidx.lifecycle.Observer {
            it.let{
                adapter.setProducts(it)
            }
        })

        binding.addButton.setOnClickListener{
            addButtonClicked(adapter)
        }
    }

    private fun addButtonClicked(adapter: ProductAdapter){
        val name = binding.editTextName.text.toString()
        val quantity =  binding.editTextQuantity.text.toString().toIntOrNull() ?: 0
        val price = binding.editTextPrice.text.toString().toFloatOrNull() ?: 0f
        if(name.isEmpty()){
            Toast.makeText(this, "Name can't be empty", Toast.LENGTH_LONG).show()
            return
        }

        adapter.add(
            Product(
                name = name,
                quantity = quantity,
                price = price,
                bought = false
            )
        )
        Toast.makeText(this, "Added new product: $name" , Toast.LENGTH_LONG).show()
    }
}