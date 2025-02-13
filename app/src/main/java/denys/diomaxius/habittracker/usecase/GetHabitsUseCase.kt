package denys.diomaxius.habittracker.usecase

import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress
import denys.diomaxius.habittracker.data.repository.HabitProgressRepository
import denys.diomaxius.habittracker.data.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetHabitsUseCase @Inject constructor(
    private val habitRepository: HabitRepository,
    private val habitProgressRepository: HabitProgressRepository
) {
    fun execute(year: Int): Flow<Pair<List<Habit>, Map<Int, List<HabitProgress>>>> {
        return combine(
            habitRepository.getAllHabits(year),
            habitProgressRepository.getAllProgress()
        ) { habits, progress ->
            habits to progress.groupBy { it.habitId }
        }
    }
}
