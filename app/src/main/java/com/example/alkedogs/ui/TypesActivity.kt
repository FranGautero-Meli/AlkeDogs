package com.example.alkedogs.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import com.example.alkedogs.R
import com.example.alkedogs.databinding.ActivityTypesBinding
import com.example.alkedogs.ui.adapter.TypesAdapter


class TypesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTypesBinding
    private lateinit var listTypes: List<String>
    private var numberOfParticipants:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Here the parameter number of participants sent by the main activity is taken
        numberOfParticipants = intent.getIntExtra(getString(R.string.number_of_participants), 0)

        listTypes = getCategory()

        //listener to Click in the RecyclerView
        fun appClickListener(position: Int) {
            getDataToResult(position)
        }

        binding.typesRecyclerview.adapter = TypesAdapter(listTypes, clickListener = {
            appClickListener(it)
        })

        //Here we remove the toolbar only for this activity
        window.setDecorFitsSystemWindows(false)
        if (window.insetsController != null) {

            window.insetsController!!.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            window.insetsController!!.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }


        setBackButtonListener()
        setUpRandomButton()
    }

    //Function that set the action of back button
    private fun setBackButtonListener() {
        binding.icBackTypes.setOnClickListener { onBackPressed() }
    }

    //Function that set te action of random button
    private fun setUpRandomButton() {
        binding.icRandom.setOnClickListener {
            navigateToResultActivity("", numberOfParticipants)
        }
    }

    //Function that takes the position of the item that was clicked and call the navigate function
    // with this parameter and numberOfParticipants parameter too
    private fun getDataToResult(position: Int) {
        val textTypeCategory = listTypes[position]
        navigateToResultActivity(textTypeCategory, numberOfParticipants)
    }


    private fun navigateToResultActivity(typeCategory: String?, numberOfParticipants: Int?) {
        val navigateToResultActivity = Intent(this, ResultActivity::class.java).apply {
            putExtra(getString(R.string.type_category), typeCategory?.lowercase())
            putExtra(getString(R.string.number_of_participants), numberOfParticipants)
        }
        startActivity(navigateToResultActivity)
    }


    private fun getCategory(): List<String> {
        return listOf(
            "Education",
            "Recreational",
            "Social",
            "Diy",
            "Charity",
            "Cooking",
            "Relaxation",
            "Music",
            "Busywork"
        )
    }
}
