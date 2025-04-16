package com.example.practicetwo.appwidgets

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.example.practicetwo.DataPackage.Helpers.generateNumericalStrings
import kotlin.random.Random
import androidx.glance.unit.ColorProvider


class IMDbGlanceWidget : GlanceAppWidget() {
    companion object {
        val MOVIE_ID = ActionParameters.Key<String>("movie_id")
    }
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            MyContent(context)
        }
    }
}

@Composable
fun MyContent(context: Context) {
    val randID = generateNumericalStrings(Random.nextInt(1, 1999999), 7)
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Random Movie",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = ColorProvider(Color.White)
            )
        )
        Button(
            text = "Open Movie",
            onClick = actionRunCallback<OpenCustomTabAction>(
                parameters = actionParametersOf(IMDbGlanceWidget.MOVIE_ID to randID)
            ),
            modifier = GlanceModifier.padding(8.dp)
        )
    }
}

class OpenCustomTabAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: androidx.glance.GlanceId,
        parameters: androidx.glance.action.ActionParameters
    ) {
        val movieId = parameters[IMDbGlanceWidget.MOVIE_ID] ?: ""
        val intent = Intent(context, CustomTabActivity::class.java).apply {
            putExtra("movie_id", movieId)
        }
        context.startActivity(intent)
    }
}