package dev.dmil.coursesapp.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dmil.coursesapp.feature.home.R

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeContent(
        uiState = uiState
    )

}

@Composable
private fun HomeContent(
    uiState: HomeUiState
) {

    LazyColumn(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                TextField(
                    value = "",
                    onValueChange = {  },
                    enabled = false,
                    leadingIcon = { Icon(
                        painter = painterResource(R.drawable.search),
                        contentDescription = "Search Icon")
                    }
                )
                Button(
                    onClick = {  }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.filter),
                        contentDescription = "Filter Icon"
                    )
                }
            }

        }
        items(uiState.courses) { course ->
            CourseCard(course)
        }
    }

}