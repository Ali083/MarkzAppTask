package com.example.markzapptask.aj.adapters

import com.example.markzapptask.databinding.ItemUserBinding
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.markzapptask.aj.room.ProductEntity

class UserAdapter : RecyclerView.Adapter<UserAdapter.ProductViewHolder>() {

    private var products: List<ProductEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun setProducts(products: List<ProductEntity>) {
        this.products = products
        notifyDataSetChanged()
    }

    class ProductViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductEntity) {
            // Show the progress bar
            binding.progressBar.visibility = View.VISIBLE

            // Load image using Glide with a request listener
            Glide.with(binding.productImage.context)
                .load(product.image)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        // Hide the progress bar when the image load fails
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        // Hide the progress bar when the image load succeeds
                        binding.progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(binding.productImage)

            binding.productTitle.text = product.title
            binding.productPrice.text = "$${product.price}"
            binding.productRating.text = "Rating: ${product.rating.rate}"
        }
    }
}
