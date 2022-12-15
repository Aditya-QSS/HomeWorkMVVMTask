package com.imaginato.homeworkmvvm.ui.login.ViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaginato.homeworkmvvm.data.local.login.User
import com.imaginato.homeworkmvvm.repository.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel @ViewModelInject constructor(private val userRepo: UserRepo): ViewModel() {

    /**
     * Insert user details
     */

    //insert user details to room database
    fun insertUserDetails(user: User){
        userRepo.createUserRecords(user)
    }

}