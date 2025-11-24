package com.alexius.weatherio.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.alexius.weatherio.R

@Composable
fun UvIndexWeatherWidgetItem(
    modifier: GlanceModifier = GlanceModifier,
    uvIndex: Double
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
    ) {
        Text(
            text = "UV Index",
            style = TextStyle(
                fontSize = 16.sp,
                color = ColorProvider(R.color.widget_secondary)
            ),
        )
        Spacer(GlanceModifier.height(4.dp))
        Text(
            text = uvIndex.toString(),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = ColorProvider(R.color.widget_onSurface)
            )
        )
    }
}