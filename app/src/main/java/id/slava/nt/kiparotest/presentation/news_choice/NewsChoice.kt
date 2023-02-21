package id.slava.nt.kiparotest.presentation.news_choice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import id.slava.nt.kiparotest.MainViewModel
import id.slava.nt.kiparotest.presentation.navigation.Screen


@Composable
fun NewsChoice(navController: NavController, viewModel: MainViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize() ){

        Button(onClick = {
            viewModel.getJsonNews()
            navController.navigate(Screen.ListNewsScreen.route)
        },
            modifier = Modifier
                .align(Alignment.TopCenter))
        {
            Text(text = "Json News")
        }
        Button(onClick = {
            viewModel.getXmlNews()
            navController.navigate(Screen.ListNewsScreen.route)
        },
            modifier = Modifier
                .align(Alignment.Center)) {
            Text(text = "Xml News")
        }

    }

}