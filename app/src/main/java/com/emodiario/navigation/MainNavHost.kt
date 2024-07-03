package com.emodiario.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.emodiario.domain.preferences.Prefs
import com.emodiario.navigation.Arguments.ACTIVITY_ARG
import com.emodiario.navigation.Arguments.ACTIVITY_NAME
import com.emodiario.navigation.Routes.HOME
import com.emodiario.navigation.Routes.LOGIN
import com.emodiario.navigation.Routes.PROFILE
import com.emodiario.navigation.Routes.RATING_HISTORY
import com.emodiario.navigation.Routes.RATING_SCREEN
import com.emodiario.navigation.Routes.REGISTER
import com.emodiario.navigation.Routes.REGISTER_ACTIVITY
import com.emodiario.presentation.home.HomeScreen
import com.emodiario.presentation.login.LoginScreen
import com.emodiario.presentation.profile.ProfileScreen
import com.emodiario.presentation.rating.RatingScreen
import com.emodiario.presentation.ratingHistory.RatingHistoryScreen
import com.emodiario.presentation.register.RegisterScreen
import com.emodiario.presentation.registerActivity.RegisterActivityScreen

object Routes {
    const val LOGIN = "LOGIN_SCREEN"
    const val REGISTER = "REGISTER_SCREEN"
    const val HOME = "HOME_SCREEN"
    const val REGISTER_ACTIVITY = "REGISTER_ACTIVITY_SCREEN"
    const val RATING_SCREEN = "RATING_SCREEN"
    const val RATING_HISTORY = "RATING_HISTORY_SCREEN"
    const val PROFILE = "PROFILE_SCREEN"
}

object Arguments {
    const val ACTIVITY_ARG = "ACTIVITY_ID"
    const val ACTIVITY_NAME = "ACTIVITY_NAME"
}

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController(),
    onBackPressed: () -> Unit,
    prefs: Prefs
) {
    NavHost(
        navController = navController,
        startDestination = if (prefs.isLogged) HOME else LOGIN
    ) {
        composable(HOME) {
            HomeScreen(
                onRatingActivityPressed = { activity ->
                    navController.navigate("${RATING_SCREEN}/${activity.id}/${activity.name}")
                },
                onRegisterNewActivityPressed = {
                    navController.navigate(REGISTER_ACTIVITY)
                },
                onActivityPressed = { activity ->
                    navController.navigate("$RATING_HISTORY/${activity.id}/${activity.name}")
                },
                onProfilePressed = {
                    navController.navigate(PROFILE)
                },
                prefs = prefs
            )
        }
        composable(LOGIN) {
            LoginScreen(
                onRegisterPressed = {
                    navController.navigate(REGISTER)
                },
                navigateLoginSuccessful = { user ->
                    prefs.login(user)
                    navController.navigate(HOME) {
                        popUpTo(LOGIN) {
                            inclusive = true
                        }
                    }
                },
                onForgetPasswordPressed = {}
            )
        }
        composable(REGISTER) {
            RegisterScreen(
                navigateRegisterSuccessful = { user ->
                    prefs.login(user)
                    navController.navigate(HOME) {
                        popUpTo(LOGIN) {
                            inclusive = true
                        }
                    }
                },
                onBackPressed = onBackPressed
            )
        }
        composable(
            "$RATING_SCREEN/{$ACTIVITY_ARG}/{$ACTIVITY_NAME}",
            arguments = listOf(
                navArgument(ACTIVITY_ARG) { type = NavType.IntType },
                navArgument(ACTIVITY_NAME) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            RatingScreen(
                onBackPressed = onBackPressed,
                registerSuccessful = onBackPressed,
                activityId = backStackEntry.arguments?.getInt(ACTIVITY_ARG)!!,
                activityName = backStackEntry.arguments?.getString(ACTIVITY_NAME)!!
            )
        }
        composable(
            "$RATING_HISTORY/{$ACTIVITY_ARG}/{$ACTIVITY_NAME}",
            arguments = listOf(
                navArgument(ACTIVITY_ARG) { type = NavType.IntType },
                navArgument(ACTIVITY_NAME) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            RatingHistoryScreen(
                onBackPressed = onBackPressed,
                activityId = backStackEntry.arguments?.getInt(ACTIVITY_ARG)!!,
                activityName = backStackEntry.arguments?.getString(ACTIVITY_NAME)!!,
            )
        }
        composable(REGISTER_ACTIVITY) {
            RegisterActivityScreen(
                userId = prefs.usuario!!.id,
                onBackPressed = onBackPressed
            )
        }
        composable(PROFILE) {
            ProfileScreen(
                userId = prefs.usuario!!.id,
                onBackPressed = onBackPressed,
                onLogoutPressed = {
                    prefs.logout()
                    navController.navigate(LOGIN) {
                        popUpTo(HOME) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}