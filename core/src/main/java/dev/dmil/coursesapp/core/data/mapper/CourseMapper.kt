package dev.dmil.coursesapp.core.data.mapper

import dev.dmil.coursesapp.core.data.local.CourseEntity
import dev.dmil.coursesapp.core.data.remote.dto.CourseDto
import dev.dmil.coursesapp.core.domain.model.Course

fun CourseDto.toDomain(): Course = Course(
    id = this.id,
    title = this.title,
    text = this.text,
    price = this.price,
    rate = this.rate,
    startDate = this.startDate,
    hasLike = this.hasLike,
    publishDate = this.publishDate
)

fun CourseEntity.toDomain(): Course = Course(
    id = this.id,
    title = this.title,
    text = this.text,
    price = this.price,
    rate = this.rate,
    startDate = this.startDate,
    hasLike = this.hasLike,
    publishDate = this.publishDate
)

fun Course.toEntity(): CourseEntity = CourseEntity(
    id = this.id,
    title = this.title,
    text = this.text,
    price = this.price,
    rate = this.rate,
    startDate = this.startDate,
    hasLike = this.hasLike,
    publishDate = this.publishDate
)