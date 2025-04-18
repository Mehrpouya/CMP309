package com.example.practicetwo

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.practicetwo.DataPackage.Helpers.generateNumericalStrings
import com.example.practicetwo.ui.theme.PracticeTwoTheme
import kotlin.random.Random

class WebAppIntegration : ComponentActivity() {
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
                        RandomMovie(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun MovieScreen(url: String, modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        },
        modifier = modifier
    )
}

@Composable
fun RandomMovie(name: String, modifier: Modifier = Modifier) {
    val randID = generateNumericalStrings(Random.nextInt(1, 1999999), 7)
    val randURL = "https://www.imdb.com/title/tt$randID"
    Log.d("RandomMovie", "Generated URL: $randURL")
    Column(modifier = modifier.padding(16.dp)) {
        MovieScreen(
            url = randURL,
            modifier = Modifier.fillMaxSize()
        )
    }
}

