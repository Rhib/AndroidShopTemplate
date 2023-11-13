package com.rhib.androidsophtemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dbHelper = DatabaseHelper(this)
        val searchView = R.id.searchView
        var addProdButt = R.id.addProductToDatabaseButton

        // Search bar searching
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("SEARCHBAR", "onQueryTextSubmit: Debug query -> $query")
                if (!query.isNullOrBlank()) {
                    // What happens when search is executed
                    val products = dbHelper.searchProduct(query)
                    Log.d("SEARCHBAR", "onQueryTextSubmit: Search was executed")
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // What happens when text changes, w.g. search suggestions
                return true
            }
        })

        addProdButt.setOnClickListener(View.OnClickListener {
            addProduct()
        })
    }

    private fun addProduct() {

        // Get fields and get values from them
        val productNameEditText = R.id.productNameEditText
        val productDescriptionEditText = R.id.productDescriptionEditText
        val productPriceEditText = R.id.productPriceEditText
        val enteredName = productNameEditText.toString()
        val enteredDescription = productDescriptionEditText.toString()
        val enteredPrice = productPriceEditText.toDouble()

        // Check if all fields are filled
        if (enteredName.isNotEmpty() && enteredDescription.isNotEmpty()) {

            Log.d(
                "ADD PRODUCT",
                "addProduct: " + "name: $enteredName" + "desc: $enteredDescription" + "price $enteredPrice"
            )
            // If so, add product to database
//            val product = DatabaseHelper.Product(enteredName, enteredDescription, enteredPrice)
//            dbhelper.insertProduct(product)

            // Reset input fields

        } else {
            Toast.makeText(this, "Values can't be empty!", Toast.LENGTH_SHORT).show()
        }


//    }.setNegativeButton("Exit")
//    {
//        dialog _ ->
//        dialog.dismiss()
//    }.create()
//
//    dialog.show
    }

}