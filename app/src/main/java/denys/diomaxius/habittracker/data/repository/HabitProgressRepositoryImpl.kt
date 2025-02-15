package denys.diomaxius.habittracker.data.repository

import denys.diomaxius.habittracker.data.dao.HabitProgressDao
import denys.diomaxius.habittracker.data.model.HabitProgressEntity
import denys.diomaxius.habittracker.domain.model.HabitProgress
import denys.diomaxius.habittracker.domain.repository.HabitProgressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class HabitProgressRepositoryImpl @Inject constructor(
    private val dao: HabitProgressDao
) : HabitProgressRepository{
    override suspend fun insertProgress(habitProgress: HabitProgress) {
        dao.insertProgress(HabitProgressEntity.fromDomain(habitProgress))
    }

    override fun getProgressByHabit(habitId: Int): Flow<List<HabitProgress>> {
        return dao.getProgressByHabit(habitId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getAllProgress(): Flow<List<HabitProgress>> {
        return dao.getAllProgress().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun checkCurrentDate(habitId: Int, date: LocalDate): Int {
        return dao.checkCurrentDate(habitId, date)
    }

    override suspend fun deleteProgress(habitProgress: HabitProgress) {
        dao.deleteProgress(HabitProgressEntity.fromDomain(habitProgress))
    }
}