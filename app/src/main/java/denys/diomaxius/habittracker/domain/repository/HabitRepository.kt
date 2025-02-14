package denys.diomaxius.habittracker.domain.repository

import denys.diomaxius.habittracker.data.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    suspend fun insertHabit(habit: Habit)
    suspend fun getHabitById(habitId: Int): Habit
    fun getAllHabits(year: Int): Flow<List<Habit>>
    suspend fun deleteHabit(habit: Habit)
    suspend fun updateHabits(habits: List<Habit>)
}