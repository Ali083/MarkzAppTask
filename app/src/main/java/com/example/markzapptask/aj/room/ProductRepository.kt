package com.example.markzapptask.aj.room

import com.example.markzapptask.aj.FakeApiService
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {
    val products: Flow<List<ProductEntity>> = productDao.getProducts()

    suspend fun refreshProducts() {
        val apiService = FakeApiService.getInstance()
        val productsFromApi = apiService.getProducts()
        productDao.insertAll(productsFromApi)
    }
}
