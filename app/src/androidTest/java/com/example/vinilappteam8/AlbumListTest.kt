import android.app.Application
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import androidx.lifecycle.MutableLiveData
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinilappteam8.models.Album
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.example.vinilappteam8.viewmodels.AlbumsViewModel
import com.example.vinilappteam8.views.AlbumsView
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumsViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAlbumsAreListed() {
        // Verifica que el botón "Entrar como Invitado" está visible
        composeTestRule.onNodeWithText("Entrar como Invitado")
            .assertIsDisplayed()
            .performClick()

        // Simula un ViewModel con datos de prueba
        val fakeAlbums = listOf(
            Album(
                albumId = 1,
                name = "Artist 1",
                cover = TODO(),
                releaseDate = TODO(),
                description = TODO(),
                genre = TODO(),
                recordLabel = TODO()
            ),
            Album(
                albumId = 2,
                name = "Artist 2",
                cover = TODO(),
                releaseDate = TODO(),
                description = TODO(),
                genre = TODO(),
                recordLabel = TODO()
            )
        )

        val fakeViewModel = FakeAlbumsViewModel(
            application = ApplicationProvider.getApplicationContext()
        ).apply {
            albums.value = fakeAlbums
            eventNetworkError.value = false
        }

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        // Configura el contenido de Compose
        composeTestRule.setContent {
            AlbumsView(
                innerPadding = PaddingValues(0.dp),
                navController = navController
            )
        }

        // Verifica que los álbumes se muestran en la lista
        fakeAlbums.forEach { album ->
            composeTestRule.onNodeWithText(album.name).assertIsDisplayed()
        }
    }
}

class FakeAlbumsViewModel(application: Application) : AlbumsViewModel(application) {
    override val albums = MutableLiveData<List<Album>>()
    override val eventNetworkError = MutableLiveData<Boolean>(false)
}
