package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.domain.repository.YearStorageRepository
import javax.inject.Inject

class AddYearUseCase @Inject constructor(
    private val yearStorageRepository: YearStorageRepository
){
    suspend operator fun invoke(year: Int) {
        yearStorageRepository.addYear(year)
    }
}