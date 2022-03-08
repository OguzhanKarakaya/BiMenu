package com.example.bimenu2.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.bimenu2.Constants
import com.example.bimenu2.R
import com.example.bimenu2.activities.HomePageActivity
import com.example.bimenu2.databinding.FragmentLoginBinding
import com.example.bimenu2.models.LoginUserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private var loginUserModel: LoginUserModel? = LoginUserModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.btnRegister.setOnClickListener {
            onRegisterClicked()
        }

        binding.btnLogin.setOnClickListener {
            onLoginClicked()
        }

        return binding.root
    }

    private fun onLoginClicked() {
        val reference = FirebaseDatabase.getInstance().reference
        reference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                loginUserModel = snapshot.child(Constants.CUSTOMER_DATABASE)
                    .child(binding.etPhoneNumber.text.toString())
                    .getValue(LoginUserModel::class.java)

                if (loginUserModel?.email?.isNotBlank() == true) {
                    val intent = Intent(requireContext(), HomePageActivity::class.java)
                    startActivity(intent)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("TAG", "onCancelled: ")
            }

        })
    }

    private fun onRegisterClicked() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, RegisterFragment())
            .commit()
    }
}