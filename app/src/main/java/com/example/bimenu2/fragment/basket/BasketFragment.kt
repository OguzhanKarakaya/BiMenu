package com.example.bimenu2.fragment.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bimenu2.adapter.BasketAdapter
import com.example.bimenu2.databinding.FragmentBasketBinding
import com.example.bimenu2.fragment.homepage.HomePageFragment
import com.example.bimenu2.models.BasketModel

class BasketFragment: Fragment() {

    private lateinit var binding: FragmentBasketBinding
    private var basketList = ArrayList<BasketModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(inflater, container, false)

        basketList = HomePageFragment.basketList

        val adapter = BasketAdapter(basketList)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        return binding.root
    }

}