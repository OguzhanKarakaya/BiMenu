package com.example.bimenu2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bimenu2.R
import com.example.bimenu2.databinding.ActivityHomePageBinding
import com.example.bimenu2.fragment.homepage.homepage.HomePageFragment
import com.example.bimenu2.fragment.homepage.OrderFragment
import com.example.bimenu2.fragment.homepage.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomePageFragment())
        binding.bottomNavigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.navigationHomePage -> fragment = HomePageFragment()
                R.id.navigationOrders -> fragment = OrderFragment()
                R.id.navigationProfile -> fragment = ProfileFragment()
            }
            loadFragment(fragment)
        }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.containerHomePage, fragment)
                .commit()
            return true
        }
        return false
    }
}