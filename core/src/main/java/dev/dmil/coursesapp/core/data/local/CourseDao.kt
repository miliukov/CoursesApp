package dev.dmil.coursesapp.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Query("SELECT * FROM courses")
    fun getFavourites(): Flow<List<CourseEntity>>

    @Insert
    suspend fun addToFavourites(courseEntity: CourseEntity)

    @Query("DELETE FROM courses WHERE id = :id")
    suspend fun removeFromFavourites(id: Int)

}