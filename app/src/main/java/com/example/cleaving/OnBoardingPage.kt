package com.example.cleaving

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.cleaving.LoginRegistrasi.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class OnBoardingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_page)

        val buttonWt1 = findViewById<Button>(R.id.ButtonOnBoarding)
        buttonWt1.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    var firebashAuth = FirebaseAuth.getInstance()
    override fun onStart() {
        super.onStart()
        if (firebashAuth.currentUser != null) {
            startActivity(Intent(this, HomeFragment::class.java))
        }
    }
}