package com.example.onboard.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onboard.models.NoteAppModel

@androidx.room.Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll():LiveData<List<NoteAppModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteModel: NoteAppModel)

    @Delete
    fun delete(model: NoteAppModel)
}