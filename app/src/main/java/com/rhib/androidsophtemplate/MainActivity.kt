package com.rhib.androidsophtemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {


    private val TAG = "MAIN_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dbHelper = DatabaseHelper(this)
        var searchView:SearchView = findViewById(R.id.searchView)
        var addProdButt:Button = findViewById(R.id.addProductToDatabaseButton);

//        // Search bar searching
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                Log.d("SEARCHBAR", "onQueryTextSubmit: Debug query -> $query")
//                if (!query.isNullOrBlank()) {
//                    // What happens when search is executed
//                    val products = dbHelper.searchProduct(query)
//                    Log.d("SEARCHBAR", "onQueryTextSubmit: Search was executed")
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // What happens when text changes, w.g. search suggestions
//                return true
//            }
//        })

        addProdButt.setOnClickListener(View.OnClickListener {
            addProduct()
        })
    }

    private fun addProduct() {

        // Get fields and get values from them
        val productNameEditText:EditText = findViewById(R.id.productNameEditText)
        val productDescriptionEditText:EditText = findViewById(R.id.productDescriptionEditText)
        val productPriceEditText:EditText = findViewById(R.id.productPriceEditText)
        val enteredName = productNameEditText.toString()
        Log.d(TAG, "enteredName: $enteredName")
        val enteredDescription = productDescriptionEditText.toString()
        Log.d(TAG, "enteredDescription: $enteredDescription")
        val enteredPrice = productPriceEditText.toString()
        Log.d(TAG, "enteredPrice: $enteredPrice")

        val enteredPriceDouble = enteredPrice.toDouble()
        Log.d(TAG, "enteredPriceDouble: $enteredPriceDouble")


        // Check if all fields are filled
//        if (enteredName.isNotEmpty() && enteredDescription.isNotEmpty()) {
//
//            Log.d(
//                "ADD PRODUCT",
//                "addProduct: " + "name: $enteredName" + "desc: $enteredDescription" + "price $enteredPrice"
//            )
//            // If so, add product to database
////            val product = DatabaseHelper.Product(enteredName, enteredDescription, enteredPrice)
////            dbhelper.insertProduct(product)
//
//            // Reset input fields
//
//        } else {
//            Toast.makeText(this, "Values can't be empty!", Toast.LENGTH_SHORT).show()
//        }


//    }.setNegativeButton("Exit")
//    {
//        dialog _ ->
//        dialog.dismiss()
//    }.create()
//
//    dialog.show
    }

}