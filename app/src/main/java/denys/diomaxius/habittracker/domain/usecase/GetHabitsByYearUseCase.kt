package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.domain.model.Habit
import denys.diomaxius.habittracker.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHabitsByYearUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    operator fun invoke(year: Int): Flow<List<Habit>> = habitRepository.getAllHabits(year)
}