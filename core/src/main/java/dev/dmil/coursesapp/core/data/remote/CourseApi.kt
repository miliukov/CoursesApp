package dev.dmil.coursesapp.core.data.remote

import dev.dmil.coursesapp.core.data.remote.dto.CoursesResponseDto
import retrofit2.http.GET

interface CourseApi {

    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun getCourses(): CoursesResponseDto

}