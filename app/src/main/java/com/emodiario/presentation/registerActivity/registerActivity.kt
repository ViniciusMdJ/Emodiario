package com.emodiario.presentation.registerActivity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emodiario.R
import com.emodiario.common.ui_components.TextFieldCustom
import com.emodiario.ui.theme.EmodiarioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterActivityScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterActivityViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.register_activity_title),
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
                .fillMaxSize()
                .padding(vertical = 32.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = stringResource(id = R.string.label_name_resgister_activity))

            TextFieldCustom(
                text = viewModel.uiState.nameActivity.collectAsState().value,
                onTextChanged = viewModel.uiState::updateNameActivity,
                placeholderText = stringResource(id = R.string.placeholder_name_register_activity)
            )

            Text(text = stringResource(id = R.string.label_description_resgister_activity))

            TextFieldCustom(
                text = viewModel.uiState.description.collectAsState().value ?: "",
                onTextChanged = viewModel.uiState::updateDescription,
                placeholderText = stringResource(id = R.string.placeholder_description_register_activity)
            )

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = { viewModel.registerActivity() }
            ) {
                Text(
                    text = stringResource(id = R.string.btn_register)
                )
            }
        }
    }
}

@Preview
@Composable
fun RegisterActivityScreenPreview() {
    EmodiarioTheme {
        RegisterActivityScreen(
            onBackPressed = { }
        )
    }
}