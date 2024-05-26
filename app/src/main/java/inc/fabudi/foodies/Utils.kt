package inc.fabudi.foodies

import inc.fabudi.foodies.data.Product
import io.github.cdimascio.dotenv.dotenv
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object EnvVars {
    val environmentVariables = dotenv {
        directory = "/assets"
        filename = "env"
    }

    val userAgent: String = environmentVariables["USER-AGENT"]
    val baseUrl: String = environmentVariables["API_ENDPOINT"]
}

object Utils {

    private fun formatValue(value: Number, formatString: String): String {
        val formatSymbols = DecimalFormatSymbols(Locale.ENGLISH)
        formatSymbols.setDecimalSeparator('.')
        formatSymbols.setGroupingSeparator(' ')
        val formatter = DecimalFormat(formatString, formatSymbols)
        return formatter.format(value)
    }

    fun Int.toPrice(): String {
        if (this == 0) return ""
        if ((this / 100f).toInt() < this / 100f) return formatValue(this / 100f, "###,###.##") + " ₽"
        return formatValue(this / 100f, "###,###") + " ₽"
    }

    fun Number.withMeasureUnit(unit: String): String = "$this $unit"

    fun Set<Pair<Product, Int>>.findQty(id: Int) = this.find { it.first.id == id }?.second ?: 0
}