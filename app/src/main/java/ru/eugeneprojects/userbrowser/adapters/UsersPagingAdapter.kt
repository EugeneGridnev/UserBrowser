package ru.eugeneprojects.userbrowser.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.eugeneprojects.userbrowser.R
import ru.eugeneprojects.userbrowser.databinding.ItemUserLayoutBinding
import ru.eugeneprojects.userbrowser.data.models.User

class UsersPagingAdapter :
    PagingDataAdapter<User, UsersPagingAdapter.ProductViewHolder>(UserDiffCallBack()) {

    private var onItemClickListener: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        return ProductViewHolder(
            ItemUserLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = getItem(position) ?: return
        holder.bind(product, onItemClickListener)
    }


    class ProductViewHolder(val binding: ItemUserLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onClickListener: ((User) -> Unit)? = null) {

            val name = user.name.first + " " + user.name.last

            Glide.with(itemView)
                .load(user.picture.large)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(binding.itemViewUserImage)
            binding.textViewUserName.text = name
            binding.textViewUserEmail.text = user.email
            itemView.setOnClickListener {
                onClickListener?.invoke(user)
            }
        }
    }

    class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (User) -> Unit) {
        onItemClickListener = listener
    }
}