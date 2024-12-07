package com.s2start.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.s2start.auth.presentation.ui.login.LoginScreenRoot
import com.s2start.sample.ui.navigation.NavigationRoot
import com.s2start.sample.ui.theme.SampleTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            if(!viewModel.state.isCheckingAuth) {
                val navController = rememberNavController()
                NavigationRoot(
                    navController = navController,
                    isLoggedIn = viewModel.state.isLoggedIn
                )
            }
        }
    }
}