package com.example.onboard.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteAppModel(
    val title: String,
    val description: String,
    val data: String,
    val color: String
    ): java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
