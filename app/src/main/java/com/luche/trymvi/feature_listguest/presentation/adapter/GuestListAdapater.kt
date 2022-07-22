package com.luche.trymvi.feature_listguest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luche.trymvi.databinding.GuestItemListBinding
import com.luche.trymvi.feature_savename.data.entity.Guest

class GuestListAdapater(
    private val onItemClick: (Int) -> Unit
) : ListAdapter<Guest, GuestListAdapater.GuestVH>(diffCallback) {

    inner class GuestVH(
        private val binding: GuestItemListBinding,
        private val onItemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(guest: Guest) {
            binding.root.setOnClickListener {
                onItemClick(guest.id)
            }
            binding.tvGuestId.text = guest.id.toString()
            binding.tvGuestName.text = guest.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestVH {
        return GuestVH(
            GuestItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: GuestVH, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Guest>() {
            override fun areItemsTheSame(oldItem: Guest, newItem: Guest): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Guest, newItem: Guest): Boolean {
                return oldItem == newItem
            }
        }
    }

}

