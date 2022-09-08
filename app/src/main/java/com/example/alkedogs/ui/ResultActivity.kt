package com.example.alkedogs.ui


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.alkedogs.R
import com.example.alkedogs.data.network.NotBoredApiService
import com.example.alkedogs.data.network.RetrofitHelper
import com.example.alkedogs.databinding.ActivityResultBinding
import com.example.alkedogs.ui.model.Activities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding
    private var typeCategory = ""


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
        val notBoredApi = RetrofitHelper.getInstance().create(NotBoredApiService::class.java)

        typeCategory = intent.extras?.getString("typeCategory") ?: ""
        val participants = intent.extras?.getInt("numberOfParticipants") ?: 0

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = notBoredApi.getActivity(participants, typeCategory)
                Log.d("Service", result.toString())
                val resultBody = result.body()
                if (resultBody?.error == null) {
                    setUpViews(
                        Activities(
                            resultBody?.type ?: "",
                            resultBody?.participants ?: 1,
                            resultBody?.activity ?: "",
                            resultBody?.price ?: 1F
                        )
                    )
                } else setUpError()
            } catch (e: Exception) {
                setUpError()
            }
        }

    }

    private fun setUpError() {
        binding.appBarResultActivity.title = "Activity Not Found"
        binding.groupContent.visibility = View.GONE
        binding.groupCategory.visibility = View.GONE
        binding.errorMessageResult.visibility = View.VISIBLE
    }

    private fun setUpViews(game: Activities) {
        //chequear si la categoria se envió o no en el servicio para definir estos dos textos:
        if (typeCategory.isNotBlank()) {
            //Titulo de la activity, si se mando categoría es la categoría enviada, si no es RANDOM
            binding.appBarResultActivity.title = game.category
        } else {
            //visibilidad o no del renglón en donde se muestra la categoría (SÓLO si no envió en el servicio, es decir, cuando es random)
            binding.appBarResultActivity.title = getString(R.string.random_title)
            binding.textViewSubtitleCategory.text = game.category
            binding.groupCategory.visibility = View.VISIBLE
        }

        binding.textViewTitleResult.text = game.title
        binding.textViewParticipantsNumber.text = game.participants.toString()

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
            callService()
        }


    }
}