import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home :
        Screen(
            "home",
            "Home",
            androidx.compose.material.icons.Icons.Filled.Home
        )
    object FavoriteMovies : Screen("movies", "Fav Movies",
        androidx.compose.material.icons.Icons.Filled.Person)
    object Settings : Screen("settings", "Settings",
        androidx.compose.material.icons.Icons.Filled.Settings)
}