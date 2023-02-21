package id.slava.nt.kiparotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import id.slava.nt.kiparotest.presentation.navigation.Navigation
import id.slava.nt.kiparotest.ui.theme.KiparoTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = viewModel<MainViewModel>()

            KiparoTestTheme {

                Navigation(viewModel)

            }
        }
    }
}

