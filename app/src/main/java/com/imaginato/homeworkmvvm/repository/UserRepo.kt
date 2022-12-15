package com.imaginato.homeworkmvvm.repository

import androidx.room.Query
import androidx.room.Transaction
import com.imaginato.homeworkmvvm.data.local.login.User
import com.imaginato.homeworkmvvm.data.local.login.UserDao
import org.koin.core.component.KoinApiExtension
import java.util.concurrent.Flow
import javax.inject.Inject

@KoinApiExtension
class UserRepo @Inject constructor(private val userDao: UserDao) {

    //insert user details to room
    fun createUserRecords(user: User) {
        return userDao.insert(user)
    }


}