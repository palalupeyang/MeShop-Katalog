package com.example.meshop


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()


        val emailEditText: TextInputEditText = findViewById(R.id.emaiil)
        val passwordEditText: TextInputEditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.signin)
        val SignupButton: Button = findViewById(R.id.signup)


        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Tolong Isikan Semua", Toast.LENGTH_SHORT).show()
            }
        }
        SignupButton.setOnClickListener {
            // Navigasi ke halaman SignUp (RegisterActivity)
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                    // kode untuk navigasi ke halaman setelah login berhasil
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    intent.putExtra("showHomeFragment", true)
                    finish()
                } else {
                    Toast.makeText(this, "Gagal Memuat", Toast.LENGTH_SHORT).show()
                }
            }
    }
}