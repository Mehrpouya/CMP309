package com.example.practicetwo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practicetwo.DataPackage.Movie
import com.example.practicetwo.DataPackage.MovieViewModel
import com.example.practicetwo.ui.theme.PracticeTwoTheme
import com.example.practicetwo.ui.theme.SadTheme
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream
import java.io.InputStream

class MovieActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SadTheme {
                // Main screen content
                MovieMainScreen()
            }
        }
    }
}

//region Main Screen
@Composable
fun MovieMainScreen(movieViewModel: MovieViewModel = viewModel()) {


    Column {
        AddMovieScreen(movieViewModel)
        MovieListScreen(movieViewModel)
    }
}
//endregion

//region Add Movie Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMovieScreen(movieViewModel: MovieViewModel) {
    var title by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    Column {
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        TextField(value = director, onValueChange = { director = it }, label = { Text("Director") })
        TextField(value = year, onValueChange = { year = it }, label = { Text("Year") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { launcher.launch("image/*") }) {
            Text("Select Image")
        }

        if (imageUri != null) {
            val inputStream: InputStream? = imageUri?.let { context.contentResolver.openInputStream(it) }
            val bitmap = BitmapFactory.decodeStream(inputStream)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Selected Image",
                modifier = Modifier.size(100.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val image: ByteArray? = imageUri?.let {
                val inputStream: InputStream? = context.contentResolver.openInputStream(it)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                val stream =
                    ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
                stream.toByteArray()
            }
            val movie = Movie(title = title, director = director, year = year.toInt(), moviePoster = image)
            movieViewModel.insertMovie(movie)
            title = ""
            director = ""
            year = ""
            imageUri = null
        }) {
            Text("Add Movie")
        }
    }
}
//endregion

//region Movie List Screen
@Composable
fun MovieListScreen(movieViewModel: MovieViewModel) {
    val allMovies by movieViewModel.allMovies.collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Movie List")
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(allMovies) { movie ->
                MovieItem(movie = movie)
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Title: ${movie.title}")
        Text(text = "Director: ${movie.director}")
        Text(text = "Year: ${movie.year}")
        if (movie.moviePoster != null) {
            val bitmap = BitmapFactory.decodeByteArray(movie.moviePoster, 0, movie.moviePoster.size)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Movie Poster",
                modifier = Modifier.size(100.dp)
            )
        }
    }
}