package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.domain.repository.YearStorageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveYearsUseCase @Inject constructor(
    private val yearStorageRepository: YearStorageRepository
){
    operator fun invoke(): Flow<List<Int>> = yearStorageRepository.yearsFlow
}