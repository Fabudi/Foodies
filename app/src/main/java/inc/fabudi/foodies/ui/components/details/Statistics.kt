package inc.fabudi.foodies.ui.components.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.Utils.withMeasureUnit
import inc.fabudi.foodies.ui.theme.Outline

@Composable
fun Statistics(
    modifier: Modifier = Modifier,
    units: String = "мл",
    measure: Int = 0,
    calories: Double = 0.0,
    proteins: Double = 0.0,
    fats: Double = 0.0,
    carbohydrates: Double = 0.0
) {
    Column(modifier = modifier) {
        HorizontalDivider(color = Outline, thickness = 1.dp)
        val text = if (units.contains("г")) "Вес" else "Объём" // Check if units contains "г", so it should be weight else set "Volume"
        StatisticsRow(modifier = Modifier.padding(start = 16.dp, end = 16.dp), type = text, value = measure.withMeasureUnit(units))
        HorizontalDivider(color = Outline, thickness = 1.dp)
        StatisticsRow(modifier = Modifier.padding(start = 16.dp, end = 16.dp), type = "Энерг. ценность", value = calories.withMeasureUnit("г"))
        HorizontalDivider(color = Outline, thickness = 1.dp)
        StatisticsRow(modifier = Modifier.padding(start = 16.dp, end = 16.dp), type = "Белки", value = proteins.withMeasureUnit("г"))
        HorizontalDivider(color = Outline, thickness = 1.dp)
        StatisticsRow(modifier = Modifier.padding(start = 16.dp, end = 16.dp), type = "Жиры", value = fats.withMeasureUnit("г"))
        HorizontalDivider(color = Outline, thickness = 1.dp)
        StatisticsRow(modifier = Modifier.padding(start = 16.dp, end = 16.dp), type = "Углеводы", value = carbohydrates.withMeasureUnit("г"))
    }
}

@Preview
@Composable
private fun StatisticsPreview() {
    Statistics()
}