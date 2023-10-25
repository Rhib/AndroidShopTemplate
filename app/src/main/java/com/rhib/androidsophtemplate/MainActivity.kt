package com.rhib.androidsophtemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView
    private lateinit var addProdButt: Button
    private lateinit var dbhelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dbhelper = DatabaseHelper(this)

        searchView = findViewById(R.id.searchView)
        addProdButt = findViewById(R.id.addProductToDatabaseButton)

        // Search bar searching
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("SEARCHBAR", "onQueryTextSubmit: Debug query -> $query")
                if (!query.isNullOrBlank()) {
                    // What happens when search is executed
                    val products = dbhelper.searchProduct(query)
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
            showAddProductDialog()
        })
    }

    private fun showAddProductDialog() {
        // TODO create xlm pendant
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_product, null)
        val productNameEditText = dialogView.findViewById<EditText>(R.id.productNameEditText)
        val productDescriptionEditText =
            dialogView.findViewById<EditText>(R.id.productDescriptionEditText)
        val productPriceditText = dialogView.findViewById<EditText>(R.id.productPriceEditText)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add product")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog _ ->
                val name = productNameEditText.text.toString()
                val description = productDescriptionEditText.text.toString()
                val priceText = productPriceditText.text.toString()

                if (name.isNotEmpty() && description.isNotEmpty() && priceText.isNotEmpty()) {
                    val price = priceText.toDouble()
                    val product = DatabaseHelper.Product(name, description, price)
                    dbhelper.insertProduct(product)

                    // Reset input fields
                    productNameEditText.text.clear()
                    productDescriptionEditText.text.clear()
                    productPriceEditText.text.clear()
                } else {
                    Toast.makeText(this, "Values can't be empty!", Toast.LENGTH_SHORT).show()
                }

                dialog.dismiss()
            }
            .setNegativeButton("Exit") { dialog _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show
    }
}