package com.example.kamrul_78_locationsharingapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kamrul_78_locationsharingapp.databinding.FragmentProfile20Binding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment20 : Fragment() {

    private lateinit var binding: FragmentProfile20Binding
    private lateinit var authViewModel: AuthenticationViewModel20
    private lateinit var firestoreViewModel: FireStoreViewModel20
    private lateinit var locationViewModel: LocationViewModel20
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfile20Binding.inflate(inflater, container, false)

        authViewModel = ViewModelProvider(this).get(AuthenticationViewModel20::class.java)
        firestoreViewModel = ViewModelProvider(this).get(FireStoreViewModel20::class.java)
        locationViewModel = ViewModelProvider(this).get(LocationViewModel20::class.java)


        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(requireContext(), LoginActivity20::class.java))

        }

        binding.homeBtn.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        loadUserInfo()
        binding.updateBtn.setOnClickListener {
            val newName = binding.NameEt.text.toString()
            val newLocation = binding.locationEt.text.toString()

            updateBtn(newName, newLocation)
        }

        return binding.root
    }

    private fun updateBtn(newName: String, newLocation: String) {
        val currentUser = authViewModel.getCurrentUser()
        if (currentUser != null) {
            val userId = currentUser.uid
            firestoreViewModel.updateUser(requireContext(), userId, newName, newLocation)
            Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), MainActivity::class.java))
        } else {
            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadUserInfo() {
        val currentUser = authViewModel.getCurrentUser()
        if(currentUser != null) {
            binding.emailEt.setText(currentUser.email)
            firestoreViewModel.getUser(requireContext(), currentUser.uid){ currentuser ->
                if (currentUser.displayName != null) {
                    binding.NameEt.setText(currentUser.displayName)
                }
            }
        }else {
            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
        }

    }
}