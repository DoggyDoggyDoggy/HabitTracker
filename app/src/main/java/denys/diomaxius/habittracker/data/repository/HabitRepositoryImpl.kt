package denys.diomaxius.habittracker.data.repository

import denys.diomaxius.habittracker.data.dao.HabitDao
import denys.diomaxius.habittracker.data.model.HabitEntity
import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val dao: HabitDao
) : HabitRepository {
    override suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(HabitEntity.fromDomain(habit))
    }

    override suspend fun getHabitById(habitId: Int): Habit {
        return dao.getHabitById(habitId).toDomain()
    }

    override fun getAllHabits(year: Int): Flow<List<Habit>> {
        return dao.getAllHabits(year).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun deleteHabit(habit: Habit) {
        dao.deleteHabit(HabitEntity.fromDomain(habit))
    }

    override suspend fun updateHabits(habits: List<Habit>) {
        val entities = habits.map { HabitEntity.fromDomain(it) }
        dao.updateHabits(entities)
    }

    override suspend fun updateHabit(habit: Habit) {
        dao.updateHabit(HabitEntity.fromDomain(habit))
    }
}