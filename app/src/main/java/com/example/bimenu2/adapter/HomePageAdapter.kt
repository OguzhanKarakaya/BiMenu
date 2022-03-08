package com.example.bimenu2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bimenu2.databinding.ItemHomePageBinding
import com.example.bimenu2.models.MenuOptionModel

class HomePageAdapter(
    private val menuList: ArrayList<MenuOptionModel>,
    private val onItemClickListener: OnHomePageItemClickListener
) :
    RecyclerView.Adapter<HomePageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHomePageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuList[position])
        holder.itemView.setOnClickListener {
            onItemClickListener.onHomePageItemClicked(menuList[position])
        }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    class ViewHolder(private val binding: ItemHomePageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: MenuOptionModel) {
            binding.model = model
        }
    }

    interface OnHomePageItemClickListener {
        fun onHomePageItemClicked(menuOptionModel: MenuOptionModel)
    }
}