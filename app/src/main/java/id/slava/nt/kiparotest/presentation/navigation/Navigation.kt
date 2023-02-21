package id.slava.nt.kiparotest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.slava.nt.kiparotest.MainViewModel
import id.slava.nt.kiparotest.presentation.news_choice.NewsChoice
import id.slava.nt.kiparotest.presentation.news_list.NewsList

@Composable
fun Navigation(viewModel: MainViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.NewsChoiceScreen.route
    ) {
        composable(route = Screen.NewsChoiceScreen.route) {
            NewsChoice(navController = navController, viewModel = viewModel)

        }
        composable(
            route = Screen.ListNewsScreen.route) {
            NewsList(navController = navController, viewModel = viewModel)

        }
    }

}