package com.imaginato.homeworkmvvm.data.local.login

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.junit.Ignore

@Ignore
@Entity(tableName = "users_table")
data class User(

    var userId: String,
    var username: String,
    var isDeleted: Boolean,

){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}