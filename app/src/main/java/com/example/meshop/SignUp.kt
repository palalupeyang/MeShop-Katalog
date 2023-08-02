package com.example.meshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class SignUp : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        val namaEditText: TextInputEditText = findViewById(R.id.namaLengkap)
        val emailEditText: TextInputEditText = findViewById(R.id.emaiil)
        val passwordEditText: TextInputEditText = findViewById(R.id.password)
        val passwordConfirmEditText: TextInputEditText = findViewById(R.id.passconfir)
        val registerButton: Button = findViewById(R.id.signup)

        registerButton.setOnClickListener {
            val nama = namaEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val passconfir = passwordConfirmEditText.text.toString()

            if (nama.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                if (password == passconfir) {
                    registerUser(nama, email, password)
                } else {
                    Toast.makeText(this, "Password and Konfirmasi Password Tidak Sama", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tolong Isi Semua", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(nama: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.updateProfile(UserProfileChangeRequest.Builder()
                        .setDisplayName(nama)
                        .build())
                        ?.addOnCompleteListener { profileUpdateTask ->
                            if (profileUpdateTask.isSuccessful) {
                                Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                                // kode untuk navigasi ke halaman setelah registrasi berhasil
                                val intent = Intent(this, SignIn::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Gagal mengupdate profile", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Registrasi Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
