package com.imaginato.homeworkmvvm.ui.login.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.imaginato.homeworkmvvm.R
import com.imaginato.homeworkmvvm.data.local.login.User
import com.imaginato.homeworkmvvm.ui.demo.MainActivityViewModel
import com.imaginato.homeworkmvvm.ui.login.ViewModel.UserViewModel
import org.json.JSONException
import org.json.JSONObject
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import java.io.UnsupportedEncodingException
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@KoinApiExtension
fun LoginPage(modifier: Modifier = Modifier) {
    val focusRequester = remember {
        FocusRequester()
    }
    val ctx = LocalContext.current
    val usernameSate = remember { mutableStateOf("") }
    val passwordSate = remember { mutableStateOf("") }
    val isVisibility = remember {
        mutableStateOf(false)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_jetdevs),
            contentDescription = "JetDevs",
            modifier = Modifier
                .size(200.dp)
                .width(500.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("user"),
            value = usernameSate.value,
            onValueChange = {
                usernameSate.value = it
            },
            label = { Text("Username") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),

            keyboardActions = KeyboardActions(
                onNext = {
                    focusRequester.requestFocus()
                }
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .testTag("password"),
            value = passwordSate.value,
            onValueChange = { passwordSate.value = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (isVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(onClick = { isVisibility.value = !isVisibility.value }) {
                    Icon(
                        imageVector = if (isVisibility.value) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }, contentDescription = null
                    )
                }
            },

            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            enabled = usernameSate.value.isNotEmpty() && passwordSate.value.isNotEmpty(),
            onClick = { postDataUsingVolley(usernameSate.value,passwordSate.value,ctx) }) {
            Text("Login")
        }
    }
}

private fun postDataUsingVolley(username:String,password:String,ctx: Context) {
    val userViewModel: UserViewModel? = null
    val url = "https://private-222d3-homework5.apiary-mock.com/api/login"
    val queue = Volley.newRequestQueue(ctx)
    val request: StringRequest = object : StringRequest(
        Method.POST, url,
        Response.Listener { response ->
            Log.d("YourResponse", "response---$response")
            var respObj: JSONObject? = null
            try {
                respObj = JSONObject(response)
                val errorCode = respObj.getString("errorCode")
                val errorMessage = respObj.getString("errorMessage")
                if (errorCode.equals("00")){
                    val data = respObj.getString("data")
                    var responseData = JSONObject(data)

                    val userId = responseData.getString("userId")
                    val userName = responseData.getString("userName")
                    val isDeleted = responseData.getBoolean("isDeleted")

                    val user = User(userId = userId, username = userName, isDeleted = isDeleted)

                    //save the details to room database
                    userViewModel!!insertUserDetails(user)
                    Toast.makeText(ctx, "Login Successful", Toast.LENGTH_SHORT).show()

                    Log.d("YourResponse", "userId----$userId")
                    Log.d("YourResponse", "userName----$userName")
                    Log.d("YourResponse", "isDeleted----$isDeleted")
                }
                Log.d("YourResponse", "errorMessage----$errorMessage")

            } catch (e: JSONException) {
                Log.d("YourResponse", "error---" + e.message)
                e.printStackTrace()
            }
        },
        Response.ErrorListener { error ->
            Log.d("YourResponse", "error---" + error.message)
            if (error?.networkResponse == null) {
                return@ErrorListener
            }
            val body: String
            //get status code here
            val statusCode = error.networkResponse.statusCode.toString()
            //get response body and parse with appropriate encoding
            try {
                body = String(error.networkResponse.data, StandardCharsets.UTF_8)
                Log.d("YourResponse", "body---$body")
                Toast.makeText(
                    ctx,
                    "Fail to get response body = $body",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: UnsupportedEncodingException) {
                // exception
            }
        }) {
        @Throws(AuthFailureError::class)
        override fun getHeaders(): Map<String, String> {
            val headers: MutableMap<String, String> = HashMap()
            headers["Content-Type"] = "application/json"
            headers["IMSI"] = "357175048449937"
            headers["IMEI"] = "510110406068589"
            return headers
        }

        override fun getBody(): ByteArray {
            val params: MutableMap<Any?, Any?> = HashMap()
            params["username"] = username
            params["password"] = password
            return JSONObject(params).toString().toByteArray()
        }
    }
    request.retryPolicy = DefaultRetryPolicy(
        10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
    )
    queue.add(request)
}