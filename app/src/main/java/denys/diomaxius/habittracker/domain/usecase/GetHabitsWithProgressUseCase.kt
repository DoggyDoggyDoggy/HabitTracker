package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.data.repository.HabitProgressRepositoryImpl
import denys.diomaxius.habittracker.data.repository.HabitRepositoryImpl
import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.model.HabitProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetHabitsWithProgressUseCase @Inject constructor(
    private val habitRepositoryImpl: HabitRepositoryImpl,
    private val habitProgressRepositoryImpl: HabitProgressRepositoryImpl
) {
    fun execute(year: Int): Flow<Pair<List<Habit>, Map<Int, List<HabitProgress>>>> {
        return combine(
            habitRepositoryImpl.getAllHabits(year),
            habitProgressRepositoryImpl.getAllProgress()
        ) { habits, progress ->
            habits to progress.groupBy { it.habitId }
        }
    }
}
