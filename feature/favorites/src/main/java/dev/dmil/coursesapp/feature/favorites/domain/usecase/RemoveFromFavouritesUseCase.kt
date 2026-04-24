package dev.dmil.coursesapp.feature.favorites.domain.usecase

import dev.dmil.coursesapp.core.domain.repository.CourseRepository
import javax.inject.Inject

class RemoveFromFavouritesUseCase @Inject constructor(
    private val repository: CourseRepository
) {

    suspend operator fun invoke(id: Int): Result<Unit> {
        return repository.removeFromFavourites(id)
    }

}