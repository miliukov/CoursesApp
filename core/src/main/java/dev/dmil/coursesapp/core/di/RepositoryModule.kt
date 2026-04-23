package dev.dmil.coursesapp.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dmil.coursesapp.core.data.repository.CourseRepositoryImpl
import dev.dmil.coursesapp.core.domain.repository.CourseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsCourseRepository(impl: CourseRepositoryImpl): CourseRepository

}