package com.imaginato.homeworkmvvm.data.remote.demo.response

import com.google.gson.annotations.SerializedName

data class DemoResponse constructor(
    @SerializedName("ip_addr")
    var ipAddress: String?)