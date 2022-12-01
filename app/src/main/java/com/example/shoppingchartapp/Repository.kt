package com.example.shoppingchartapp

import androidx.lifecycle.MutableLiveData
import com.example.shoppingchartapp.model.Product
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class Repository(private val fbdb: FirebaseDatabase) {

    val allProducts: MutableLiveData<HashMap<String, Product>> =
        MutableLiveData<HashMap<String, Product>>().also{
            it.value = HashMap<String, Product>()
        }

    init{
        fbdb.getReference("Product").addChildEventListener(
            object: ChildEventListener{
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val product = Product(
                        id = snapshot.ref.key as String,
                        name = snapshot.child("name").getValue(String::class.java)!!,
                        price = snapshot.child("price").getValue(Double::class.java)!!,
                        quantity = snapshot.child("quantity").getValue(Int::class.java)!!,
                        bought = snapshot.child("bought").getValue(Boolean::class.java)!!
                    )
                    allProducts.value?.put(product.id, product)
                    allProducts.postValue(allProducts.value)
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    val product = Product(
                        id = snapshot.ref.key as String,
                        name = snapshot.child("name").getValue(String::class.java)!!,
                        price = snapshot.child("price").getValue(Double::class.java)!!,
                        quantity = snapshot.child("quantity").getValue(Int::class.java)!!,
                        bought = snapshot.child("bought").getValue(Boolean::class.java)!!
                    )
                    allProducts.value?.set(product.id, product)
                    allProducts.postValue(allProducts.value)
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    val product = Product(
                        id = snapshot.ref.key as String,
                        name = snapshot.child("name").getValue(String::class.java)!!,
                        price = snapshot.child("price").getValue(Double::class.java)!!,
                        quantity = snapshot.child("quantity").getValue(Int::class.java)!!,
                        bought = snapshot.child("bought").getValue(Boolean::class.java)!!
                    )
                    allProducts.value?.remove(snapshot.ref.key)
                    allProducts.postValue(allProducts.value)
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                    TODO("Not yet implemented")
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    fun insert(product: Product){
        fbdb.getReference("Product").push().also{
            product.id = it.ref.key.toString()
            it.setValue(product)
        }
    }

    fun update(product: Product){
        //TODO W uwierzytelnianiu przed Product UID/ zalogowanego uzytkownika
        var productRef = fbdb.getReference("Product/${product.id}")
        productRef.child("name").setValue(product.name)
        productRef.child("quantity").setValue(product.quantity)
        productRef.child("price").setValue(product.price)
        productRef.child("bought").setValue(product.bought)
    }

    fun delete(product: Product) = fbdb.getReference("Product/${product.id}").removeValue()

}