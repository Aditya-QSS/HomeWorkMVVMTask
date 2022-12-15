package com.imaginato.homeworkmvvm.ui.login

import android.os.Bundle
import androidx.compose.material.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.imaginato.homeworkmvvm.ui.login.ViewModel.UserViewModel
import com.imaginato.homeworkmvvm.ui.login.screen.LoginPage
import com.imaginato.homeworkmvvm.ui.login.ui.theme.HomeWorkMVVMTheme
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeWorkMVVMTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    LoginPage()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeWorkMVVMTheme() {
        LoginPage()
    }
}

