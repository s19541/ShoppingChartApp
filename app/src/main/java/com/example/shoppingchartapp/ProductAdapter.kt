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
    private val textSize = 24f

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductListElementBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewName.text = products[position].name
        holder.binding.textViewPrice.text = products[position].price.toString() + " pln"
        holder.binding.textViewQuantity.text = products[position].quantity.toString()
        holder.binding.checkBoxBought.isChecked = products[position].bought

        holder.binding.textViewName.textSize = textSize
        holder.binding.textViewPrice.textSize = textSize
        holder.binding.textViewQuantity.textSize = textSize
        holder.binding.checkBoxBought.textSize = textSize




        holder.binding.root.setOnClickListener {
            delete(products[position].id)
            Toast.makeText(
                holder.binding.root.context,
                "Successfully removed product with id: ${products[position].id}",
                Toast.LENGTH_LONG
            ).show()
        }

        holder.binding.checkBoxBought.setOnClickListener {
            products[position].bought = holder.binding.checkBoxBought.isChecked
            updateProduct(products[position])
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

    fun updateProduct(product: Product){
        productViewModel.update(product)
    }
}