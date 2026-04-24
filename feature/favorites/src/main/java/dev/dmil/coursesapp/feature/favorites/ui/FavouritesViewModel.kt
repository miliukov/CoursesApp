package dev.dmil.coursesapp.feature.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dmil.coursesapp.core.domain.model.Course
import dev.dmil.coursesapp.feature.favorites.domain.usecase.GetFavouritesUseCase
import dev.dmil.coursesapp.feature.favorites.domain.usecase.RemoveFromFavouritesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val removeFromFavouritesUseCase: RemoveFromFavouritesUseCase
): ViewModel() {

    val uiState: StateFlow<List<Course>> = getFavouritesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun removeFavourite(course: Course) {
        viewModelScope.launch {
            removeFromFavouritesUseCase(course.id)
        }
    }

}