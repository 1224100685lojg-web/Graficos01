package mx.edu.utng.lojg.graficos001

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utng.lojg.graficos001.data.database.MeditationDatabase
import mx.edu.utng.lojg.graficos001.data.repository.MeditationRepository
import mx.edu.utng.lojg.graficos001.navigation.NavigationGraph
import mx.edu.utng.lojg.graficos001.ui.theme.viewmodel.MeditationViewModel
import mx.edu.utng.lojg.graficos001.ui.theme.viewmodel.MeditationViewModelFactory
import mx.edu.utng.lojg.graficos001.ui.theme.MeditationTrackerTheme


/**
 * MainActivity: El corazón de la aplicación Android
 *
 * Responsabilidades:
 * 1. Inicializar la base de datos
 * 2. Crear el repositorio
 * 3. Configurar el ViewModel
 * 4. Establecer el contenido de Compose
 *
 * Ciclo de vida:
 * onCreate() -> onStart() -> onResume() -> [APP VISIBLE] ->
 * onPause() -> onStop() -> onDestroy()
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite que el contenido use toda la pantalla (edge-to-edge)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Inicializar la base de datos y repositorio
        val database = MeditationDatabase.getDatabase(applicationContext)
        val repository = MeditationRepository(database.meditationDao())

        // UI con Compose
        setContent {
            MeditationTrackerTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: MeditationViewModel = viewModel(
                        factory = MeditationViewModelFactory(repository)
                    )
                    NavigationGraph(viewModel = viewModel)
                }
            }
        }
    }
}