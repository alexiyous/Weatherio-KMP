package com.alexius.weatherio.presentation.forecast.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

@Composable
fun <T> LineGraph(
    modifier: Modifier = Modifier,
    dataPoints: List<T>,
    xValueMapper: (T) -> String,
    yValueMapper: (T) -> Float,
    graphTitle: String? = null,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    labelFontSize: TextUnit = 12.sp,
    gradientColors: List<Color> = listOf(
        MaterialTheme.colorScheme.surfaceContainerHighest,
        Color.Transparent
    )
) {
    val padding = 16.dp
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier.padding(padding)
    ) {
        val maxWidth = size.width
        val maxHeight = size.height

        val xAxisPadding = 60f
        val yAxisPadding = 80f
        val graphPadding = 50f
        val graphWidth = maxWidth - xAxisPadding - graphPadding
        val graphHeight = maxHeight - yAxisPadding - graphPadding

        val yValues = dataPoints.map(yValueMapper)
        val maxYValue = yValues.maxOrNull() ?: 0f
        val minYValue = yValues.minOrNull() ?: 0f
        val yRange = maxYValue - minYValue

        // map to graph coordinates
        val xInterval = graphWidth / (dataPoints.size - 1).coerceAtLeast(1)
        val yInterval = if (yRange > 0) graphHeight / yRange else 0f

        val points = dataPoints.mapIndexed { index, data ->
            Offset(
                x = xAxisPadding + index * xInterval,
                y = maxHeight - graphPadding -  ((yValueMapper(data) - minYValue) * yInterval)
            )
        }

        val path = Path().apply {
            points.forEachIndexed { index, point ->
                if (index == 0) moveTo(point.x, maxHeight - yAxisPadding)
                else lineTo(point.x, point.y)
            }
            lineTo(points.last().x, maxHeight - yAxisPadding)
            close()
        }

        drawPath(
            path = path,
            brush = Brush.verticalGradient(
                colors = gradientColors,
                startY = maxHeight - yAxisPadding,
                endY = graphPadding
            )
        )

        points.zipWithNext { start, end ->
            drawLine(
                color = color,
                strokeWidth = 4f,
                start = start,
                end = end
            )
        }

        val labelStep = (dataPoints.size / 10).coerceAtLeast(1)
        dataPoints.forEachIndexed { index, data ->
            val xPosition = xAxisPadding + index * xInterval
            val labelOffset = Offset(xPosition - labelFontSize.value / 2, maxHeight - yAxisPadding + 10f)
            if (index % labelStep == 0) {
                drawText(
                    textMeasurer = textMeasurer,
                    text = xValueMapper(data),
                    topLeft = labelOffset,
                    style = TextStyle(color = color)
                )
            }
        }

        val yLabelStep = yRange / 10
        for (i in 0..10) {
            val yValue = minYValue + i * yLabelStep
            val yPosition = maxHeight - yAxisPadding - i * (graphHeight / 10)
            drawText(
                textMeasurer = textMeasurer,
                text = yValue.roundToInt().toString(),
                topLeft = Offset(yAxisPadding / 2 - labelFontSize.value, yPosition - labelFontSize.value / 2),
                style = TextStyle(color = color)
            )
        }

        graphTitle?.let {
            drawText(
                textMeasurer = textMeasurer,
                text = it,
                topLeft = Offset((maxWidth / 2 - textMeasurer.measure(it).size.width) / 2, graphPadding / 2),
                style = TextStyle(color = color, fontWeight = FontWeight.Bold, fontSize = (labelFontSize * 1.2))
            )
        }
    }
}

@Preview
@Composable
fun LineGraphPreview() {
    data class DataPoint(val x: String, val y: Float)

    val dataPoints = listOf(
        DataPoint("Mon", 10f),
        DataPoint("Tue", 15f),
        DataPoint("Wed", 12f),
        DataPoint("Thu", 18f),
        DataPoint("Fri", 20f),
        DataPoint("Sat", 22f),
        DataPoint("Sun", 19f)
    )
    LineGraph(dataPoints = dataPoints, xValueMapper = { it.x }, yValueMapper = { it.y }, graphTitle = "Weekly Temperature")
}
