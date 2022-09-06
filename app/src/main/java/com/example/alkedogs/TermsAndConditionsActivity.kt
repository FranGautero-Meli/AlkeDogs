package com.example.alkedogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alkedogs.databinding.ActivityTermsAndConditionsBinding

class TermsAndConditionsActivity : AppCompatActivity() {

    lateinit var binding: ActivityTermsAndConditionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsAndConditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCloseButtonListener()
    }

    private fun setCloseButtonListener() {
        binding.buttonCloseTYC.setOnClickListener { onBackPressed() }
    }
}