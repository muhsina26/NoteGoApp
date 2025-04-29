package com.example.notego.Database

import androidx.lifecycle.LiveData
import com.example.notego.Models.Note

class NotesRepository(private val noteDao: NoteDao) {

    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note.id, note.title, note.note, note.priority)
    }


    //Sorting methods
    fun getNotesSortedByTitleAsc():LiveData<List<Note>>{
        return noteDao.getNotesSortedByTitleAsc()
    }

    fun getNotesSortedByTitleDesc():LiveData<List<Note>>{
        return noteDao.getNotesSortedByTitleDesc()
    }

    fun getNotesSortedByDateAsc():LiveData<List<Note>>{
        return noteDao.getNotesSortedByDateAsc()
    }

    fun getNotesSortedByDateDesc():LiveData<List<Note>>{
        return noteDao.getNotesSortedByDateDesc()
    }



}


