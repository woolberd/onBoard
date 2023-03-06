package com.example.onboard.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteAppModel(
    val title: String? = null,
    val description: String? = null ,
    val data: String? = null,
    val color: String? = null,
    ): java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
