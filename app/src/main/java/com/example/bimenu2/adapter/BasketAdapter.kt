package com.example.bimenu2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bimenu2.databinding.ItemBasketBinding
import com.example.bimenu2.models.BasketModel

class BasketAdapter(private val basketList: ArrayList<BasketModel>) :
    RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapter.ViewHolder {
        val itemBinding =
            ItemBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BasketAdapter.ViewHolder, position: Int) {
        holder.bind(basketList[position])
    }

    override fun getItemCount(): Int {
        return basketList.size
    }

    class ViewHolder(private val binding: ItemBasketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: BasketModel) {
            binding.model = model
        }
    }
}