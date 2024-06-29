package com.emodiario.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.emodiario.domain.model.Activity
import com.emodiario.domain.model.Rating
import com.emodiario.navigation.Arguments.ACTIVITY_ARG
import com.emodiario.navigation.Arguments.RATING_ARG
import com.emodiario.navigation.Routes.HOME
import com.emodiario.navigation.Routes.LOGIN
import com.emodiario.navigation.Routes.RATING_HISTORY
import com.emodiario.navigation.Routes.RATING_SCREEN
import com.emodiario.navigation.Routes.REGISTER
import com.emodiario.navigation.Routes.REGISTER_ACTIVITY
import com.emodiario.presentation.home.HomeScreen
import com.emodiario.presentation.login.LoginScreen
import com.emodiario.presentation.rating.RatingScreen
import com.emodiario.presentation.ratingHistory.RatingHistoryScreen
import com.emodiario.presentation.register.RegisterScreen
import com.emodiario.presentation.registerActivity.RegisterActivityScreen
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

object Routes {
    const val LOGIN = "LOGIN_SCREEN"
    const val REGISTER = "REGISTER_SCREEN"
    const val HOME = "HOME_SCREEN"
    const val REGISTER_ACTIVITY = "REGISTER_ACTIVITY_SCREEN"
    const val RATING_SCREEN = "RATING_SCREEN"
    const val RATING_HISTORY = "RATING_HISTORY_SCREEN"
}

object Arguments {
    const val ACTIVITY_ARG = "ACTIVITY_ID"
    const val RATING_ARG = "RATING_ID"
}

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController(),
    onBackPressed: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = LOGIN
    ) {
        composable(HOME) {
            HomeScreen(
                onRatingActivityPressed = { activity ->
                    navController.navigate("${RATING_SCREEN}/${activity.id}")
                },
                onRegisterNewActivityPressed = {
                    navController.navigate(REGISTER_ACTIVITY)
                },
                onActivityPressed = { activity ->
                    navController.navigate("$RATING_HISTORY/${activity.id}")
                }
            )
        }
        composable(LOGIN) {
            LoginScreen(
                onRegisterPressed = {
                    navController.navigate(REGISTER)
                },
                navigateLoginSuccessful = {
                    navController.navigate(HOME)
                },
                onForgetPasswordPressed = {}
            )
        }
        composable(REGISTER) {
            RegisterScreen(
                navigateRegisterSuccessful = { },
                onBackPressed = onBackPressed
            )
        }
        composable(
            "$RATING_SCREEN/{$ACTIVITY_ARG}",
            arguments = listOf(navArgument(ACTIVITY_ARG) { type = NavType.StringType })
        ) { backStackEntry ->
            RatingScreen(
                onBackPressed = onBackPressed,
                activityId = backStackEntry.arguments?.getString(ACTIVITY_ARG)!!,
                ratingId = backStackEntry.arguments?.getString(RATING_ARG)
            )
        }
        composable(
            "$RATING_HISTORY/{$ACTIVITY_ARG}",
            arguments = listOf(navArgument(ACTIVITY_ARG) { type = NavType.StringType })
        ) { backStackEntry ->
            RatingHistoryScreen(
                onBackPressed = onBackPressed,
                activityId = backStackEntry.arguments?.getString(ACTIVITY_ARG)!!
            )
        }
        composable(REGISTER_ACTIVITY) {
            RegisterActivityScreen(
                onBackPressed = onBackPressed
            )
        }
    }
}