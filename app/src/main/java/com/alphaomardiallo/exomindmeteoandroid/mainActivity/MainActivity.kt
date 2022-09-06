package com.alphaomardiallo.exomindmeteoandroid.mainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alphaomardiallo.exomindmeteoandroid.R
import com.alphaomardiallo.exomindmeteoandroid.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolBar()
    }

    private fun setupToolBar() {
        setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(supportActionBar)!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
    }
}