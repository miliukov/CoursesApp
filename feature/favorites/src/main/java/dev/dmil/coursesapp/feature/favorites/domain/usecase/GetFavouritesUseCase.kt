package dev.dmil.coursesapp.feature.favorites.domain.usecase

import dev.dmil.coursesapp.core.domain.model.Course
import dev.dmil.coursesapp.core.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val repository: CourseRepository
) {

    operator fun invoke(): Flow<List<Course>> {
        return repository.getFavourites()
    }

}