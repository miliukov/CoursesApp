package dev.dmil.coursesapp.core.domain.repository

import dev.dmil.coursesapp.core.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    suspend fun getCourses(): Result<List<Course>>

    suspend fun getFavourites(): Flow<List<Course>>

    suspend fun addToFavourites(course: Course): Result<Unit>

    suspend fun removeFromFavourites(id: Int): Result<Unit>

}