package com.emodiario.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emodiario.R
import com.emodiario.presentation.common.ScreenState
import com.emodiario.presentation.common.ui_components.ErrorScreen
import com.emodiario.presentation.common.ui_components.LoadingScreen
import com.emodiario.presentation.common.ui_components.NetworkImage
import com.emodiario.ui.theme.EmodiarioTheme

@Composable
fun ProfileScreen(
    onBackPressed: () -> Unit,
    onLogoutPressed: () -> Unit,
    userId: Int,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val screenState = viewModel.uiState.screenState.collectAsState()
    val name = viewModel.uiState.name.collectAsState()
    val photoUrl = viewModel.uiState.photoUrl.collectAsState()
    val showDialog = viewModel.uiState.showDialog.collectAsState()

    when (screenState.value) {
        ScreenState.Loading -> {
            viewModel.getContent(userId)
            LoadingScreen()
        }
        ScreenState.Content -> {
            ScreenContent(
                name = name.value,
                photoUrl = photoUrl.value,
                showDialog = showDialog.value,
                toggleShowDialog = viewModel.uiState::updateShowDialog,
                onBackPressed = onBackPressed,
                onLogoutPressed = onLogoutPressed
            )
        }

        is ScreenState.Error -> {
            ErrorScreen(
                onRetry = {viewModel.getContent(userId)},
                message = (screenState.value as ScreenState.Error).message
            )
        }
    }
}

@Composable
fun ScreenContent(
    name: String,
    photoUrl: String,
    showDialog: Boolean,
    toggleShowDialog: (Boolean) -> Unit,
    onBackPressed: () -> Unit,
    onLogoutPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarProfile(
                imageUrl = photoUrl,
                userName = name,
                iconNavigation = {
                    Icon(
                        modifier = Modifier.clickable { onBackPressed() },
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back buttom",
                        tint = Color.White
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { toggleShowDialog(true) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = "Logout",
                        tint = colorResource(id = R.color.error)
                    )
                    Text(
                        text = stringResource(id = R.string.logout),
                        style = MaterialTheme.typography.titleLarge,
                        color = colorResource(id = R.color.error),
                    )
                }
            }
        }
        if (showDialog) {
            AlertDialog(
                title = { Text(stringResource(id = R.string.title_confirmation_logout)) },
                onDismissRequest = { },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onLogoutPressed()
                        }
                    ) {
                        Text(stringResource(id = R.string.yes))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { toggleShowDialog(false) }
                    ) {
                        Text(stringResource(id = R.string.no))
                    }
                },
            )

        }

    }
}

@Composable
fun TopAppBarProfile(
    imageUrl: String?,
    userName: String,
    iconNavigation: @Composable () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.primary
                    )
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
        ) {
            iconNavigation()
            Text(
                text = stringResource(id = R.string.profile_title),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
            )
        }
        NetworkImage(
            imageUrl = imageUrl,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(50.dp))
        )
        Text(
            text = userName,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }

}

@Preview
@Composable
fun ProfileScreenPreview() {
    EmodiarioTheme {
        ScreenContent(
            name = "Vinicus",
            photoUrl = "",
            showDialog = true,
            toggleShowDialog = {},
            onLogoutPressed = {},
            onBackPressed = {}
        )
    }
}

@Preview
@Composable
fun TopAppBarProfilePreview() {
    EmodiarioTheme {
        TopAppBarProfile(
            imageUrl = null,
            userName = "Vinicius",
            iconNavigation = {
                Icon(
                    modifier = Modifier.clickable { },
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back buttom",
                    tint = Color.White
                )
            }
        )
    }
}