package com.example.alkedogs

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.alkedogs.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBackButtonListener()
        callService()
    }

    private fun setBackButtonListener() {
        binding.icArrowBackResultActivity.setOnClickListener { onBackPressed() }
    }

    private fun callService() {
        //si el servicio da ok
        setUpViews(Activities("educational", 1, "leer un libro", 0.1F))
        //si no trae resultados
        setUpError()
    }

    private fun setUpError() {
        binding.groupContent.visibility = View.GONE
        binding.groupCategory.visibility = View.GONE
        binding.errorMessageResult.visibility = View.VISIBLE
    }

    data class Activities(
        val category: String,
        val participants: Int,
        val title: String,
        val price: Float
    )

    private fun setUpViews(game: Activities) {
        //chequear si la categoria se envió o no en el servicio para definir estos dos textos:

        //Titulo de la activity, si se mando categoría es la categoría enviada, si no es RANDOM
        binding.appBarResultActivity.title = game.category
        //visibilidad o no del renglón en donde se muestra la categoría (SÓLO si no envió en el servicio, es decir, cuando es random)
        binding.textViewSubtitleCategory.text = game.category
        binding.groupCategory.visibility = View.VISIBLE




        binding.textViewTitleResult.text = game.title

        val price = game.price.let {
            when {
                it == 0F -> "Free"
                it > 0F && it <= 0.3F -> "Low"
                it > 0.3F && it <= 0.6F -> "Medium"
                it > 0.6F -> "High"
                else -> "Unknown"
            }
        }
        binding.textViewPriceLevel.text = price


        binding.btnTryAnother.setOnClickListener {
            TODO("llamar al servicio nuevamente")
        }


    }
}