package pet.plants

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.security.InvalidParameterException

enum class Screen { Shoppe, Login }

fun Fragment.navigate(to: Screen, from: Screen) {
    if (to == from) {
        throw InvalidParameterException("Can't navigate to $to")
    }
    when (to) {
        Screen.Shoppe -> {
            findNavController().navigate(R.id.shoppe_fragment)
        }
        Screen.Login -> {
            findNavController().navigate(R.id.login_fragment)
        }
    }
}