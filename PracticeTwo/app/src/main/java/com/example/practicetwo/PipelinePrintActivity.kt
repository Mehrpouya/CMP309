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

There are several exercises for you to complete. These are in this file and ActivityExerciseI.kt files.

 _______ .-. .-. _______  .---.  ,---.  ,-.  .--.  ,-.
|__   __|| | | ||__   __|/ .-. ) | .-.\ |(| / /\ \ | |
  )| |   | | | |  )| |   | | |(_)| `-'/ (_)/ /__\ \| |
 (_) |   | | | | (_) |   | | | | |   (  | ||  __  || |
   | |   | `-')|   | |   \ `-' / | |\ \ | || |  |)|| `--.
   `-'   `---(_)   `-'    )---'  |_| \)\`-'|_|  (_)|( __.'
                         (_)         (__)          (_)
* In addition to exercises and details provided here within each Kotlin code, the following codelab is useful to get you started with some basics of compose
if you struggle in following exercises, feel free to follow the codelab below before engaging with practical exercises.
* https://www.youtube.com/watch?v=k3jvNqj4m08&t=147s
* */
class PipelinePrintActivity : ComponentActivity() {
    var clickCounter: Int = 0
    /* onCreate is called when the activity is first created.
    - performs basic application startup logic.
    - happens only once for the entire life of the activity
    When writing the onCreate function, usually set up the following:
    - Declaring the user interface,
    - Defining member variables
    - Configuring some of the UI.
*/
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
    /*
   _   _       _   _
  | \ | | ___ | |_(_) ___ ___
  |  \| |/ _ \| __| |/ __/ _ \
  | |\  | (_) | |_| | (_|  __/
  |_| \_|\___/ \__|_|\___\___|

  // Try minimising your app
  * moving to previous activity by swiping right on emulator or your phone and notice how different callbacks are being called depending on your activity state.
  * */
    override fun onPause() {
        super.onPause()
        val toast = Toast.makeText(applicationContext,"Lifecycle onPause called", Toast.LENGTH_LONG).show()
        // Pause ongoing tasks, such as animations or sensitive data changes
    }

    override fun onResume() {
        super.onResume()
        val toast = Toast.makeText(applicationContext,"Lifecycle onResume called", Toast.LENGTH_LONG).show()
        // Pause ongoing tasks, such as animations or sensitive data changes
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
                    * Run PipelinePrintActivity and see the final solution. Once you know how things roughly should look, go ahead and change intent into
                    * Once you've tested how intents work, bring back the intent to run ActivityExerciseI::class.java like below comment so you can develop the solutions.
                    * val intent = Intent(this@PipelinePrintActivity, ActivityExerciseI::class.java).apply {
                        intent.putExtra("media_id", "a1b2c3")
                    * */
                    val intent = Intent(
                        this@PipelinePrintActivity,
                        ActivityExerciseISolutions::class.java).apply{
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

                Exercise 1 - difficulty easy :
                Change this button to show multiplies of 2 each time user clicks it.
                i.e. clicked 2 -> for each click value * 2 -> show button text as clicked 4 and so on
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
}