package denys.diomaxius.habittracker.data.repository

import denys.diomaxius.habittracker.data.dao.HabitDao
import denys.diomaxius.habittracker.data.dao.HabitProgressDao
import denys.diomaxius.habittracker.data.model.HabitProgress
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HabitProgressRepository @Inject constructor(
    private val dao: HabitProgressDao
) {
    suspend fun insertProgress(habitProgress: HabitProgress) {
        dao.insertProgress(habitProgress)
    }

    fun getProgressByHabit(habitId: Int) {
        dao.getProgressByHabit(habitId)
    }
    fun getAllProgress(): Flow<List<HabitProgress>> {
        return dao.getAllProgress()
    }

    suspend fun deleteProgress(habitProgress: HabitProgress) {
        dao.deleteProgress(habitProgress)
    }
}