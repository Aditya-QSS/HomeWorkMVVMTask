package com.imaginato.homeworkmvvm.data.remote.demo

import kotlinx.coroutines.flow.Flow

interface DemoRepository {

    suspend fun getDemoData(): Flow<String>
}