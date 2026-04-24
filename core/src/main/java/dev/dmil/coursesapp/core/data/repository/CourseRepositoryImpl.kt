package dev.dmil.coursesapp.core.data.repository

import dev.dmil.coursesapp.core.data.local.CourseDao
import dev.dmil.coursesapp.core.data.mapper.toDomain
import dev.dmil.coursesapp.core.data.mapper.toEntity
import dev.dmil.coursesapp.core.data.remote.CourseApi
import dev.dmil.coursesapp.core.domain.model.Course
import dev.dmil.coursesapp.core.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val api: CourseApi,
    private val dao: CourseDao
): CourseRepository {

    override suspend fun getCourses(): Result<List<Course>> {
        return runCatching {
            val favouriteIds = dao.getFavourites().first().map { it.id }.toSet()
            api.getCourses().courses.map { dto ->
                dto.toDomain().copy(hasLike = dto.id in favouriteIds)
            }
        }
    }

    override fun getFavourites(): Flow<List<Course>> {
        return dao.getFavourites().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addToFavourites(course: Course): Result<Unit> {
        return runCatching {
            dao.addToFavourites(course.toEntity())
        }
    }

    override suspend fun removeFromFavourites(id: Int): Result<Unit> {
        return runCatching {
            dao.removeFromFavourites(id)
        }
    }
}