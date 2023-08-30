package com.example.cleaving.LoginRegistrasi

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cleaving.HomeActivity
import com.example.cleaving.databinding.ActivityRegistrasiBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.userProfileChangeRequest

class RegistrasiActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegistrasiBinding
    var firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference
    private lateinit var progressDialog: ProgressDialog

    private fun initComponent() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Logging")
        progressDialog.setMessage("Mohon tunggu")
        database = FirebaseDatabase.getInstance().getReference("Users")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponent()

        binding.ButtonDaftar.setOnClickListener  {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.ButtonLogin.setOnClickListener {
            if (binding.username.text.isNotEmpty()&&binding.email.text.isNotEmpty()&&binding.password.text.isNotEmpty()&&binding.passwordconfirm.text.isNotEmpty()){
                if (binding.password.text.toString().trim() == binding.passwordconfirm.text.toString().trim()){
                    if (binding.password.text.toString().trim().length < 8 ||binding.passwordconfirm.text.toString().trim().length < 8){
                        Toast.makeText( this, "Password kurang dari 8 karakter", Toast.LENGTH_SHORT).show()
                    }else{
//                        database = Firebase.database.reference
                        val user = User(binding.username.text.toString(),binding.email.text.toString())
                        database.child(binding.username.text.toString()).setValue(user)
                        prosesRegister()
                    }
                }else{
                    Toast.makeText( this, "Password yang terisi berbeda", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText( this, "Data belum lengkap terisi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser!=null){
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private  fun prosesRegister(){
        val fullname = binding.username.text.toString()
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val userUpdateProfile = userProfileChangeRequest {
                        displayName = fullname
                    }
                    val user = task.result.user
                    user!!.updateProfile(userUpdateProfile)
                        .addOnSuccessListener {
                            progressDialog.dismiss()
                            startActivity(Intent(this, RegistrasiActivity::class.java))
                        }
                        .addOnFailureListener{ error2 ->
                            Toast.makeText(this, error2.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                }else{
                    progressDialog.dismiss()
                }
            }
            .addOnFailureListener{ error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
    }
}