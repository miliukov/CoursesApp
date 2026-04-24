package dev.dmil.coursesapp.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dmil.coursesapp.core.domain.model.Course
import dev.dmil.coursesapp.feature.home.domain.usecase.AddToFavouritesUseCase
import dev.dmil.coursesapp.feature.home.domain.usecase.GetCoursesUseCase
import dev.dmil.coursesapp.feature.home.domain.usecase.RemoveFromFavouritesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val isSorted: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val addToFavouritesUseCase: AddToFavouritesUseCase,
    private val removeFromFavouritesUseCase: RemoveFromFavouritesUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getCoursesUseCase()
                .onSuccess { courses ->
                    _uiState.value = _uiState.value.copy(
                        courses = courses,
                        isLoading = false
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        error = error.localizedMessage,
                        isLoading = false
                    )
                }
        }
    }

    fun toggleSort() {
        val sorted = !_uiState.value.isSorted
        val courses = if (sorted) {
            _uiState.value.courses.sortedByDescending { it.publishDate }
        } else {
            _uiState.value.courses.sortedBy { it.publishDate }
        }
        _uiState.value = _uiState.value.copy(
            isSorted = sorted,
            courses = courses
        )
    }

    fun toggleFavourite(course: Course) {
        viewModelScope.launch {
            if (course.hasLike) {
                removeFromFavouritesUseCase(course.id)
                    .onSuccess {
                        _uiState.value = _uiState.value.copy(
                            courses = _uiState.value.courses.map { c ->
                                if (c.id == course.id) c.copy(hasLike = !course.hasLike) else c
                            }
                        )
                    }
            } else {
                addToFavouritesUseCase(course.copy(hasLike = true))
                    .onSuccess {
                        _uiState.value = _uiState.value.copy(
                            courses = _uiState.value.courses.map { c ->
                                if (c.id == course.id) c.copy(hasLike = !course.hasLike) else c
                            }
                        )
                    }
            }
        }
    }

}