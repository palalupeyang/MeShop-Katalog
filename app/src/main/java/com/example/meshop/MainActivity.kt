package com.example.meshop

import HomeFragment
import SearchActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // ======== Buat Setting Ke Fragment ======
                    val homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_main, homeFragment)
                        .commit()
                    true
                }

                R.id.nav_explore -> {
                    // ======== Buat Setting Ke Fragment ======
                    val searchFragment = SearchActivity()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_main, searchFragment)
                        .commit()
                    true
                }

                // ======== Buat Setting Ke Activity ======

                R.id.nav_account -> {
                    val intent = Intent(this, Profil::class.java)
                    startActivity(intent)
                    true

                }

                else -> false
            }
        }
    }
}