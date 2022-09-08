package com.example.alkedogs.ui


import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
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

        //Here we remove the toolbar only for this activity
        window.setDecorFitsSystemWindows(false)
        if (window.insetsController != null) {

            window.insetsController!!.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            window.insetsController!!.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        setBackButtonListener()
        callService()
    }

    //Function that set the action of back button
    private fun setBackButtonListener() {
        binding.icArrowBackResultActivity.setOnClickListener { onBackPressed() }
    }

    //Function that makes the api call
    private fun callService() {

        //Here we take the category parameters and participants sent by the other activities
        typeCategory = intent.extras?.getString("typeCategory") ?: ""
        val participants = intent.extras?.getInt("numberOfParticipants") ?: 0

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val notBoredApi =
                    RetrofitHelper.getInstance().create(NotBoredApiService::class.java)
                val result = notBoredApi.getActivity(participants, typeCategory)
                val resultBody = result.body()
                //If the error field is null (means that result is OK) we setUp view with data
                //Else, we show an error message
                runOnUiThread {
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
                }
            } catch (e: Exception) {
                runOnUiThread { setUpError() }

            }
        }
    }

    //We setUp an error:
    //We hide the part of the UI that shows the data and show the error message
    private fun setUpError() {
        binding.appBarResultActivity.title = "Activity Not Found"
        binding.groupContent.visibility = View.GONE
        binding.groupCategory.visibility = View.GONE
        binding.errorMessageResult.visibility = View.VISIBLE
    }

    private fun setUpViews(game: Activities) {
        //we check if the category was specified or not
        if (typeCategory.isNotBlank()) {
            //If the category was specified the title is the category
            binding.appBarResultActivity.title =
                game.category.replaceFirstChar { char -> char.uppercase() }
        } else {
            //If the category wasn't specified the title is Random and we modified the UI to show the category part
            binding.appBarResultActivity.title = getString(R.string.random_title)
            binding.textViewSubtitleCategory.text =
                game.category.replaceFirstChar { char -> char.uppercase() }
            binding.groupCategory.visibility = View.VISIBLE
        }

        binding.textViewTitleResult.text = game.title.replaceFirstChar { char -> char.uppercase() }
        binding.textViewParticipantsNumber.text = game.participants.toString()

        //Conditional to define the price range according to the received float
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