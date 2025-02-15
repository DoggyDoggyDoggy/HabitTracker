package denys.diomaxius.habittracker.domain.repository


import denys.diomaxius.habittracker.domain.model.HabitProgress
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface HabitProgressRepository {
    suspend fun insertProgress(habitProgress: HabitProgress)
    fun getProgressByHabit(habitId: Int): Flow<List<HabitProgress>>
    fun getAllProgress(): Flow<List<HabitProgress>>
    suspend fun checkCurrentDate(habitId: Int, date: LocalDate): Int
    suspend fun deleteProgress(habitProgress: HabitProgress)
}