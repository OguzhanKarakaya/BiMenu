package com.example.bimenu2.fragment.homepage

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bimenu2.R
import com.example.bimenu2.adapter.HomePageDetailAdapter
import com.example.bimenu2.databinding.CustomMenuItemDetailBinding
import com.example.bimenu2.databinding.FragmentHomePageDetailBinding
import com.example.bimenu2.models.MenuSubOptionModel
import com.example.bimenu2.models.MenuSubOptionModelList
import com.example.bimenu2.LDSProgressDialog.show

class HomePageDetailFragment : Fragment(), HomePageDetailAdapter.OnMenuSubOptionClickListener {

    private lateinit var binding: FragmentHomePageDetailBinding
    private lateinit var detailBinding: CustomMenuItemDetailBinding
    private var menuSubOptionModelList = MenuSubOptionModelList()
    private var toolbarTitle: String? = ""
    private var progressDialog: Dialog? = null
    private val args: HomePageDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageDetailBinding.inflate(inflater, container, false)

        menuSubOptionModelList = args.menuSubOptionModelList
        toolbarTitle = args.toolbarTitle
startProgress(requireContext())
        binding.txtToolbarTitle.text = toolbarTitle
        Handler().postDelayed({
            fillRecycler()
        }, 2000)

        return binding.root
    }

    private fun fillRecycler() {
        val adapter = HomePageDetailAdapter(menuSubOptionModelList, this)
        binding.recyclerDetail.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerDetail.adapter = adapter
        stopProgress()
    }

    private fun showOrderDetail(menuSubOptionModel: MenuSubOptionModel) {
        detailBinding = CustomMenuItemDetailBinding.inflate(this.layoutInflater)
        detailBinding.model = menuSubOptionModel
        AlertDialog.Builder(requireContext(), R.style.PastOrderDialog).apply {
            setView(detailBinding.root)
            create()
            show()
        }
    }

    override fun onDetailSubOptionClick(model: MenuSubOptionModel) {
        showOrderDetail(model)
    }

    fun startProgress(context: Context) {
        if (progressDialog == null)
            progressDialog = show(context, null)
        progressDialog?.show()
    }

    fun stopProgress() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }
}