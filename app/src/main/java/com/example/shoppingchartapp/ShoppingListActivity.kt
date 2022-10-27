package com.example.shoppingchartapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            adapter.add(
                Product(
                    name = "Milk",
                    quantity = 10,
                    price = 12.50f,
                    bought = false
                )
            )
        }


    }
}