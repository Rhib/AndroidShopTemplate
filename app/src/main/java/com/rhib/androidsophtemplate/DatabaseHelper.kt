package com.rhib.androidsophtemplate

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "productionDatabase"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Here, tables are created
        val createTableSQL = """" 
            CREATE TABLE Products (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                description TEXT,
                price REAL
            )
        """
        db?.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Here comes code to be executed when database version is being upgraded
        Log.d("DATABASE", "onUpgrade: Database upgraded")
    }

    data class Product(
        val name: String, val description: String, val price: Double

    )

    fun insertProduct(product: Product) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", product.name)
        values.put("description", product.description)
        values.put("price", product.price)
        db.insert("Products", null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun searchProduct(query: String): List<Product> {
        val products = ArrayList<Product>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Products WHERE name LIKE '%$query%'", null)

        if (cursor.moveToFirst()) {
            do {
                val product = Product(
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("description")),
                    cursor.getDouble(cursor.getColumnIndex("price"))
                )
                products.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return products
    }


}