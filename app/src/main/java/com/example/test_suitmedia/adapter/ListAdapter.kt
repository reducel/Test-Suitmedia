package com.example.test_suitmedia.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test_suitmedia.data.response.DataItem
import com.example.test_suitmedia.databinding.ItemUserBinding
import com.example.test_suitmedia.ui.third_screen.ThirdScreenActivity

class ListAdapter(private val context: Context): PagingDataAdapter<DataItem, ListAdapter.MyViewHolder>(DIFF_CALLBACK){

    class MyViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            binding.tvItemName.text = user.firstName + " " + user.lastName
            binding.tvItemEmail.text = user.email
            Glide.with(itemView.context)
                .load(user.avatar)
                .into(binding.imgItemPhoto)
        }
    }

    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data!!)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent()
            intent.putExtra("name", data?.firstName + " " + data?.lastName)
            (context as ThirdScreenActivity).setResult(Activity.RESULT_OK, intent)
            context.finish()}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}