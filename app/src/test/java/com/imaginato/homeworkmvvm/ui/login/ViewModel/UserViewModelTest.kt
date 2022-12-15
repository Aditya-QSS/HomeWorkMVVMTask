package com.imaginato.homeworkmvvm.ui.login.ViewModel

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.imaginato.homeworkmvvm.data.local.login.User
import com.imaginato.homeworkmvvm.data.local.login.UserDao
import com.imaginato.homeworkmvvm.data.local.login.UserDatabase
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserViewModelTest : TestCase(){

    private lateinit var db: UserDatabase
    private lateinit var dao: UserDao

    @BeforeEach
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, UserDatabase::class.java).build()
        dao = db.userDao()
    }

    @Test
     fun insertUserDetails() = runBlocking {
        val user = User(userId = "123", username = "Aditya", isDeleted = true)
        dao.insert(user)
        val allUsers = dao.getUsers()
        assertThat(allUsers.contains(user)).isTrue()
    }

}