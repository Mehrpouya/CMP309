package com.example.practicetwo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.practicetwo.ui.theme.SadTheme
/*
 _______ .-. .-. _______  .---.  ,---.  ,-.  .--.  ,-.
|__   __|| | | ||__   __|/ .-. ) | .-.\ |(| / /\ \ | |
  )| |   | | | |  )| |   | | |(_)| `-'/ (_)/ /__\ \| |
 (_) |   | | | | (_) |   | | | | |   (  | ||  __  || |
   | |   | `-')|   | |   \ `-' / | |\ \ | || |  |)|| `--.
   `-'   `---(_)   `-'    )---'  |_| \)\`-'|_|  (_)|( __.'
                         (_)         (__)          (_)
* In addition to exercises and details provided here within each Kotlin code, the following codelab is useful to get you started with some basics of compose
* https://www.youtube.com/watch?v=k3jvNqj4m08&t=147s
* */
class PipelinePrintActivity : ComponentActivity() {

    var clickCounter: Int = 0

//    var clickCounter

    //perform basic application startup logic.
    // happens only once for the entire life of the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SadTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    RandomButton(
                        name = "Click me!",
                        modifier = Modifier
                    )
                }
            }
        }
    }


@Composable
fun RandomButton(name: String, modifier: Modifier = Modifier) {
    val toast = Toast.makeText(
        applicationContext,
        "button created in $name",
        Toast.LENGTH_LONG).show()
    var buttonText by remember { mutableStateOf("Click Me") }
    var button1ClickCounter by remember { mutableStateOf( 0) }
    var button2ClickCounter by remember { mutableStateOf( 0) }
    //A layout composable that places its children in a vertical sequence. For a layout composable that places its children in a horizontal sequence, see Row
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = {
                    button1ClickCounter++; // Try minimising your app and see if this value resets.
                    Toast.makeText(applicationContext, "clicked again  $button2ClickCounter", Toast.LENGTH_SHORT).show()
                    buttonText = "Clicked for the $button1ClickCounter times"
                    /*
                    *    _
                        | |    ___  __ _ _ __ _ __
                        | |   / _ \/ _` | '__| '_ \
                        | |__|  __/ (_| | |  | | | |
                        |_____\___|\__,_|_|  |_| |_|
                    We use intents to start new activities.
                    * Create a new activity and use the code below to run your activity from here using onclick listener.
                    * Once you've tested how intent work, bring back the intent to run ActivityExerciseI::class.java again so you can continue with this practical.
                    * val intent = Intent(this@PipelinePrintActivity, ActivityExerciseI::class.java).apply {
                        intent.putExtra("media_id", "a1b2c3")
                    * */
                    val intent = Intent(this@PipelinePrintActivity, ActivityExerciseISolutions::class.java).apply {
                        intent.putExtra("media_id", "a1b2c3") // This is how you can send some data to a new activity.
                        // ...
                    }
                    startActivity(intent)
                }
                )
            {
                Text(text = buttonText, fontSize = 24.sp)
            }
            /*
                 ____                 _   _           _   _  _             _____                   _
                |  _ \ _ __ __ _  ___| |_(_) ___ __ _| | | || |           | ____|_  _____ _ __ ___(_)___  ___
                | |_) | '__/ _` |/ __| __| |/ __/ _` | | | || |_   _____  |  _| \ \/ / _ \ '__/ __| / __|/ _ \
                |  __/| | | (_| | (__| |_| | (_| (_| | | |__   _| |_____| | |___ >  <  __/ | | (__| \__ \  __/
                |_|   |_|  \__,_|\___|\__|_|\___\__,_|_|    |_|           |_____/_/\_\___|_|  \___|_|___/\___|

                Exercise 1:
                Change this button to show multiplies of 2 each time user clicks it.
                */
            Button(
                onClick = {
                    /*
                    *        _   _       _   _
                            | \ | | ___ | |_(_) ___ ___
                            |  \| |/ _ \| __| |/ __/ _ \
                            | |\  | (_) | |_| | (_|  __/
                            |_| \_|\___/ \__|_|\___\___|

                    // Try minimising your app and see if clickCounter looses its value. We defined it as normal variable and not a mutable state.
                    * */
                    clickCounter++;
                    button2ClickCounter++;
                    Toast.makeText(applicationContext, "clickCounter is $clickCounter", Toast.LENGTH_SHORT).show()
                },
                )
            {
                //Change button2ClickCounter into regular click counter and see how your button click counter is not being updated in UI!
                //You'll notice how clickCounter is not getting updated.
                //This is because we are not storing it using a mutable state of and remember compose function.
                //Which means, as soon as a value changes, it tells the UI to refresh.
                //Also notice if you change value of button1ClickCounter or buttontext in this onclick, it shouldn't update this UI element.
                Text(text = "clicked: $button2ClickCounter", fontSize = 24.sp)
            }
        }


    }
    /*
// Invoked when the activity might be temporarily destroyed; save the instance state here.
    override fun onSaveInstanceState(outState: Bundle) {
//        outState?.run {
//            putString(GAME_STATE_KEY, gameState)
//            putString(TEXT_VIEW_KEY, textView.text.toString())
//        }
        // Call superclass to save any view hierarchy.
        super.onSaveInstanceState(outState)
    }
override fun onRestart() {
    super.onRestart()
    val toast = Toast.makeText(applicationContext, "onRestart Called", Toast.LENGTH_LONG).show()
}

override fun onPause() {
    super.onPause()
    val toast = Toast.makeText(applicationContext, "onPause Called", Toast.LENGTH_LONG).show()
}

override fun onResume() {
    super.onResume()
    val toast = Toast.makeText(applicationContext, "onResume Called", Toast.LENGTH_LONG).show()
}

override fun onStop() {
    super.onStop()
    val toast = Toast.makeText(applicationContext, "onStop Called", Toast.LENGTH_LONG).show()
}

override fun onDestroy() {
    super.onDestroy()
    val toast = Toast.makeText(applicationContext, "onDestroy Called", Toast.LENGTH_LONG).show()
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    PracticeTwoTheme {
        Greeting2("Android")
    }
}*/
}