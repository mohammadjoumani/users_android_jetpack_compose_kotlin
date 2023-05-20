package com.mmj.madarsofttask.presentation.resources.route

sealed class Screen(val route: String) {
    object UsersScreen : Screen(USERS_SCREEN)
    object AddUserScreen : Screen(ADD_USER_SCREEN)
}

const val USERS_SCREEN = "user_screen"
const val ADD_USER_SCREEN = "add_user_screen"