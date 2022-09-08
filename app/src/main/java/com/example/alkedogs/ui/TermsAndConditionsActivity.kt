package com.example.alkedogs.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import com.example.alkedogs.databinding.ActivityTermsAndConditionsBinding

class TermsAndConditionsActivity : AppCompatActivity() {

    lateinit var binding: ActivityTermsAndConditionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsAndConditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Here we remove the toolbar only for this activity
        window.setDecorFitsSystemWindows(false)
        if (window.insetsController != null) {

            window.insetsController!!.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            window.insetsController!!.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        setCloseButtonListener()
    }

    private fun setCloseButtonListener() {
        binding.buttonCloseTYC.setOnClickListener { onBackPressed() }
    }
}