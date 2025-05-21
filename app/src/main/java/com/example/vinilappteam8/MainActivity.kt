package com.example.vinilappteam8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.vinilappteam8.navigation.NavManager
import com.example.vinilappteam8.ui.theme.AppTheme
import com.example.vinilappteam8.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val navController = rememberNavController()
            val viewModel = hiltViewModel<MainViewModel>()

            AppTheme(dynamicColor = false, darkTheme = true) {
                NavManager(navController = navController, viewModel = viewModel)
            }

        }
    }
}