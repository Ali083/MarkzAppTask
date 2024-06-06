package com.example.markzapptask.aj.adapters

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
import com.example.markzapptask.databinding.ItemStatusBinding

class StatusAdapter : RecyclerView.Adapter<StatusAdapter.UserViewHolder>() {
    private var users: List<ProductEntity> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun setUsers(users: List<ProductEntity>) {
        this.users = users
        notifyDataSetChanged()
    }

    class UserViewHolder(private val binding: ItemStatusBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ProductEntity) {
            // Show the progress bar
            binding.progressBar.visibility = View.VISIBLE

            // Load image using Glide with a request listener
            Glide.with(binding.statusCircleImage.context)
                .load(user.image)
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
                .into(binding.statusCircleImage)

            binding.sText.text = user.title
        }
    }
}
