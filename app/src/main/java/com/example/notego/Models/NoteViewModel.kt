package com.example.notego.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notego.Database.NoteDatabase
import com.example.notego.Database.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepository
    val allnotes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NotesRepository(dao)
        allnotes = repository.allNotes
    }

    fun deleteNote(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)

    }
    fun insertNote(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun updateNote(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)

    }
    //sorting Methods

    //get sorted notes by title
    fun getSortedNotesByTitleAsc():LiveData<List<Note>>{
        return  repository.getNotesSortedByTitleAsc()
    }
    //Get sorted notes by title DESC

    fun getSortedNotesByTitleDesc():LiveData<List<Note>>{
        return  repository.getNotesSortedByTitleDesc()
    }

    //Get sorted notes by Date(ASC)

    fun  getSortedNotesByDateAsc():LiveData<List<Note>>{
        return repository.getNotesSortedByDateAsc()
    }

    fun getSortedNotesByDateDesc():LiveData<List<Note>>{
        return  repository.getNotesSortedByDateDesc()
    }


}