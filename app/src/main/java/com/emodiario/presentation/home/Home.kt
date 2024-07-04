package com.emodiario.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emodiario.R
import com.emodiario.presentation.common.ui_components.NetworkImage
import com.emodiario.domain.model.Activity
import com.emodiario.domain.model.User
import com.emodiario.domain.preferences.Prefs
import com.emodiario.presentation.common.ScreenState
import com.emodiario.presentation.common.ui_components.ErrorScreen
import com.emodiario.presentation.common.ui_components.LoadingScreen
import com.emodiario.ui.theme.EmodiarioTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onRegisterNewActivityPressed: () -> Unit,
    onRatingActivityPressed: (Activity) -> Unit,
    onActivityPressed: (Activity) -> Unit,
    onProfilePressed: () -> Unit,
    user: User
) {
    val screenState = viewModel.uiState.screenState.collectAsState()
    val url = viewModel.uiState.imageUrl.collectAsState()
    val activities = viewModel.uiState.activities.collectAsState()

    viewModel.getContent(user)

    when (screenState.value) {
        ScreenState.Loading -> {
            LoadingScreen()
        }

        ScreenState.Content -> {
            ScreenContent(
                url = url.value,
                onRegisterNewActivityPressed = onRegisterNewActivityPressed,
                onRatingActivityPressed = onRatingActivityPressed,
                onActivityPressed = onActivityPressed,
                onProfilePressed = onProfilePressed,
                user = user,
                activities = activities.value
            )
        }

        is ScreenState.Error -> {
            ErrorScreen(
                 onRetry = { viewModel.getContent(user) },
                message = (screenState.value as ScreenState.Error).message
            )
        }
    }
}

@Composable
fun ScreenContent(
    modifier: Modifier = Modifier,
    url: String?,
    user: User,
    activities: List<Activity>,
    onRegisterNewActivityPressed: () -> Unit,
    onRatingActivityPressed: (Activity) -> Unit,
    onActivityPressed: (Activity) -> Unit,
    onProfilePressed: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(68.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = onRegisterNewActivityPressed,
                shape = CircleShape,
            ) {
                Icon(
                    Icons.Filled.Add,
                    "Large floating action button",
                    modifier = Modifier.size(32.dp),
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
        ) {
            item {
                Column {
                    Row {
                        Text(
                            modifier = Modifier.size(200.dp),
                            text = "Olá, ${user.name.split(" ")[0]}",
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
                                    imageUrl = url,
                                    modifier = Modifier.size(130.dp),
                                    onClick = onProfilePressed
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
            items(activities) { activity ->
                CardActivity(
                    activity = activity,
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
fun CardActivity(
    modifier: Modifier = Modifier,
    activity: Activity,
    onRatingActivityPressed: () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
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
fun ScreenContentPreview() {
    EmodiarioTheme {
        ScreenContent(
            url = "",
            user = User(1, "Gabriel", "vinicius@gmail.com", "", ""),
            onRegisterNewActivityPressed = {},
            onRatingActivityPressed = {},
            onActivityPressed = {},
            onProfilePressed = {},
            activities = listOf(
                Activity(1, name = "Nome 1"),
                Activity(2, name = "Nome 2"),
                Activity(3, name = "Nome 3"),
                Activity(4, name = "Nome 4")
            )
        )
    }
}

@Preview
@Composable
fun CardActivityPreview() {
    EmodiarioTheme {
        CardActivity(
            activity = Activity(1, name = "Nome"),
            onRatingActivityPressed = {},
            onClick = {}
        )
    }
}
