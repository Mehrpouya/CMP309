import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Scaffold( modifier = modifier,
        topBar = {
            TopAppBar(title = { Text("Local Storage Demo") })
        }
    ) {
        innerPadding ->
        DataButtons(modifier = Modifier.padding(innerPadding))

    }
}

@Composable
fun DataButtons(modifier:Modifier = Modifier) {
    val context = LocalContext.current
    var data by remember { mutableStateOf("No data loaded") }
    val fileName = "data.txt"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Button to load data from internal storage
        Button(
            onClick = {
                try {
                    context.openFileInput(fileName).bufferedReader().use { reader ->
                        data = reader.readText()
                    }
                } catch (e: Exception) {
                    data = "Error reading data: ${e.message}"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Load Data")
        }

        // Button to write data to internal storage
        Button(
            onClick = {
                try {
                    context.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
                        val textToSave = "Hello, this is saved data"
                        output.write(textToSave.toByteArray())
                    }
                    data = "Data written successfully!"
                } catch (e: Exception) {
                    data = "Error writing data: ${e.message}"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Write Data")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = data)
    }
}

@Preview
@Composable
fun StoragePreview()
{
    MyApp()
}
