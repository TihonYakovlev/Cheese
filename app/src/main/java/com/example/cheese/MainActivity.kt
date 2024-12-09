package com.example.cheese

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.cheese.navigation.AppNavigation
import com.example.cheese.ui.theme.CheeseTheme
import com.example.cheese.view_models.ProfileViewModel

class MainActivity : ComponentActivity() {

    //private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val profileViewModel: ProfileViewModel by viewModels()
       // profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            CheeseTheme {
                    AppNavigation(navController = navController, profileViewModel = profileViewModel)
            }
        }
    }
}

