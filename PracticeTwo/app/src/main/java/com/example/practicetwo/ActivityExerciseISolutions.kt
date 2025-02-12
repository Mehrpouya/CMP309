package com.example.practicetwo
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
class ActivityExerciseISolutions : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()//Uncomment me to see the effects
        //Enables the edge-to-edge display for this ComponentActivity
        setContent {

            SadTheme {
            Surface(shape = RoundedCornerShape(10.dp)) {
                    StartApp(listOf("Hadi", "Alessandro", "Luke", "Don", "Ruth")) // Best to have a simple composable function  here instead of any UI code.
                }
            }
        }
    }
}
//renamed this composable so Kotlin compiler won't get confused with the other StartMyApp composable in ActivityExerciseI.kt
@Composable
fun StartApp(names: List<String> = listOf("Hadi", "Alessandro", "Luke")) {

    var isOnBoarding by remember { mutableStateOf(true) }

    if (isOnBoarding) {
        OnBoardingScreenSolution(onBoardingContinueClicked = { isOnBoarding = false })
    } else {
        UserManagementSolution(names = names)
    }

}


@Composable
fun UserManagementSolution(modifier: Modifier = Modifier, names: List<String>?) {
    Surface(
        // color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(20.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            for (name in names ?: emptyList()) {
                UserItemSolution(name)
            }
        }
    }
}


@Composable
fun OnBoardingScreenSolution(modifier: Modifier = Modifier,onBoardingContinueClicked: () -> Unit) {
    //You can simply add a column container to make sure your elements won't be positioned on top of each other.
    Column {
    Greeting2("CMP309 students", modifier)
    Button(onClick = onBoardingContinueClicked) {
        Text("Continue")
    }
    }
}

@Composable
fun UserItemSolution(name: String = "Hadi") {
    val context = LocalContext.current
    val emailed = remember {mutableStateOf(false)}
    val showImage = remember { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(10.dp)
    ) {

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
                    })
                {
                    Text(if(emailed.value) "Already welcomed!" else "Welcome them")
                }
            }
        }
    }
}