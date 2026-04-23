package dev.dmil.coursesapp.core.data.repository

import dev.dmil.coursesapp.core.data.local.CourseDao
import dev.dmil.coursesapp.core.data.mapper.toDomain
import dev.dmil.coursesapp.core.data.mapper.toEntity
import dev.dmil.coursesapp.core.data.remote.CourseApi
import dev.dmil.coursesapp.core.domain.model.Course
import dev.dmil.coursesapp.core.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CourseRepositoryImpl(
    private val api: CourseApi,
    private val dao: CourseDao
): CourseRepository {

    override suspend fun getCourses(): Result<List<Course>> {
        return runCatching {
            api.getCourses().courses.map { it.toDomain() }
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