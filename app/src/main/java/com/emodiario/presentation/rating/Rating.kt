package com.emodiario.presentation.rating

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emodiario.R
import com.emodiario.common.ScreenState
import com.emodiario.common.ui_components.CardDate
import com.emodiario.common.ui_components.LoadingScreen
import com.emodiario.common.ui_components.TextFieldCustom
import com.emodiario.domain.model.Activity
import com.emodiario.ui.theme.EmodiarioTheme

@Composable
fun RatingScreen(
    viewModel: RatingViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    activityId: String,
    ratingId: String? = null
) {
    val screenState = viewModel.uiState.screenState.collectAsState()
    val activity = viewModel.uiState.activity.collectAsState()
    val date = viewModel.uiState.date.collectAsState()
    val showDatePickerDialog = viewModel.uiState.showDatePickerDialog.collectAsState()
    val description = viewModel.uiState.description.collectAsState()

    viewModel.getContent(activityId, ratingId)
    when(screenState.value) {
        ScreenState.Loading -> {
            LoadingScreen()
        }
        ScreenState.Content -> {
            activity.value?.let {
                RatingContent(
                    onBackPressed = onBackPressed,
                    activity = it,
                    date = date.value,
                    showDatePickerDialog = showDatePickerDialog.value,
                    setShowDatePickerDialog = viewModel.uiState::updateShowDatePickerDialog,
                    onConfirmDate = viewModel.uiState::updateDate,
                    description = description.value ?: "",
                    onDescriptionChanged = viewModel.uiState::updateDescription
                )
            }
        }

        is ScreenState.Error -> {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingContent(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    activity: Activity,
    date: Long,
    showDatePickerDialog: Boolean,
    setShowDatePickerDialog: (Boolean) -> Unit,
    onConfirmDate: (Long) -> Unit,
    description: String,
    onDescriptionChanged: (String) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.rating_title),
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { onBackPressed() },
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back buttom"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.primary),
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CardActivityRating(activity = activity)

            Text(text = stringResource(id = R.string.label_date_rating_activity))

            CardDate(
                date = date,
                showDatePickerDialog = showDatePickerDialog,
                setShowDatePickerDialog = setShowDatePickerDialog,
                onConfirmDate = onConfirmDate
            )

            Text(text = stringResource(id = R.string.label_rating_activity))

            CardRating()

            Text(text = stringResource(id = R.string.label_description_rating_activity))

            TextFieldCustom(
                text = description,
                onTextChanged = onDescriptionChanged,
                placeholderText = stringResource(id = R.string.placeholder_description_rating_activity)
            )
        }
    }
}

@Composable
fun CardRating(
    modifier: Modifier = Modifier
) {
    Row {

    }
}

@Composable
fun CardActivityRating(
    activity: Activity,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp)),
        color = MaterialTheme.colorScheme.tertiary
    ) {
        Row(
            modifier = Modifier
                .padding(22.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = activity.name,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
fun RatingScreenPreview() {
    EmodiarioTheme {
        RatingScreen(
            onBackPressed = {},
            ratingId = "my id",
            activityId = "my id"
        )
    }
}

@Preview
@Composable
fun CardActivityPreview() {
    EmodiarioTheme {
        CardActivityRating(
            activity = Activity("my id", name = "Nome")
        )
    }
}
