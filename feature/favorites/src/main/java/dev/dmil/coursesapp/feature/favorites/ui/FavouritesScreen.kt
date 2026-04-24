package dev.dmil.coursesapp.feature.favorites.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dmil.coursesapp.core.domain.model.Course
import dev.dmil.coursesapp.core.ui.CourseCard

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FavouritesContent(
        uiState = uiState,
        removeFavourite = viewModel::removeFavourite
    )
}

@Composable
private fun FavouritesContent(
    uiState: List<Course>,
    removeFavourite: (Course) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(uiState) { course ->
            CourseCard(
                course,
                onFavouriteClick = { removeFavourite(course) }
            )
        }
    }
}