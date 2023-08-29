package com.example.cleaving.LoginRegistrasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cleaving.R
import com.example.cleaving.databinding.ActivityRegistrasiBinding

class RegistrasiActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegistrasiBinding
//    private database: Databa
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}