package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.domain.repository.UserFirstEntryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckFirstLaunchUseCase @Inject constructor(
    private val userFirstEntryRepository: UserFirstEntryRepository
) {
    operator fun invoke(): Flow<Boolean> = userFirstEntryRepository.firstEntry
}