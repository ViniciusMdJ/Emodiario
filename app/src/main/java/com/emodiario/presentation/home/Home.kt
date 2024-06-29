package com.emodiario.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.emodiario.R
import com.emodiario.domain.model.Activity
import com.emodiario.ui.theme.EmodiarioTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onRegisterNewActivityPressed: () -> Unit,
    onRatingActivityPressed: (Activity) -> Unit,
    onActivityPressed: (Activity) -> Unit
) {
    val url = viewModel.uiState.imageUrl.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        floatingActionButton = {
            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = onRegisterNewActivityPressed
            ) {
                Text(text = stringResource(id = R.string.btn_register_newActivity))

            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                ),
            verticalArrangement = Arrangement.Top,

            ) {
            item {
                Column {
                    Row {
                        Text(
                            modifier = Modifier.size(200.dp),
                            text = "Olá, Vinicius",
                            style = MaterialTheme.typography.displayLarge,
                            minLines = 2,
                            maxLines = 2
                        )
                        Surface(
                            modifier = Modifier.align(Alignment.CenterVertically),
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(160.dp)
                                    .clip(CircleShape)
                                    .background(
                                        brush = Brush.linearGradient(
                                            colors = listOf(
                                                MaterialTheme.colorScheme.secondary,
                                                MaterialTheme.colorScheme.primary
                                            )
                                        )
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                NetworkImage(
                                    imageUrl = url.value,
                                    modifier = Modifier.size(130.dp)
                                )

                            }
                        }
                    }
                    Text(
                        text = stringResource(id = R.string.home_description),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

            }
            items(List(7) { Activity("my id", name = "Nome") }) { activity ->
                CardActivity(
                    activity = activity,
                    modifier = Modifier.padding(vertical = 6.dp),
                    onRatingActivityPressed = { onRatingActivityPressed(activity) },
                    onClick = { onActivityPressed(activity) }
                )
            }
            item {
                Spacer(modifier = Modifier.size(60.dp))
            }
        }

    }
}

@Composable
fun NetworkImage(imageUrl: String?, modifier: Modifier) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.drawable.blank_profile),
        contentDescription = stringResource(R.string.app_name),
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(CircleShape)
    )
}

@Composable
fun CardActivity(
    modifier: Modifier = Modifier,
    activity: Activity,
    onRatingActivityPressed: () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .clickable { onClick() },
        color = MaterialTheme.colorScheme.tertiary
    ) {
        Row(
            modifier = Modifier
                .padding(22.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = activity.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(id = R.string.text_card_eval),
                modifier = Modifier
                    .clickable { onRatingActivityPressed() }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    EmodiarioTheme {
        HomeScreen(
            onRegisterNewActivityPressed = {},
            onRatingActivityPressed = {},
            onActivityPressed = {}
        )
    }
}

@Preview
@Composable
fun CardActivityPreview() {
    EmodiarioTheme {
        CardActivity(
            activity = Activity("my id", name = "Nome"),
            onRatingActivityPressed = {},
            onClick = {}
        )
    }
}
