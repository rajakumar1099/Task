package com.app.zohotask.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.app.zohotask.databinding.ItemListBinding
import com.app.zohotask.model.DataModel

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {
    var data = mutableListOf<DataModel>()
    fun setDataList(data: List<DataModel>){
        this.data = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = data[position]
        holder.binding.authorName.text = data.author.toString()
        holder.binding.username.text = data.name.toString()
        holder.binding.description.text = data.description.toString()
        holder.binding.languageColor.setBackgroundColor(Color.parseColor(data.languageColor.toString()))
        holder.binding.languageName.text = data.language.toString()
        holder.binding.starCount.text = data.stars.toString()
        Glide.with(holder.itemView.context).load(data.avatar).into(holder.binding.avatar)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class MainViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

}
