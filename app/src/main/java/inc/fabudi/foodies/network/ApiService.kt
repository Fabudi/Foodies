package inc.fabudi.foodies.network

import inc.fabudi.foodies.data.Category
import inc.fabudi.foodies.data.Product
import inc.fabudi.foodies.data.Products
import inc.fabudi.foodies.data.Tag
import retrofit2.http.GET

interface ApiService {
    @GET("Categories.json")
    suspend fun getCategories(): List<Category>

    @GET("Tags.json")
    suspend fun getTags(): List<Tag>

    @GET("Products.json")
    suspend fun getProducts(): List<Product>
}

interface ApiState {
    data object Loading : ApiState
    data class Success(val data: Any) : ApiState
    data class Error(val message: String): ApiState
}