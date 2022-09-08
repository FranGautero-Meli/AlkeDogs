package com.example.alkedogs.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alkedogs.R
import com.example.alkedogs.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Here we remove the toolbar only for this activity
        window.setDecorFitsSystemWindows(false)
        if (window.insetsController != null) {

            window.insetsController!!.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            window.insetsController!!.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        setUpListeners()
    }

    private fun setUpListeners() {
        setUpStartButton()
        setUpTermsAndConditionsButton()
    }

    private fun setUpTermsAndConditionsButton() {
        binding.textViewTermsAndConditions.setOnClickListener { navigateToTermsAndConditions() }
    }

    private fun navigateToTermsAndConditions() {
        val navigateToTermsAndConditions = Intent(this, TermsAndConditionsActivity::class.java)
        startActivity(navigateToTermsAndConditions)
    }

    private fun setUpStartButton() {
        binding.extendedFloatingActionButtonMain.setOnClickListener {
            navigateToTypesActivity()
        }
    }

    private fun navigateToTypesActivity() {
        val navigateToTypesActivities = Intent(this, TypesActivity::class.java)
        val numberOfParticipants = binding.tvParticipants.text.toString()
        if (numberOfParticipants == "" || numberOfParticipants.toInt() !in 11 downTo 0) {
            Toast.makeText(this, "Please, insert a number between 1 to 10", Toast.LENGTH_LONG)
                .show()
        } else {
            navigateToTypesActivities.putExtra(
                getString(R.string.number_of_participants),
                numberOfParticipants.toInt()
            )
            startActivity(navigateToTypesActivities)
        }


    }
}