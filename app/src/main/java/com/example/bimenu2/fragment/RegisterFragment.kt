package com.example.bimenu2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.bimenu2.Constants
import com.example.bimenu2.R
import com.example.bimenu2.databinding.FragmentRegisterBinding
import com.example.bimenu2.models.LoginUserModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val auth = Firebase.auth
    private val userModel = LoginUserModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        binding.userModel = userModel

        binding.btnCompleteRegister.setOnClickListener {
            onCompleteClicked()
        }

        return binding.root
    }

    private fun onCompleteClicked() {
        auth.createUserWithEmailAndPassword(
            binding.etUserMail.text.toString(),
            binding.etUserPassword.text.toString()
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                val reference = FirebaseDatabase.getInstance().reference
                binding.userModel?.phoneNumber = binding.etUserPhoneNumber.text.toString()
                reference.child(Constants.CUSTOMER_DATABASE).child(binding.etUserPhoneNumber.text.toString()).setValue(binding.userModel)
            }
        }
    }

}