package inc.fabudi.foodies

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import inc.fabudi.foodies.ui.navigation.NavigationGraph
import inc.fabudi.foodies.ui.theme.FoodiesTheme
import inc.fabudi.foodies.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewmodel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            DisposableEffect(isSystemInDarkTheme()) {
                this@MainActivity.enableEdgeToEdge(
                    statusBarStyle =
                        SystemBarStyle.light(Color.WHITE, Color.BLACK)
                )
                onDispose {  }
            }
            FoodiesTheme(darkTheme = false) {
                val navController = rememberNavController()
                NavigationGraph(navController = navController, viewmodel = viewmodel)
            }
        }
    }
}