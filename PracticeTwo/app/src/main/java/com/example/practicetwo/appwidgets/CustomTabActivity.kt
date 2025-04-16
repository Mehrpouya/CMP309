package com.example.practicetwo.appwidgets

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.example.practicetwo.R

class CustomTabActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = intent.getStringExtra("movie_id") ?: ""
        openCustomTab(this, "https://www.imdb.com/title/tt$movieId/")
        finish()
    }
    fun openCustomTab(context: Context, url: String) {
        if (url.isNotEmpty()) {
            try {
                val builder = CustomTabsIntent.Builder()
                val colorSchemeParams = CustomTabColorSchemeParams.Builder()
                    // You can use your own colour set to match your app.
                    .setToolbarColor(ContextCompat.getColor(context, R.color.teal_200))
                    .build()
                builder.setDefaultColorSchemeParams(colorSchemeParams)
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(context, Uri.parse(url))
            } catch (e: Exception) {
                Log.e("CustomTabs", "Error launching custom tab", e)
                // Handle the error, e.g., show an error message to the user
            }
        } else {
            Log.e("CustomTabs", "URL is empty")
            // Handle the case where the URL is empty
        }
    }
}