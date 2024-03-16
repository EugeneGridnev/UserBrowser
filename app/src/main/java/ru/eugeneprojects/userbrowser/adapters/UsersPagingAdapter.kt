package ru.eugeneprojects.userbrowser.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.eugeneprojects.userbrowser.R
import ru.eugeneprojects.userbrowser.databinding.ItemUserLayoutBinding
import ru.eugeneprojects.userbrowser.models.Result

class UsersPagingAdapter :
    PagingDataAdapter<Result, UsersPagingAdapter.ProductViewHolder>(UserDiffCallBack()) {

    private var onItemClickListener: ((Result) -> Unit)? = null

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

        fun bind(result: Result, onClickListener: ((Result) -> Unit)? = null) {

            val name = result.name.first + result.name.last

            Glide.with(itemView)
                .load(result.picture.large)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(binding.itemViewUserImage)
            binding.textViewUserName.text = name
            binding.textViewUserTelephoneNumber.text = result.phone
            itemView.setOnClickListener {
                onClickListener?.invoke(result)
            }
        }
    }

    class UserDiffCallBack : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
}