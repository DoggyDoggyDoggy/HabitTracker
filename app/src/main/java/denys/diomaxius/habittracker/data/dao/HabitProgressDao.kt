package denys.diomaxius.habittracker.data.dao

import androidx.room.*
import denys.diomaxius.habittracker.data.model.HabitProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: HabitProgress)

    @Query("SELECT * FROM habit_progress WHERE habitId = :habitId ORDER BY date ASC")
    fun getProgressByHabit(habitId: Int): Flow<List<HabitProgress>>

    @Query("SELECT * FROM habit_progress ORDER BY date ASC")
    fun getAllProgress(): Flow<List<HabitProgress>>

    @Delete
    suspend fun deleteProgress(progress: HabitProgress)
}