package ru.eugeneprojects.userbrowser.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.eugeneprojects.userbrowser.R
import ru.eugeneprojects.userbrowser.data.models.User
import ru.eugeneprojects.userbrowser.databinding.ItemUserLayoutBinding

class UsersPagingAdapter :
    PagingDataAdapter<User, UsersPagingAdapter.UserViewHolder>(UserDiffCallBack()) {

    private var onItemClickListener: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        return UserViewHolder(
            ItemUserLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = getItem(position) ?: return
        holder.bind(user, onItemClickListener)
    }


    class UserViewHolder(private val binding: ItemUserLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onClickListener: ((User) -> Unit)? = null) {

            val name = "${user.name.first} ${user.name.last}"
            val address =
                "${user.location.street.number} ${user.location.street.name} " +
                        "${user.location.city} ${user.location.state} ${user.location.country}"

            Glide.with(itemView)
                .load(user.picture.large)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(binding.itemViewItemUserImage)
            binding.textViewItemUserName.text = name
            binding.textViewItemUserAddress.text = address
            binding.textViewUserItemTelephoneNumber.text = user.cell
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