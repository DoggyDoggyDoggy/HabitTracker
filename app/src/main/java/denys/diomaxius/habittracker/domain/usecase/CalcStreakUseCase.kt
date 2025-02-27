package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.domain.model.HabitProgress
import denys.diomaxius.habittracker.domain.repository.HabitProgressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CalcStreakUseCase @Inject constructor(
    private val habitProgressRepository: HabitProgressRepository
) {
    operator fun invoke(habitId: Int): Flow<Int> {
        return habitProgressRepository.getProgressByHabit(habitId)
            .map { progressList ->
                calculateStreak(progressList)
            }
    }

    private fun calculateStreak(progressList: List<HabitProgress>) : Int {
        TODO("Not yet implemented")
    }

}