package com.imaginato.homeworkmvvm.exts
import timber.log.Timber

// Extensions for String
const val LOG_TYPE_REQUEST = "log request:"
const val LOG_TYPE_RESPONSE = "log response:"
const val LOG_TYPE_ERROR = "log error:"
const val LOG_TYPE_INFO = "log info:"

fun String.printLog(type: String, tag: String) {
    when (type) {
        LOG_TYPE_REQUEST -> Timber.i(tag, "$type $this")
        LOG_TYPE_RESPONSE -> Timber.i(tag, "$type $this")
        LOG_TYPE_ERROR -> Timber.e(tag, "$type $this")
    }
}