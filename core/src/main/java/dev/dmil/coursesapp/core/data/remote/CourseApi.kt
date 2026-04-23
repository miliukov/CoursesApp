package dev.dmil.coursesapp.core.data.remote

import dev.dmil.coursesapp.core.data.remote.dto.CoursesResponseDto
import retrofit2.http.GET

interface CourseApi {

    @GET("")
    suspend fun getCourses(): CoursesResponseDto

}