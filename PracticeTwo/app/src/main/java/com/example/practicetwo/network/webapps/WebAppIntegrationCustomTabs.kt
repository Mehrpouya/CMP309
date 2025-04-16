package com.example.practicetwo

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import com.example.practicetwo.DataPackage.Helpers.generateNumericalStrings
import com.example.practicetwo.ui.theme.PracticeTwoTheme
import kotlin.random.Random

class WebAppIntegrationCustomTabs : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTwoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold { innerPadding ->
                        RandomMovieCustom(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
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

@Composable
fun RandomMovieCustom(name: String, modifier: Modifier = Modifier) {
    val randID = generateNumericalStrings(Random.nextInt(1, 1999999), 7)
    val context = LocalContext.current
    Column(modifier = modifier.padding(16.dp)) {
        Button(onClick = { openCustomTab(context, "https://www.imdb.com/title/tt$randID/") }) {
            Text("Open in Custom Tab")
        }
    }
}


