package com.example.shoppingchartapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingchartapp.databinding.ProductListElementBinding
import com.example.shoppingchartapp.model.Product

class ProductAdapter(private val productViewModel: ProductViewModel) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val binding: ProductListElementBinding) : RecyclerView.ViewHolder(binding.root)

    private var products = emptyList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductListElementBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.binding.tvName.text = products[position].name
        holder.binding.tvPrice.text = products[position].price.toString()
        holder.binding.tvQuantity.text = products[position].quantity.toString()
        holder.binding.checkBox.isChecked = products[position].bought
        holder.binding.root.setOnClickListener {
            delete(products[position].id)
            Toast.makeText(
                holder.binding.root.context,
                "Successfully removed product with id: ${products[position].id}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun getItemCount(): Int = products.size

    fun add(product: Product){
        productViewModel.insert(product)
        notifyDataSetChanged()
    }

    fun delete(id: Long){
        productViewModel.delete(id)
    }

    fun setProducts(allProduct: List<Product>){
        products = allProduct
        notifyDataSetChanged()
    }
}