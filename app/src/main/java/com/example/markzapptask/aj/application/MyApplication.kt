package com.example.markzapptask.aj.application

import android.app.Application
import com.example.markzapptask.aj.room.AppDatabase
import com.example.markzapptask.aj.room.ProductRepository

class MyApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val productRepository by lazy { ProductRepository(database.productDao()) }
}