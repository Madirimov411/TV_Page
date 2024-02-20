package com.uzb_khiva.videoplayerappinxml.CustomPlayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uzb_khiva.videoplayerappinxml.R
import com.uzb_khiva.videoplayerappinxml.databinding.ActivityMyCustomPlayerBinding

class MyCustomPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyCustomPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyCustomPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnWatch.setOnClickListener {
                val intent = Intent(this@MyCustomPlayerActivity, WatchActivity::class.java)
                startActivity(intent)
            }
        }

    }
}