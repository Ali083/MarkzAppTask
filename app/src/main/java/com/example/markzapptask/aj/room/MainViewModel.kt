package com.example.markzapptask.aj.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val productRepository: ProductRepository) : ViewModel() {
    val products: LiveData<List<ProductEntity>> = productRepository.products.asLiveData()

    fun refreshProducts() {
        viewModelScope.launch {
            productRepository.refreshProducts()
        }
    }
}
