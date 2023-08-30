package com.example.cleaving.LoginRegistrasi

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.example.cleaving.HomeActivity
import com.example.cleaving.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    var firebaseAuth = FirebaseAuth.getInstance()

    private lateinit var progressDialog: ProgressDialog

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser!=null){
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Logging")
        progressDialog.setMessage("Mohon tunggu")

        binding.ButtonLogin.setOnClickListener{
            if (binding.email.text.isNotEmpty() && binding.password.text.isNotEmpty()){
                prosesLogin()
            }else{
                Toast.makeText( this, "Silahkan isi email dan password terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

        binding.ButtonDaftar.setOnClickListener {
            startActivity(Intent(this, RegistrasiActivity::class.java))
        }

//        binding.lupapw.setOnClickListener {
//            val builder = AlertDialog.Builder(this)
//            val view = layoutInflater.inflate(R.layout.forgot_password,null)
//            val userEmail = view.findViewById<EditText>(R.id.email_lupapw)
//            builder.setView(view)
//            val dialog = builder.create()
//
//            view.findViewById<Button>(R.id.reset_lupapw).setOnClickListener {
//                cekEmail(userEmail)
//                dialog.dismiss()
//            }
//            view.findViewById<Button>(R.id.cancel_lupapw).setOnClickListener {
//                dialog.dismiss()
//            }
//            if (dialog.window != null){
//                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
//            }
//            dialog.show()
//        }
    }

    private fun cekEmail(Email:EditText){
        if (Email.text.toString().isEmpty()){
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email.text.toString()).matches()){
            return
        }
        firebaseAuth.sendPasswordResetEmail(Email.text.toString()).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Cek Email Anda", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun prosesLogin(){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, HomeActivity::class.java))
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener {
                progressDialog.dismiss()
            }
    }
}