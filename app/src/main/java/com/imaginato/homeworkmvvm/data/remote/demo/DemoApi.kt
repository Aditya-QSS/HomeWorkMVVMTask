package com.imaginato.homeworkmvvm.data.remote.demo

import com.imaginato.homeworkmvvm.data.remote.demo.response.DemoResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Url

interface DemoApi {
    @GET
    fun getDemoDataAsync(@Url url: String): Deferred<DemoResponse>
}