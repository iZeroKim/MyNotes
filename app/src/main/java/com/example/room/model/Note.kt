package com.example.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(
    @PrimaryKey(autoGenerate = true)
    private val id: Int,
    @ColumnInfo(name = "note_title")
    private val title: String,
    @ColumnInfo(name = "note_description")
    private val description: String,
    @ColumnInfo(name = "note_priority")
    private val priority: Int) {

}