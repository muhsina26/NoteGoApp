package com.example.notego.Database

import android.icu.text.CaseMap.Title
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notego.Models.Note


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)// jodi amon hoi j note jeta insert korte jassi sei note already thakae tahole oita replace hoye jabe
    suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note:Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes():LiveData<List<Note>>

    @Query("UPDATE notes_table SET title = :title, note = :note, priority = :priority WHERE id = :id")
    suspend fun update(id: Int?, title: String?, note: String?, priority: kotlin.String)


    //sorting by Title
    @Query("SELECT * FROM notes_table ORDER BY title ASC")
    fun getNotesSortedByTitleAsc(): LiveData<List<Note>>

    @Query("SELECT * FROM notes_table ORDER BY title DESC")
    fun getNotesSortedByTitleDesc():LiveData<List<Note>>

    //sorting by date

    @Query("SELECT *FROM notes_table ORDER BY id ASC")
    fun getNotesSortedByDateAsc():LiveData<List<Note>>

    @Query("SELECT *FROM notes_table ORDER BY id DESC")
    fun getNotesSortedByDateDesc():LiveData<List<Note>>




}