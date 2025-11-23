package com.alexius.weatherio.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alexius.weatherio.common.ui.theme.WeatherIoTheme
import com.alexius.weatherio.common.utils.compose.neumorphicDown
import com.alexius.weatherio.presentation.utils.NavigationType
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EmptyContent(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    image: ImageVector,
    buttonText: String? = null,
    isFullscreen: Boolean = true,
    navigationType: NavigationType,
    buttonAction: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        Image(
            modifier = Modifier
                .neumorphicDown(
                    shape = RoundedCornerShape(50),
                    shadowPadding = 4.dp,
                )
                .sizeIn(200.dp, 200.dp).padding(horizontal = if (isFullscreen) 42.dp else 53.dp),
            imageVector = image,
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth(),
            text = subtitle,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.weight(1f))
        buttonText?.let {
            val buttonPaddingHorizontal = when (navigationType) {
                NavigationType.BOTTOM_NAVIGATION -> 16.dp
                NavigationType.PERMANENT_NAVIGATION_DRAWER -> 200.dp
                NavigationType.NAVIGATION_RAIL -> 8.dp
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(bottom = 8.dp)
                    .padding(horizontal = buttonPaddingHorizontal)
                    .neumorphicDown(
                        shape = ButtonDefaults.shape,
                        shadowPadding = 3.dp,
                    ),
                onClick = { buttonAction?.invoke() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                )
            ) {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyContentPreview() {
    WeatherIoTheme {
        EmptyContent(
            title = "No data",
            subtitle = "There is no data to show you right now.",
            image = Icons.Default.Info,
            buttonText = "Retry",
            isFullscreen = true,
            buttonAction = {},
            navigationType = NavigationType.BOTTOM_NAVIGATION
        )
    }
}
