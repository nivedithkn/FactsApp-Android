package com.niv.factsapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.niv.factsapp.R

class MainActivity : AppCompatActivity() {

    val SPLASH_TIMEOUT = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startNextScreen(SPLASH_TIMEOUT)
    }

    /**
     * Method to navigate to next screen
     *
     * @param splashTimeout splash time out
     */
    private fun startNextScreen(splashTimeout: Long) {
        Handler().postDelayed({

            startActivity(Intent(this@MainActivity, FactsListingActivity::class.java))
            finish()

        }, splashTimeout)
    }
}
