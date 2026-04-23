package dev.dmil.coursesapp.feature.home.domain.usecase

import dev.dmil.coursesapp.core.domain.model.Course
import dev.dmil.coursesapp.core.domain.repository.CourseRepository
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val repository: CourseRepository
) {

    suspend operator fun invoke(): Result<List<Course>> {
        return repository.getCourses()
    }

}