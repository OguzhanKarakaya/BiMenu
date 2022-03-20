package com.example.bimenu2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bimenu2.databinding.ItemHomePageDetailBinding
import com.example.bimenu2.models.MenuSubOptionModel
import com.example.bimenu2.models.MenuSubOptionModelList

class HomePageDetailAdapter(
    private val menuSubOptionModelList: MenuSubOptionModelList,
    private val onDetailClick: OnMenuSubOptionClickListener
) :
    RecyclerView.Adapter<HomePageDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemBinding =
            ItemHomePageDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuSubOptionModelList[position])
        holder.itemView.setOnClickListener {
            onDetailClick.onDetailSubOptionClick(menuSubOptionModelList[position])
        }
    }

    override fun getItemCount(): Int {
        return menuSubOptionModelList.size
    }

    class ViewHolder(private val binding: ItemHomePageDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: MenuSubOptionModel) {
            binding.model = model
        }
    }

    interface OnMenuSubOptionClickListener {
        fun onDetailSubOptionClick(model: MenuSubOptionModel)
    }
}