package denys.diomaxius.habittracker.data.repository

import denys.diomaxius.habittracker.data.dao.HabitDao
import denys.diomaxius.habittracker.data.model.Habit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HabitRepository @Inject constructor(
    private val dao: HabitDao
) {
    suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(habit)
    }

    suspend fun getHabitById(habitId: Int) : Habit {
        return dao.getHabitById(habitId)
    }

    fun getAllHabits(year: Int): Flow<List<Habit>> {
        return dao.getAllHabits(year)
    }

    suspend fun deleteHabit(habit: Habit) {
        dao.deleteHabit(habit)
    }

    suspend fun updateHabits(habits: List<Habit>) {
        dao.updateHabits(habits)
    }
}