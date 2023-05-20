package com.mmj.madarsofttask.presentation.resources.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmj.madarsofttask.presentation.main.MainViewModel
import com.mmj.madarsofttask.presentation.adduser.AddUserScreen
import com.mmj.madarsofttask.presentation.users.UsersScreen

@Composable
fun NavGraph(
    mainViewModel: MainViewModel
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.UsersScreen.route) {
        composable(route = Screen.UsersScreen.route) {
            UsersScreen(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }
        composable(route = Screen.AddUserScreen.route) {
            AddUserScreen(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }
    }
}