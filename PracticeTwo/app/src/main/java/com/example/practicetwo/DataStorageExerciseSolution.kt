//package com.example.practicetwo
//
//import com.example.practicetwo.data.NoteRepository
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.lifecycle.ViewModelProvider
//import com.example.practicetwo.data.NoteDatabase
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Initialize database and repository
//        val database = NoteDatabase.getDatabase(this)
//        val repository = NoteRepository(database.noteDao())
//
//        // Create ViewModel
//        val viewModelFactory = NoteViewModelFactory(repository)
//        val noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]
//
//        setContent {
//            // Pass the viewModel to your UI
//            NoteApp(noteViewModel)
//        }
//    }
//}
//
//@Composable
//fun NoteApp(noteViewModel: NoteViewModel) {
//    val notes by noteViewModel.notes.collectAsState()
//
//    var title by remember { mutableStateOf("") }
//    var content by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier.fillMaxSize().padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        OutlinedTextField(
//            value = title,
//            onValueChange = { title = it },
//            label = { Text("Title") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        OutlinedTextField(
//            value = content,
//            onValueChange = { content = it },
//            label = { Text("Content") },
//            modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp)
//        )
//
//        Button(onClick = {
//            noteViewModel.addNote(title, content)
//            title = ""
//            content = ""
//        }) {
//            Text("Add Note")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text("All Notes:")
//        notes.forEach { note ->
//            Text("• ${note.title}: ${note.content}")
//        }
//    }
//}
//
