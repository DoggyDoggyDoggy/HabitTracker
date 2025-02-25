package denys.diomaxius.habittracker.domain.usecase

import denys.diomaxius.habittracker.domain.repository.UserFirstEntryRepository
import javax.inject.Inject

class RegisterFirstEntryUseCase @Inject constructor(
    private val userFirstEntryRepository: UserFirstEntryRepository
) {
    suspend operator fun invoke() {
        userFirstEntryRepository.registerFirstEntry()
    }
}