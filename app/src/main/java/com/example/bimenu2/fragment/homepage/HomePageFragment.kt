package com.example.bimenu2.fragment.homepage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bimenu2.Constants
import com.example.bimenu2.R
import com.example.bimenu2.adapter.HomePageAdapter
import com.example.bimenu2.databinding.FragmentHomePageBinding
import com.example.bimenu2.models.BasketModel
import com.example.bimenu2.models.MenuOptionModel
import com.example.bimenu2.models.MenuSubOptionModel
import com.example.bimenu2.models.MenuSubOptionModelList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.lang.reflect.Type
import kotlin.collections.set


class HomePageFragment : Fragment(),
    HomePageAdapter.OnHomePageItemClickListener {

    private lateinit var binding: FragmentHomePageBinding

    private var hashMap = HashMap<String, ArrayList<MenuSubOptionModel>>()
    private val menuOptionModelList = ArrayList<MenuOptionModel>()
    private var isFirstRun: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        getDBObjects()

        return binding.root
    }

    private fun fillRecycler() {
        isFirstRun = true
        val adapter = HomePageAdapter(menuOptionModelList, this)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
    }


    override fun onHomePageItemClicked(menuOptionModel: MenuOptionModel) {
        val test = hashMap[menuOptionModel.optionName]
        val lastMenuList = MenuSubOptionModelList()
        if (test != null) {
            for (i in test) {
                lastMenuList.add(i)
            }
        }
        val action =
            HomePageFragmentDirections.actionHomePageFragment2ToHomePageDetailFragment(
                lastMenuList,
                menuOptionModel.optionName ?: ""
            )
        findNavController().navigate(action)
    }

    private fun getDBObjects() {
        try {
            if (!isFirstRun) {
                val obj = JSONObject(loadJSONFromAsset())
                val menuJSONObj =
                    obj.getJSONObject(Constants.MENU_DATABASE).getJSONObject("Hangover12345")

                val basketJSONArray =
                    obj.getJSONObject(Constants.BASKET_DATABASE).getJSONArray("BASKET")

                for (menuItems in 0 until menuJSONObj.length()) {
                    val menuList = ArrayList<MenuSubOptionModel>()
                    val menuItemKey = menuJSONObj.names().get(menuItems)
                    val jsonArrayMenuItem = menuJSONObj.get(menuItemKey.toString())
                    val gson = Gson()
                    val listType: Type =
                        object : TypeToken<ArrayList<MenuSubOptionModel?>?>() {}.type
                    val posts: ArrayList<MenuSubOptionModel> =
                        gson.fromJson(jsonArrayMenuItem.toString(), listType)
                    menuList.addAll(posts)
                    hashMap[menuItemKey.toString()] = menuList
                }
                hashMap.keys.forEach {
                    val menuOptionModel = MenuOptionModel()
                    menuOptionModel.optionName = it
                    menuOptionModelList.add(menuOptionModel)
                }

                for (basketItems in 0 until basketJSONArray.length()) {
                    val gson = Gson()
                    val type : Type = object : TypeToken<MenuSubOptionModel?>() {}.type
                    val menuSOM: MenuSubOptionModel = gson.fromJson(basketJSONArray[basketItems].toString(), type)
                    val basketModel = BasketModel()
                    basketModel.menuSubOption = menuSOM
                    basketList.add(basketModel)
                }

                Log.i("TAG", "getDBObjects: ")
            }
            fillRecycler()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun loadJSONFromAsset(): String? {
        var json: String? = null
        json = try {
            val inputStream = requireActivity().assets.open("menu_db.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    companion object {
        val basketList = ArrayList<BasketModel>()
    }
}