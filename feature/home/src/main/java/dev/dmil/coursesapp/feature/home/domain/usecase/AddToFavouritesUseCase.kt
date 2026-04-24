package dev.dmil.coursesapp.feature.home.domain.usecase

import dev.dmil.coursesapp.core.domain.model.Course
import dev.dmil.coursesapp.core.domain.repository.CourseRepository
import javax.inject.Inject

class AddToFavouritesUseCase @Inject constructor(
    private val repository: CourseRepository
) {

    suspend operator fun invoke(course: Course): Result<Unit> {
        return repository.addToFavourites(course)
    }

}