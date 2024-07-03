package com.emodiario.presentation.common.ui_components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.emodiario.R

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.clip(CircleShape),
        onClick = onClick
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.blank_profile),
            error = painterResource(id = R.drawable.blank_profile),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.Crop
        )
    }
}