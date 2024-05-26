package inc.fabudi.foodies.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import inc.fabudi.foodies.data.CartState
import inc.fabudi.foodies.data.Product
import inc.fabudi.foodies.data.Tag
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
    val searchResultProductsState: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Loading)
    val categoriesState: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Loading)
    val selectedCategory = mutableIntStateOf(0)
    val selectedTags = mutableStateOf(listOf<Tag>())
    val prevSelectedTags = mutableStateOf(listOf<Tag>())
    val cartState: MutableStateFlow<CartState> = MutableStateFlow(CartState.Empty)

    var searchQuery by mutableStateOf("")

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

    fun addItem(productId: Int): Int {
        val products = ((productsState.value as ApiState.Success).data as List<Product>)
        val product = products.find { it.id == productId } ?: products[0]
        if (cartState.value is CartState.Empty) {
            cartState.value = CartState.Filled(setOf(Pair(product, 1)))
            return 1
        }

        val state = cartState.value as CartState.Filled
        val cartProduct = state.products.find { it.first == product }
        if (cartProduct == null) {
            cartState.value = CartState.Filled(state.products.plus(setOf(Pair(product, 1))))
            return 1
        }

        cartState.value = CartState.Filled(
            state.products.minus(cartProduct).plus(
                cartProduct.copy(
                    second = cartProduct.second + 1
                )
            )
        )
        return cartProduct.second + 1
    }

    fun removeItem(productId: Int): Int {
        val product =
            ((productsState.value as ApiState.Success).data as List<Product>).find { it.id == productId }
        if (cartState.value is CartState.Empty) return 0

        val state = cartState.value as CartState.Filled
        val cartProduct = state.products.find { it.first == product }
        if (cartProduct == null) return 0

        val newItemCounter = cartProduct.second - 1
        if (state.products.size == 1 && newItemCounter == 0) {
            cartState.value = CartState.Empty
            return 0
        }
        if (newItemCounter == 0) {
            cartState.value = CartState.Filled(state.products.minus(cartProduct))
            return 0
        }

        cartState.value = CartState.Filled(
            state.products.minus(cartProduct).plus(
                cartProduct.copy(
                    second = newItemCounter
                )
            )
        )
        return newItemCounter
    }

    private fun selectCategory() {
        if (selectedCategory.intValue == 0) sortedProductsState.value =
            ApiState.Success((productsState.value as ApiState.Success).data as List<Product>)
        else sortedProductsState.value =
            ApiState.Success(((productsState.value as ApiState.Success).data as List<Product>).filter { it.category_id == selectedCategory.intValue })
    }

    fun filter() {
        prevSelectedTags.value = selectedTags.value
        selectCategory()
        filterProducts()
        if (((sortedProductsState.value as ApiState.Success).data as List<Product>).isEmpty()) {
            sortedProductsState.value =
                ApiState.Error("Таких блюд нет :(\nПопробуйте изменить фильтры")
        }
    }

    private fun filterProducts() {
        if (prevSelectedTags.value.isEmpty()) return

        val filteredProducts = (productsState.value as ApiState.Success).data as List<Product>
        val filteredProductsByTags =
            filteredProducts.filter { product -> prevSelectedTags.value.all { tag -> product.tag_ids.contains(tag.id) } }
        sortedProductsState.value = ApiState.Success(filteredProductsByTags)
    }

    fun onTagChecked(tag: Tag) {
        selectedTags.value = if (selectedTags.value.contains(tag)) {
            selectedTags.value.minus(tag)
        } else {
            selectedTags.value.plus(tag)
        }
    }

    fun resetFilter() {
        selectedTags.value = prevSelectedTags.value
    }

    fun getProduct(productId: Int): Product {
        val products = ((productsState.value as ApiState.Success).data as List<Product>)
        return products.find { it.id == productId } ?: products[0]
    }

    fun getProductQty(productId: Int): Int {
        return if (cartState.value is CartState.Filled)
                ((cartState.value as CartState.Filled).products.find { it.first.id == productId }?.second)
            ?: 0 else 0
    }

    fun getTotalPrice(): Int {
        return if (cartState.value is CartState.Filled)
            ((cartState.value as CartState.Filled).products.sumOf { it.first.price_current * it.second })
        else 0
    }

    fun searchQuery(query: String) {
        searchQuery = query
        val products = ((productsState.value as ApiState.Success).data as List<Product>)
        if (query.isEmpty()) {
            searchResultProductsState.value =
                ApiState.Error("Введите название блюда, которое ищете")
            return
        }
        searchResultProductsState.value =
            ApiState.Success(products.filter { it.name.contains(query, ignoreCase = true) })
        if ((searchResultProductsState.value as ApiState.Success).data.isEmpty()) searchResultProductsState.value =
            ApiState.Error("Ничего не нашлось :(")
    }
}