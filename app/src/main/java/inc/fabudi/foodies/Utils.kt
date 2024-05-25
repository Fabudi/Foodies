package inc.fabudi.foodies

import inc.fabudi.foodies.data.Product
import io.github.cdimascio.dotenv.dotenv

object Utils {

    val environmentVariables = dotenv {
        directory = "/assets"
        filename = "env"
    }

    val userAgent: String = environmentVariables["USER-AGENT"]
    val baseUrl: String = environmentVariables["API_ENDPOINT"]

    fun Int.toPrice(): String? {
        if (this == 0) return null
        if ((this / 100f).toInt() < this / 100f) return "%.2f ₽".format(this / 100f)
        return "${(this / 100f).toInt()} ₽"
    }

    fun Int.toMeasureUnit(unit: String): String = "$this $unit"

    fun Set<Pair<Product, Int>>.findQty(id: Int) = this.find { it.first.id == id }?.second ?: 0
}