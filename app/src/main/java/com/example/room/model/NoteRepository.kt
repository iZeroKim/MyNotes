package com.example.room.model

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NoteRepository(private val noteDao: NoteDao) {
    // Observed LiveData will notify the observer when the data has changed.
    private val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun update(note: Note){
        noteDao.update(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }

    suspend  fun getAllNotes(): LiveData<List<Note>>{
        return allNotes
    }
}