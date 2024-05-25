package inc.fabudi.foodies.data

interface CartState {
    data object Empty : CartState
    data class Filled(
        val products: Set<Pair<Product, Int>>
    ) : CartState {
        val totalPrice: Int
            get() = products.sumOf { it.first.price_current * it.second }
    }
}