package com.example.onboard.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onboard.data.db.daos.NoteDao
import com.example.onboard.models.NoteAppModel

@Database(entities = [NoteAppModel::class], version = 3)

abstract class AppDataBase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}