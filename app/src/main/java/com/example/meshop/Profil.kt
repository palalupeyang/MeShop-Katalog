package com.example.meshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Profil : AppCompatActivity() {
    private lateinit var namaTextView: EditText
    private lateinit var emailTextView: EditText
    private lateinit var logoutButton: Button
    private lateinit var imgProfil: ImageView
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        namaTextView = findViewById(R.id.edtNama)
        emailTextView = findViewById(R.id.edtEmail)
        logoutButton = findViewById(R.id.btnLogout)

        // Tampilkan nama pengguna dengan huruf besar diawal kata
        currentUser?.let {
            val namaPengguna = formatName(currentUser.displayName)
            namaTextView.setText(namaPengguna)
        }

        // Display the user's email
        currentUser?.let {
            val email = currentUser.email
            emailTextView.setText(email)
        }


        // Handle logout button click
        logoutButton.setOnClickListener {
            // Sign out the user and navigate back to the login screen
            auth.signOut()
            val intent = Intent(this, SignIn::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun formatName(name: String?): String {
        if (name.isNullOrBlank()) return ""
        val words = name.split(" ").map { it.toLowerCase().capitalize() }
        return words.joinToString(" ")
    }

}



