package com.example.markzapptask.aj.room
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.markzapptask.aj.dataclass.Rating

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)