package inc.fabudi.foodies.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import inc.fabudi.foodies.data.CartState
import inc.fabudi.foodies.network.ApiService
import inc.fabudi.foodies.network.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    val tagsState: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Loading)
    private val productsState: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Loading)
    val sortedProductsState: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Loading)
    val categoriesState: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Loading)
    val selectedCategory = mutableIntStateOf(0)
    val cartState: MutableStateFlow<CartState> = MutableStateFlow(CartState.Empty)

    fun fetchTags() {
        viewModelScope.launch {
            try {
                val response = apiService.getTags()
                tagsState.value = ApiState.Success(response)
            } catch (e: Exception) {
                tagsState.value = ApiState.Error("Failed to fetch data")
            }
        }
    }

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = apiService.getProducts()
                productsState.value = ApiState.Success(response)
                sortedProductsState.value = ApiState.Success(response)
            } catch (e: Exception) {
                productsState.value = ApiState.Error("Failed to fetch data")
            }
        }
    }

    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = apiService.getCategories()
                categoriesState.value = ApiState.Success(response)
            } catch (e: Exception) {
                categoriesState.value = ApiState.Error("Failed to fetch data")
            }
        }
    }

}