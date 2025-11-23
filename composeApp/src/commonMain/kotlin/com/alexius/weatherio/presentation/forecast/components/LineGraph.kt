package com.alexius.weatherio.presentation.forecast.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexius.weatherio.common.utils.compose.neumorphicUp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

@Composable
fun <T> LineGraph(
    modifier: Modifier = Modifier,
    dataPoints: List<T>,
    xValueMapper: (T) -> String,
    yValueMapper: (T) -> Float,
    graphTitle: String? = null,
    lineColor: Color = MaterialTheme.colorScheme.primary,
    axisColor: Color = MaterialTheme.colorScheme.outlineVariant,
    labelColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    labelFontSize: TextUnit = 12.sp,
    gradientColors: List<Color> = listOf(
        MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
        MaterialTheme.colorScheme.primary.copy(alpha = 0.0f)
    )
) {
    if (dataPoints.isEmpty()) return

    val textMeasurer = rememberTextMeasurer()
    val padding = 24.dp

    val circleColor = MaterialTheme.colorScheme.surface
    val titleTextColor = MaterialTheme.colorScheme.onSurface

    Box(
        modifier = modifier
            .fillMaxWidth()
            .neumorphicUp(
                shape = RoundedCornerShape(24.dp),
                shadowPadding = 6.dp,
            )
            .height(320.dp)
            .padding(padding)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize().padding(top = 20.dp) // Top padding for title
        ) {
            val width = size.width
            val height = size.height

            val xAxisSpace = 30.dp.toPx()
            val yAxisSpace = 30.dp.toPx()

            val graphWidth = width - yAxisSpace
            val graphHeight = height - xAxisSpace

            val yValues = dataPoints.map(yValueMapper)
            val maxY = yValues.maxOrNull() ?: 0f
            val minY = yValues.minOrNull() ?: 0f
            val yRange = (maxY - minY).coerceAtLeast(1f)


            val yMinDisplay = minY - (yRange * 0.2f)
            val yMaxDisplay = maxY + (yRange * 0.2f)
            val yRangeDisplay = yMaxDisplay - yMinDisplay

            val points = dataPoints.mapIndexed { index, data ->
                val x =
                    yAxisSpace + (index.toFloat() / (dataPoints.size - 1).coerceAtLeast(1)) * graphWidth
                val y =
                    graphHeight - ((yValueMapper(data) - yMinDisplay) / yRangeDisplay) * graphHeight
                Offset(x, y)
            }


            val yGridCount = 5
            for (i in 0..yGridCount) {
                val progress = i.toFloat() / yGridCount
                val y = graphHeight - (progress * graphHeight)
                val value = yMinDisplay + (progress * yRangeDisplay)

                drawLine(
                    color = axisColor.copy(alpha = 0.3f),
                    start = Offset(yAxisSpace, y),
                    end = Offset(width, y),
                    strokeWidth = 1.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                )

                val measuredText = textMeasurer.measure(
                    text = value.roundToInt().toString(),
                    style = TextStyle(
                        color = labelColor,
                        fontSize = labelFontSize,
                        textAlign = TextAlign.End
                    )
                )

                drawText(
                    textLayoutResult = measuredText,
                    topLeft = Offset(
                        yAxisSpace - measuredText.size.width - 8.dp.toPx(),
                        y - measuredText.size.height / 2
                    )
                )
            }


            val xLabelSkip = (dataPoints.size / 6).coerceAtLeast(1)
            dataPoints.forEachIndexed { index, data ->
                if (index % xLabelSkip == 0) {
                    val x = points[index].x
                    val label = xValueMapper(data)

                    val measuredText = textMeasurer.measure(
                        text = label,
                        style = TextStyle(
                            color = labelColor,
                            fontSize = labelFontSize,
                            textAlign = TextAlign.Center
                        )
                    )

                    drawText(
                        textLayoutResult = measuredText,
                        topLeft = Offset(x - measuredText.size.width / 2, graphHeight + 12.dp.toPx())
                    )
                }
            }

            val strokePath = Path().apply {
                if (points.isNotEmpty()) {
                    moveTo(points.first().x, points.first().y)

                    for (i in 0 until points.size - 1) {
                        val p0 = points[i]
                        val p1 = points[i + 1]

                        val controlX = (p0.x + p1.x) / 2f
                        cubicTo(
                            x1 = controlX, y1 = p0.y,
                            x2 = controlX, y2 = p1.y,
                            x3 = p1.x, y3 = p1.y
                        )
                    }
                }
            }

            val fillPath = Path().apply {
                addPath(strokePath)
                lineTo(points.last().x, graphHeight)
                lineTo(points.first().x, graphHeight)
                close()
            }

            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = gradientColors,
                    startY = 0f,
                    endY = graphHeight
                )
            )

            drawPath(
                path = strokePath,
                color = lineColor,
                style = Stroke(
                    width = 4.dp.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )


            points.forEach { point ->
                drawCircle(
                    color = circleColor,
                    radius = 5.dp.toPx(),
                    center = point,
                )
                drawCircle(
                    color = lineColor,
                    radius = 5.dp.toPx(),
                    center = point,
                    style = Stroke(width = 2.dp.toPx())
                )
            }

            graphTitle?.let {
                val measuredTitle = textMeasurer.measure(
                    text = it,
                    style = TextStyle(
                        color = titleTextColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                drawText(
                    textLayoutResult = measuredTitle,
                    topLeft = Offset(
                        (width - measuredTitle.size.width) / 2,
                        -30.dp.toPx()
                    )
                )
            }
        }
    }
}
