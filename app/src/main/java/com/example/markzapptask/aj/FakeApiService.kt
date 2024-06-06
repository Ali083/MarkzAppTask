package com.example.markzapptask.aj

import com.example.markzapptask.aj.room.ProductEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface FakeApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductEntity>

    companion object {
        private var retrofitService: FakeApiService? = null

        fun getInstance(): FakeApiService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://fakestoreapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(FakeApiService::class.java)
            }
            return retrofitService!!
        }
    }
}