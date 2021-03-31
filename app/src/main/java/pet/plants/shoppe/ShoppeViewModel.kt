package pet.plants.shoppe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pet.plants.Screen
import pet.plants.util.Event

class ShoppeViewModel: ViewModel() {

    private val _navigateTo = MutableLiveData<Event<Screen>>()
    val navigateTo: LiveData<Event<Screen>> = _navigateTo

}

@Suppress("UNCHECKED_CAST")
class ShoppeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppeViewModel::class.java)) {
            return ShoppeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
