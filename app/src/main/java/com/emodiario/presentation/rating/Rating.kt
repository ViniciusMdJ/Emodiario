package com.emodiario.presentation.rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emodiario.R
import com.emodiario.presentation.common.ui_components.CardDate
import com.emodiario.presentation.common.ui_components.TextFieldCustom
import com.emodiario.domain.model.Activity
import com.emodiario.domain.model.CommuteRating
import com.emodiario.presentation.common.ScreenState
import com.emodiario.presentation.common.ui_components.DialogError
import com.emodiario.ui.theme.EmodiarioTheme

@Composable
fun RatingScreen(
    viewModel: RatingViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    registerSuccessful: () -> Unit,
    activityId: Int,
    activityName: String
) {
    val activity = Activity(activityId, activityName)
    val screenState = viewModel.uiState.screenState.collectAsState()
    val date = viewModel.uiState.date.collectAsState()
    val showDatePickerDialog = viewModel.uiState.showDatePickerDialog.collectAsState()
    val description = viewModel.uiState.description.collectAsState()
    val ratingSelected = viewModel.uiState.rating.collectAsState()

    ScreenContent(
        onBackPressed = onBackPressed,
        activity = activity,
        date = date.value,
        showDatePickerDialog = showDatePickerDialog.value,
        setShowDatePickerDialog = viewModel.uiState::updateShowDatePickerDialog,
        onConfirmDate = viewModel.uiState::updateDate,
        description = description.value,
        onDescriptionChanged = viewModel.uiState::updateDescription,
        ratingSelected = ratingSelected.value,
        updateRatingSelected = viewModel.uiState::updateRating,
        registerRating = { viewModel.registerRating(registerSuccessful, activityId) }
    )

    if(screenState.value is ScreenState.Error) {
        DialogError(onDismissRequest = { }, title = "Erro", message = (screenState.value as ScreenState.Error).message){
            Button(onClick = viewModel.uiState::setContent) {
                Text(text = stringResource(id = R.string.ok))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    activity: Activity,
    date: Long,
    showDatePickerDialog: Boolean,
    setShowDatePickerDialog: (Boolean) -> Unit,
    onConfirmDate: (Long) -> Unit,
    description: String,
    onDescriptionChanged: (String) -> Unit,
    ratingSelected: CommuteRating?,
    updateRatingSelected: (CommuteRating) -> Unit,
    registerRating: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.rating_title),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { onBackPressed() },
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        tint = Color.White,
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

            CardRating(
                selected = ratingSelected,
                updateRatingSelected = updateRatingSelected
            )

            Text(text = stringResource(id = R.string.label_description_rating_activity))

            TextFieldCustom(
                text = description,
                onTextChanged = onDescriptionChanged,
                placeholderText = stringResource(id = R.string.placeholder_description_rating_activity)
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = registerRating
            ) {
                Text(
                    text = stringResource(id = R.string.btn_register_rating)
                )
            }
        }
    }
}

@Composable
fun CardRating(
    modifier: Modifier = Modifier,
    selected: CommuteRating?,
    updateRatingSelected: (CommuteRating) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.excellent),
            contentDescription = "Excelent",
            modifier = Modifier
                .clip(CircleShape)
                .clickable { updateRatingSelected(CommuteRating.EXCELLENT) }
                .size(50.dp)
                .alpha(
                    when (selected) {
                        null -> 0.5f
                        CommuteRating.EXCELLENT -> 1f
                        else -> 0.25f
                    }
                )
        )
        Image(
            painter = painterResource(id = R.drawable.good),
            contentDescription = "Good",
            modifier = Modifier
                .clip(CircleShape)
                .clickable { updateRatingSelected(CommuteRating.GOOD) }
                .size(50.dp)
                .alpha(
                    when (selected) {
                        null -> 0.5f
                        CommuteRating.GOOD -> 1f
                        else -> 0.25f
                    }
                )
        )
        Image(
            painter = painterResource(id = R.drawable.average),
            contentDescription = "Average",
            modifier = Modifier
                .clip(CircleShape)
                .clickable { updateRatingSelected(CommuteRating.AVERAGE) }
                .size(50.dp)
                .alpha(
                    when (selected) {
                        null -> 0.5f
                        CommuteRating.AVERAGE -> 1f
                        else -> 0.25f
                    }
                )
        )
        Image(
            painter = painterResource(id = R.drawable.bad),

            contentDescription = "Bad",
            modifier = Modifier
                .clip(CircleShape)
                .clickable { updateRatingSelected(CommuteRating.BAD) }
                .size(50.dp)
                .alpha(
                    when (selected) {
                        null -> 0.5f
                        CommuteRating.BAD -> 1f
                        else -> 0.25f
                    }
                )
        )
        Image(
            painter = painterResource(id = R.drawable.terrible),
            contentDescription = "Terrible",
            modifier = Modifier
                .clip(CircleShape)
                .clickable { updateRatingSelected(CommuteRating.TERRIBLE) }
                .size(50.dp)
                .alpha(
                    when (selected) {
                        null -> 0.5f
                        CommuteRating.TERRIBLE -> 1f
                        else -> 0.25f
                    }
                )
        )
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
fun ScreenContentPreview() {
    EmodiarioTheme {
        ScreenContent(
            onBackPressed = {},
            activity = Activity(1, "Activity 1"),
            date = 123123123L,
            showDatePickerDialog = false,
            setShowDatePickerDialog = {},
            onConfirmDate = {},
            description = "",
            onDescriptionChanged = {},
            ratingSelected = null,
            updateRatingSelected = {},
            registerRating = {}
        )
    }
}

@Preview
@Composable
fun CardRatingPreview() {
    EmodiarioTheme {
        CardRating(
            selected = CommuteRating.EXCELLENT,
            updateRatingSelected = {}
        )
    }
}
