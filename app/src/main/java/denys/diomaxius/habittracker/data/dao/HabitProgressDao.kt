package denys.diomaxius.habittracker.data.dao

import androidx.room.*
import denys.diomaxius.habittracker.data.model.HabitProgressEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface HabitProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(habitProgress: HabitProgressEntity)

    @Query("SELECT * FROM habit_progress WHERE habitId = :habitId ORDER BY date ASC")
    fun getProgressByHabit(habitId: Int): Flow<List<HabitProgressEntity>>

    @Query("SELECT COUNT(*) FROM habit_progress WHERE habitId = :habitId AND date = :date AND isCompleted = 1")
    suspend fun checkCurrentDate(habitId: Int, date: LocalDate): Int

    @Query("SELECT * FROM habit_progress ORDER BY date ASC")
    fun getAllProgress(): Flow<List<HabitProgressEntity>>

    @Delete
    suspend fun deleteProgress(habitProgress: HabitProgressEntity)
}