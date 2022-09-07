package com.example.alkedogs

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alkedogs.adapter.TypesAdapter
import com.example.alkedogs.databinding.ActivityTypesBinding
import com.example.alkedogs.util.OnItemClickListenerType


class TypesActivity : AppCompatActivity(), OnItemClickListenerType {

    private lateinit var binding: ActivityTypesBinding
    private lateinit var typesAdapter: TypesAdapter
    private lateinit var listTypes: List<String>
    private var numberOfParticipants:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        numberOfParticipants = intent.getIntExtra(getString(R.string.number_of_participants),0)
        listTypes = getCategory()

        typesAdapter = TypesAdapter(listTypes)
        binding.typesRecyclerview.adapter = typesAdapter

        setUpRandomButton()
    }

    override fun onItemClick(position: Int) {

        getDataToResult(position)

    }

    private fun setUpRandomButton() {
        binding.icRandom.setOnClickListener {
            //Implementar logica ramdom
        }
    }

    private fun getDataToResult(position : Int) {
        val textCategory = listTypes[position]
        val extras = Bundle()
        extras.putString("category", textCategory)
        val intentCategory = Intent(this, ResultActivity::class.java)
        intentCategory.putExtras(extras)
        startActivity(intentCategory)
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
            "busywork"
        )
    }
}
