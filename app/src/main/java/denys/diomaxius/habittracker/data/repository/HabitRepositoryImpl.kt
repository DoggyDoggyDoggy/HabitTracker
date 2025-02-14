package denys.diomaxius.habittracker.data.repository

import denys.diomaxius.habittracker.data.dao.HabitDao
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val dao: HabitDao
) : HabitRepository {
    override suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(habit)
    }

    override suspend fun getHabitById(habitId: Int): Habit {
        return dao.getHabitById(habitId)
    }

    override fun getAllHabits(year: Int): Flow<List<Habit>> {
        return dao.getAllHabits(year)
    }

    override suspend fun deleteHabit(habit: Habit) {
        dao.deleteHabit(habit)
    }

    override suspend fun updateHabits(habits: List<Habit>) {
        dao.updateHabits(habits)
    }

    override suspend fun updateHabit(habit: Habit) {
        dao.updateHabit(habit)
    }
}