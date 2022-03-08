package com.example.bimenu2.fragment.homepage.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bimenu2.R
import com.example.bimenu2.adapter.HomePageAdapter
import com.example.bimenu2.databinding.FragmentHomePageBinding
import com.example.bimenu2.models.MenuOptionModel

class HomePageFragment : Fragment(),
    HomePageAdapter.OnHomePageItemClickListener {

    private lateinit var binding: FragmentHomePageBinding
    private var menuList = ArrayList<MenuOptionModel>()

    private var menuItem = MenuOptionModel()
    private var menuItem2 = MenuOptionModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        menuItem.optionName = "Hamburgerler"
        menuItem2.optionName = "Sıcak İçecekler"

        menuList.add(menuItem)
        menuList.add(menuItem2)

        fillRecycler()

        return binding.root
    }

    private fun fillRecycler() {
        val adapter = HomePageAdapter(menuList, this)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
    }


    override fun onHomePageItemClicked(menuOptionModel: MenuOptionModel) {
        loadFragment(HomePageDetailFragment())
    }

    private fun loadFragment(fragment: Fragment?) {
        if (fragment != null) {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.containerHomePage, fragment)
                .commit()
        }
    }
}