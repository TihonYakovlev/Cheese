package com.example.cheese.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cheese.screens.profile_screen.EnterProfileScreen
import com.example.cheese.screens.profile_screen.PersonalDataScreen
import com.example.cheese.screens.profile_screen.ProfileScreen
import com.example.cheese.view_models.ProfileViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    profileViewModel: ProfileViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "screen_profile"
    ){
        composable("screen_profile"){
            ProfileScreen(navController = navController)
        }
        composable("screen_enter_profile"){
            EnterProfileScreen(navController = navController)
        }
        composable("screen_personal_data") {
            PersonalDataScreen(navController = navController)
        }
    }
}