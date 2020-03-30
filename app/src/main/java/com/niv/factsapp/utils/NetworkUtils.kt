package com.niv.factsapp.utils

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.niv.factsapp.R

/**
 * NetworkUtils
 *
 * @author Nivedith
 * @since 2020-03-30.
 */

object NetworkUtils {

    /**
     * Method to check the internet connectivity
     *
     * @param context current context
     * @return boolean to determine the connectivity
     */
    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    /**
     * Method to show snack bar for internet connection failure on api call
     *
     * @param context current context
     * @param view view in which the layout resides
     */
    fun showErrorSnackBarOnNetworkFailure(context: Context, view: View) {

        val snackbar = Snackbar.make(view, context.getText(R.string.msg_no_internet),
            Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.RED)
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.textSize = 16F
        snackbar.show()
    }
}