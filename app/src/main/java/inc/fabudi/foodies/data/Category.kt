package inc.fabudi.foodies.data

data class Categories(
    val products: List<Category>
)

data class Category(
    val id: Int,
    val name: String
)