package com.example.practicetwo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.practicetwo.data.Note
import com.example.practicetwo.data.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * A ViewModel that manages notes data via the repository.
 * It exposes a Flow of all notes as a StateFlow for the Compose UI.
 */
class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    /**
     * Convert the Flow<List<Note>> from the repository into a StateFlow,
     * so we can collectAsState() in Compose. 'stateIn' gives us an initialValue
     * and a defined "lifecycle" for sharing.
     */
    val notes: StateFlow<List<Note>> = repository.getAllNotes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    /**
     * Called when the user wants to add a new note.
     * We launch a coroutine on the ViewModel's scope
     * and call 'insertNote' on the repository.
     */
    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            val note = Note(title = title, content = content)

            // 1) Insert into local DB
            repository.insertNote(note)
        }
    }


}

/**
 * A factory class that knows how to create a NoteViewModel
 * with the required repository argument.
 */
class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Make sure we only create a NoteViewModel
        // for the correct modelClass.
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}