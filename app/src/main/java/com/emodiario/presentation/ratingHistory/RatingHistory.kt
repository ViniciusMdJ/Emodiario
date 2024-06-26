package com.emodiario.presentation.ratingHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emodiario.R
import com.emodiario.common.toHistoryRatingDateFormat
import com.emodiario.domain.model.CommuteRating
import com.emodiario.domain.model.Rating
import com.emodiario.ui.theme.EmodiarioTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingHistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: RatingHistoryViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    activityId: String
) {
    viewModel.getRatingHistory(activityId)
    val items = viewModel.uiState.ratingHistory.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(viewModel.uiState.topAppBarState)
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.register_activity_title),
                        textAlign = TextAlign.Center
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
                    scrolledContainerColor = colorResource(id = R.color.primary),
                ),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(items.value) { rating ->
                CardRatingHistory(rating = rating)
            }
        }
    }
}

@Composable
fun CardRatingHistory(
    rating: Rating
) {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                rating.date.time.toHistoryRatingDateFormat(),
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.tertiary)
            )
            Row(
                verticalAlignment = Alignment.Top
            ) {
                DrawVerticalLineWithCircles()
                Column {
                    Text(
                        text = stringResource(id = R.string.label_mood),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = rating.rating.value,
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = R.color.label_on_empty)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = stringResource(id = R.string.label_daily),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = rating.description,
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = R.color.label_on_empty)
                    )
                }
            }
        }
    }
}

@Composable
fun DrawVerticalLineWithCircles() {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .border(2.dp, colorResource(id = R.color.primary), CircleShape),
        )
        //mak a line with 16dp
        Box(
            modifier = Modifier
                .size(width = 2.dp, height = 25.dp)
                .clip(RectangleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                ),
        )
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .border(5.dp, colorResource(id = R.color.secondary), CircleShape),
        )
    }
}

@Preview
@Composable
fun RatingHistoryPreview() {
    EmodiarioTheme {
        RatingHistoryScreen(
            onBackPressed = {},
            activityId = ""
        )
    }
}

@Preview
@Composable
fun CardRatingHistoryPreview() {
    EmodiarioTheme {
        CardRatingHistory(
            rating = Rating(
                id = 1,
                date = Date(System.currentTimeMillis()),
                rating = CommuteRating.BAD,
                description = "Teste"
            )
        )
    }
}