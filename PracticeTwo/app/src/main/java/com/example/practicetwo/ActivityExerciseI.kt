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
                Exercise Difficulty - Easy:
                Add a shape parameter to the surface above and make the surface to have rounded corners!

                TODO: do the next 2 exercise here at the end if you have time

                Exercise Difficulty - Medium:
                Add onclick listener to the surface and change the background colour per click.
                SadTheme is a theme I made for this exercise. See if you can make your own?
                tip: Start by looking at Theme.kt file in this project.

                Exercise Difficulty - Hard
                Write a function that generates list of alphanumeric strings instead of the four names below
                */

                    StartMyApp(listOf("Hadi", "Alessandro", "Luke", "Don", "Ruth")) // Best to have a simple composable function  here instead of any UI code.
                }
            }
        }
    }
}

@Composable
fun StartMyApp(names: List<String> = listOf("Hadi", "Alessandro", "Luke")) {
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
        UserManagement(names = names)
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
        This is a way to reuse code and components we develop.
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
               * "Continue" button and text are on top of each other.
               *  Workout which layout element you can use to position elements in a column
               * Tip: This guide is useful in helping you understand layout element you need to fix this issue
               * https://www.youtube.com/watch?v=xc8nAcVvpxY
               * https://developer.android.com/develop/ui/compose/layouts/basics

               * User onboarding is made of osnly one screen atm.
               * Can you add 2 or 3 more steps/screens to it.
               * Each with some text, maybe an image if you feel like it and a "next button"
               * tip:
               * - You need to define an onboarding stepCounter similar to our isOnBoarding bool
               * - You need to use remember and mutablestateof.
               * - Each different onboarding screen can be a composable function.
       * */
}

@Composable
fun UserItem(name: String = "Hadi") {
    val context = LocalContext.current // We need context for making toasts and launching activities.
    val emailed = remember {mutableStateOf(false)}
/* Notice
                         _   _       _   _
                        | \ | | ___ | |_(_) ___ ___
                        |  \| |/ _ \| __| |/ __/ _ \
                        | |\  | (_) | |_| | (_|  __/
                        |_| \_|\___/ \__|_|\___\___|

 Replace showImage line to:
val showImage by remember { mutableStateOf(false) }
Notice how you need to remove .value when using "by" delegate.
*/
    val showImage = remember { mutableStateOf(false) }
Surface(
    color = MaterialTheme.colorScheme.primary,
    modifier = Modifier.padding(10.dp)
) {
    /* Notice:
                 _   _       _   _
                | \ | | ___ | |_(_) ___ ___
                |  \| |/ _ \| __| |/ __/ _ \
                | |\  | (_) | |_| | (_|  __/
                |_| \_|\___/ \__|_|\___\___|

    See how we can include additional modifiers to customise UI elements.
    */
   /* Exercise
            ____                 _   _           _   _  _             _____                   _
           |  _ \ _ __ __ _  ___| |_(_) ___ __ _| | | || |           | ____|_  _____ _ __ ___(_)___  ___
           | |_) | '__/ _` |/ __| __| |/ __/ _` | | | || |_   _____  |  _| \ \/ / _ \ '__/ __| / __|/ _ \
           |  __/| | | (_| | (__| |_| | (_| (_| | | |__   _| |_____| | |___ >  <  __/ | | (__| \__ \  __/
           |_|   |_|  \__,_|\___|\__|_|\___\__,_|_|    |_|           |_____/_/\_\___|_|  \___|_|___/\___|

           Exercise - Easy Difficulty:
        - Go ahead and uncomment fillMaxWidth.
        - Change the 0.8f value to 1 and values between 0-1 and notice the change.

           Exercise Difficulty medium:
        1. Can you try and add an image to each list item?
        Tip:
            This is how you can show an image
                Image(painter = painterResource(id=R.drawable.zombie_head),
                contentDescription = R.string.ProfileImage.toString())
        2. Animate the image appearing and disappearing?
        * Here's a guide on how to do it
        * https://developer.android.com/develop/ui/compose/animation/composables-modifiers
        *
                    * */
    Row() {
        Column(modifier = Modifier.padding(10.dp)/*.fillMaxWidth(0.8f)*/) {
            Text("User, ")
            //Add another text component to show user name
            //Add a placeholder image that becomes visible when user clicks the button
            // if showImage is true...
            /*Image(painter = painterResource(id=R.drawable.zombie_head),
                contentDescription = R.string.ProfileImage.toString())*/
        }
        Column(modifier = Modifier.padding(10.dp)/*.fillMaxWidth(0.8f)*/) {//change padding and add other modifiers
            /* Exercise
            *
            *
             ____                 _   _           _   _  _             _____                   _
            |  _ \ _ __ __ _  ___| |_(_) ___ __ _| | | || |           | ____|_  _____ _ __ ___(_)___  ___
            | |_) | '__/ _` |/ __| __| |/ __/ _` | | | || |_   _____  |  _| \ \/ / _ \ '__/ __| / __|/ _ \
            |  __/| | | (_| | (__| |_| | (_| (_| | | |__   _| |_____| | |___ >  <  __/ | | (__| \__ \  __/
            |_|   |_|  \__,_|\___|\__|_|\___\__,_|_|    |_|           |_____/_/\_\___|_|  \___|_|___/\___|

              * Exercise - Medium Difficulty:
            * Try using OutlinedButton instead of Button
            * */
            Button (
                onClick = {
                    //Notice for creating a toast, you need a variable called context.
                    Toast.makeText(context, "Welcome email sent to $name", Toast.LENGTH_SHORT)
                        .show()
                    emailed.value = !emailed.value
                    showImage.value = !showImage.value


                })
            {
                //Change this text based on the status of emailed variable.
                // If emailed is true, button text should show, already welcomed.
                //else show Welcome them.

                Text("Welcome them")
            }
        }
    }
}
}

@Composable
fun AnotherUI() {
/*
Take a look here and make a few changes to your components using modifiers. We are not currently calling this composable.
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