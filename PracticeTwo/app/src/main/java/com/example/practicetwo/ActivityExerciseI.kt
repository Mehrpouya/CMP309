package com.example.practicetwo
/*
                             _   _       _   _
                            | \ | | ___ | |_(_) ___ ___
                            |  \| |/ _ \| __| |/ __/ _ \
                            | |\  | (_) | |_| | (_|  __/
                            |_| \_|\___/ \__|_|\___\___|

 how many things are being imported for such small activity!
 * Think about how modular the whole android architecture is. reusability if at its heart!
 * How could you reuse different components and elements you implement?
* */
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicetwo.ui.theme.PracticeTwoTheme
import com.example.practicetwo.ui.theme.SadTheme
/*
 ____  _____    _    ____
|  _ \| ____|  / \  |  _ \
| |_) |  _|   / _ \ | | | |
|  _ <| |___ / ___ \| |_| |
|_| \_\_____/_/   \_\____/

* Please pay attention to comments throughout the codes.
* They are there to help you understand the code and do some exercises.
* */
class ActivityExerciseI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()//Uncomment me to see the effects
        //Enables the edge-to-edge display for this ComponentActivity
        setContent {
            // Only your top layer composable need a theme.
            // rest will inherit and your can make changes using modifiers
            SadTheme {
            Surface() {
                /*
                 ____                 _   _           _   _  _             _____                   _
                |  _ \ _ __ __ _  ___| |_(_) ___ __ _| | | || |           | ____|_  _____ _ __ ___(_)___  ___
                | |_) | '__/ _` |/ __| __| |/ __/ _` | | | || |_   _____  |  _| \ \/ / _ \ '__/ __| / __|/ _ \
                |  __/| | | (_| | (__| |_| | (_| (_| | | |__   _| |_____| | |___ >  <  __/ | | (__| \__ \  __/
                |_|   |_|  \__,_|\___|\__|_|\___\__,_|_|    |_|           |_____/_/\_\___|_|  \___|_|___/\___|

                Exercise Difficulty - Medium:
                SadTheme is a theme I made for this exercise. See if you can make your own?
                tip: Start by looking at Theme.kt file in this project.

                Exercise Difficulty - Hard
                Write a function that generates list of alphanumeric strings instead of the four names below
                */

                    StartMyApp(listOf("Hadi", "Allesandro", "Luke", "Don", "Ruth")) // Best to have a simple composable function  here instead of any UI code.
                }
            }
        }
    }
}

@Composable
fun StartMyApp(names: List<String> = listOf("Hadi", "Allesandro", "Luke")) {
    /*
    *
             _
            | |    ___  __ _ _ __ _ __
            | |   / _ \/ _` | '__| '_ \
            | |__|  __/ (_| | |  | | | |
            |_____\___|\__,_|_|  |_| |_|

            * Ask Gemini what remember and mutableStateOf are.
            * Tune Gemini to explain it to you in a way you like,
            * e.g. ask it to give you a short explanation of what remember and mutablestateof mean?
            * Ask it to explain it to you using metaphors.
            * Remember, Gemini can look at your code (context aware) if you allowed it.
            * it will remember previous chats, so you don't have to repeat questions.
            *
            * Understand the different between using remember without "by". Like next line.
            * val isOnBoardingNoBy = remember { mutableStateOf(true) }
    * */
    var isOnBoarding by remember { mutableStateOf(true) }

    if (isOnBoarding) {
        OnBoardingScreen(onBoardingContinueClicked = { isOnBoarding = false })
    } else {
        UserManagementSolution(names = names)
    }

}


@Composable
fun UserManagement(modifier: Modifier = Modifier, names: List<String>?) {
    Surface(
        // color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(20.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            /*
                 ____                 _   _           _   _  _             _____                   _
                |  _ \ _ __ __ _  ___| |_(_) ___ __ _| | | || |           | ____|_  _____ _ __ ___(_)___  ___
                | |_) | '__/ _` |/ __| __| |/ __/ _` | | | || |_   _____  |  _| \ \/ / _ \ '__/ __| / __|/ _ \
                |  __/| | | (_| | (__| |_| | (_| (_| | | |__   _| |_____| | |___ >  <  __/ | | (__| \__ \  __/
                |_|   |_|  \__,_|\___|\__|_|\___\__,_|_|    |_|           |_____/_/\_\___|_|  \___|_|___/\___|

                Exercise Difficulty - Medium:
                Write a loop that iterates through list of names and creates a UserItem for each name.
                */

        }
    }
}


@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier,onBoardingContinueClicked: () -> Unit) {
    /*
                             _   _       _   _
                            | \ | | ___ | |_(_) ___ ___
                            |  \| |/ _ \| __| |/ __/ _ \
                            | |\  | (_) | |_| | (_|  __/
                            |_| \_|\___/ \__|_|\___\___|

        Notice how I am calling Greeting2 from composeTraining.kt file.
        This is a way to reuse code and components.
* */
    Greeting2("CMP309 students", modifier)
    Button(onClick = onBoardingContinueClicked) {
        Text("Continue")
    }
    /*
       *
       *        ____                 _   _           _   _  _             _____                   _
               |  _ \ _ __ __ _  ___| |_(_) ___ __ _| | | || |           | ____|_  _____ _ __ ___(_)___  ___
               | |_) | '__/ _` |/ __| __| |/ __/ _` | | | || |_   _____  |  _| \ \/ / _ \ '__/ __| / __|/ _ \
               |  __/| | | (_| | (__| |_| | (_| (_| | | |__   _| |_____| | |___ >  <  __/ | | (__| \__ \  __/
               |_|   |_|  \__,_|\___|\__|_|\___\__,_|_|    |_|           |_____/_/\_\___|_|  \___|_|___/\___|

               Exercise - Medium Difficulty:
               * User onboarding is made of only one screen atm.
               * Can you add 2 or 3 more steps/screens to it.
               * Each with some text, maybe an image if you feel like it and a "next button"
               * tip: You need to define an onboarding stepCounter similar to our isOnBoarding bool
               * using remember and mutablestateof.
               * Each different onboarding screen can be a composable function.
       * */
}

@Composable
fun UserItem(name: String = "Hadi") {
    val context = LocalContext.current
    val emailed = remember {mutableStateOf(false)}
    val showImage = remember { mutableStateOf(false) }


//    Toast.makeText(
//        /* context = */ getBaseContext(),
//        /* text = */ "second activity created",
//        /* duration = */ Toast.LENGTH_SHORT
//    ).show()
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(10.dp)
    ) {
        /*
                     _   _       _   _
                    | \ | | ___ | |_(_) ___ ___
                    |  \| |/ _ \| __| |/ __/ _ \
                    | |\  | (_) | |_| | (_|  __/
                    |_| \_|\___/ \__|_|\___\___|

        See how we can include additional modifiers to customise UI elements.

       *        ____                 _   _           _   _  _             _____                   _
               |  _ \ _ __ __ _  ___| |_(_) ___ __ _| | | || |           | ____|_  _____ _ __ ___(_)___  ___
               | |_) | '__/ _` |/ __| __| |/ __/ _` | | | || |_   _____  |  _| \ \/ / _ \ '__/ __| / __|/ _ \
               |  __/| | | (_| | (__| |_| | (_| (_| | | |__   _| |_____| | |___ >  <  __/ | | (__| \__ \  __/
               |_|   |_|  \__,_|\___|\__|_|\___\__,_|_|    |_|           |_____/_/\_\___|_|  \___|_|___/\___|

               Exercise - Medium Difficulty:
            - Go ahead and uncomment fillMaxWidth.
            - Change the 0.8f value to 1 and values between 0-1 and notice the change.

        * */
        Row() {
            Column(modifier = Modifier.padding(10.dp)/*.fillMaxWidth(0.8f)*/) {
                Text("User, ")
                Text(text = name)
                //generated the animation code using Gemini AI
                AnimatedVisibility(
                    visible = showImage.value,
                    enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
                    exit = fadeOut(animationSpec = tween(durationMillis = 500))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.zombie_head),
                        contentDescription = "Zombie Head"
                    )
                }
                /*Image(painter = painterResource(id=R.drawable.zombie_head),
                    contentDescription = R.string.ProfileImage.toString())*/
            }
            Column(modifier = Modifier.padding(10.dp)/*.fillMaxWidth(0.8f)*/) {
                Button (
                    onClick = {
                        //Notice for creating a toast, you need a variable called context.
                        Toast.makeText(context, "Welcome email sent to $name", Toast.LENGTH_SHORT)
                            .show()
                        emailed.value = !emailed.value
                        showImage.value = !showImage.value
                        /*
                         _____                   _
                        | ____|_  _____ _ __ ___(_)___  ___
                        |  _| \ \/ / _ \ '__/ __| / __|/ _ \
                        | |___ >  <  __/ | | (__| \__ \  __/
                        |_____/_/\_\___|_|  \___|_|___/\___|

                        Exercise 2:
                        * Can you try and animate the image appearing and disappearing?
                        * Here's a guide on how to do it
                        * https://developer.android.com/develop/ui/compose/animation/composables-modifiers
                        *
                        * */
//                        find
//                        val intent = Intent(this@PipelinePrintActivity, ActivityExerciseI::class.java).apply {
//                            intent.putExtra("media_id", "a1b2c3")
//                            // ...
//                        }
//                        startActivity(intent)

                    })
                    /*when (result) { //Check out https://kotlinlang.org/docs/control-flow.html#when-expressions-and-statements for conditionals
                    null ->  "click me"
                    else  -> "click $result"
                }*/
                {
                    Text(if(emailed.value) "Already welcomed!" else "Welcome them")
                }
            }
        }
    }
}

@Composable
fun AnotherUI() {
    /*
    Take a look here and make a few changes to your components using modifiers
    * https://developer.android.com/develop/ui/compose/modifiers-list
    * */
    Surface(color = Color.Black) {
        Box() {
            Text(
                "Another UI", modifier = Modifier.padding(24.0.dp)
            )
        }
    }
}

/*

 _____                   _
| ____|_  _____ _ __ ___(_)___  ___
|  _| \ \/ / _ \ '__/ __| / __|/ _ \
| |___ >  <  __/ | | (__| \__ \  __/
|_____/_/\_\___|_|  \___|_|___/\___|

* Use @Preview to see your composables in the IDE.
Use preview parameters to customize the preview.
You can create separate composable functions for previews.
Remember to add the theme to the preview.
Remember to add the innerPadding to the content of the Scaffold.
Go ahead, copy one of the previews
* */
@Preview(
    name = "Square Preview",
    widthDp = 50,
    heightDp = 50,
    showBackground = true,
    backgroundColor = 0xFF00FF00,
    showSystemUi = true,
    group = "MyPreviews"
)
@Composable
fun SquareComposablePreview() {
    UserItem()
}

@Preview(group = "MyPreviews")
@Composable
fun DefaultPrev() {
    StartMyApp()
}

//Not connected to any of our composable functions, just me testing different layout components.
@Preview(showBackground = true)
@Composable
fun ScaffoldPreview() {
    PracticeTwoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                UserItem()
            }
            Box(modifier = Modifier.padding(innerPadding)) {
                AnotherUI()
            }
        }
    }
}