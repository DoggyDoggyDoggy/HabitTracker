package denys.diomaxius.habittracker.data.repository

import denys.diomaxius.habittracker.data.dao.HabitProgressDao
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.domain.repository.HabitProgressRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class HabitProgressRepositoryImpl @Inject constructor(
    private val dao: HabitProgressDao
) : HabitProgressRepository{
    override suspend fun insertProgress(habitProgress: HabitProgress) {
        dao.insertProgress(habitProgress)
    }

    override fun getProgressByHabit(habitId: Int): Flow<List<HabitProgress>> {
        return dao.getProgressByHabit(habitId)
    }

    override fun getAllProgress(): Flow<List<HabitProgress>> {
        return dao.getAllProgress()
    }

    override suspend fun checkCurrentDate(habitId: Int, date: LocalDate): Int {
        return dao.checkCurrentDate(habitId, date)
    }

    override suspend fun deleteProgress(habitProgress: HabitProgress) {
        dao.deleteProgress(habitProgress)
    }
}