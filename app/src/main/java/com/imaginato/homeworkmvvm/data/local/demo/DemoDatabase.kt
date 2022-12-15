package com.imaginato.homeworkmvvm.data.local.demo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Demo::class], version = 1, exportSchema = false)
abstract class DemoDatabase : RoomDatabase() {
    abstract val demoDao: DemoDao
}