package com.example.cleaving

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cleaving.databinding.ActivityRegistrasiBinding
import com.example.cleaving.databinding.BottomSheetPickupBinding
import com.example.cleaving.databinding.FragmentRecycleBinding
import com.example.cleaving.databinding.FragmentSummaryOrderBinding
import com.example.cleaving.databinding.LayoutSampahBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SummaryOrderFragment:Fragment(){

    private lateinit var _binding:FragmentSummaryOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummaryOrderBinding.inflate(inflater,container,false)
        var mFirebaseDatabaseInstances: FirebaseDatabase?=null
        var mFirebaseDatabase: DatabaseReference
        mFirebaseDatabaseInstances= FirebaseDatabase.getInstance()
        mFirebaseDatabase=mFirebaseDatabaseInstances!!.getReference("Users")
        val user=FirebaseAuth.getInstance().currentUser
        if (user != null) {
            _binding.pickupschedule.text = user.email
        }
        return _binding.root
    }
}