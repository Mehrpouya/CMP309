package com.example.practicetwo.data
import kotlinx.coroutines.flow.Flow
/**
 * A repository class abstracts access to multiple data sources.
 * Here we only have Room, but you could combine local + network, etc.
 */

class NoteRepository(private val noteDao: NoteDao) {

    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }
}