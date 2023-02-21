package id.slava.nt.kiparotest.presentation.navigation

sealed class Screen(val route: String) {
    object NewsChoiceScreen: Screen("choice_news_screen")
    object ListNewsScreen: Screen("list_news_screen")
}
