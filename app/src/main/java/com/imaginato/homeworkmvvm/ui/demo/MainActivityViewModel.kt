package com.imaginato.homeworkmvvm.ui.demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imaginato.homeworkmvvm.data.remote.demo.DemoRepository
import com.imaginato.homeworkmvvm.ui.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.inject

@KoinApiExtension
class MainActivityViewModel : BaseViewModel() {
    private val repository: DemoRepository by inject()
    private var _resultLiveData: MutableLiveData<String> = MutableLiveData()
    private var _progress: MutableLiveData<Boolean> = MutableLiveData()
    val progress: LiveData<Boolean>
        get() {
            return _progress
        }

    val resultLiveData: LiveData<String>
        get() {
            return _resultLiveData
        }
    /**
     * Do something and handle business logic here
     * */
    fun getDemoData() {
        viewModelScope.launch {
            repository.getDemoData()
                .onStart {
                    _progress.value = true
                }.catch {
                    _progress.value = false
                }
                .onCompletion {
                }.collect {
                    _progress.value = false
                    _resultLiveData.value = it
                }
        }
    }
}