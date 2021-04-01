package pet.plants.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import pet.plants.Screen
import pet.plants.navigate
import pet.plants.ui.theme.PetplantsTheme

class LandingFragment : Fragment() {
    private val viewModel: LandingViewModel by viewModels { LandingViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.navigateTo.observe(viewLifecycleOwner) { navigateToEvent ->
            navigateToEvent.getContentIfNotHandled()?.let { navigateTo ->
                navigate(navigateTo, Screen.Shoppe)
            }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                PetplantsTheme() {

                }
            }
        }
    }
}
@Preview
@Composable
fun LandingScreen (){
    Text("Perkele")
}