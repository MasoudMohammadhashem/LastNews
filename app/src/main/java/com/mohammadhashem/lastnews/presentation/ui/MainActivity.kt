package com.mohammadhashem.lastnews.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.mohammadhashem.lastnews.R
import com.mohammadhashem.lastnews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fc_main) as NavHostFragment
    }
}