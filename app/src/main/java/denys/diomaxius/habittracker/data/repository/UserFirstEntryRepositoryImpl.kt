package denys.diomaxius.habittracker.data.repository

import denys.diomaxius.habittracker.data.datastore.OnboardingDataStoreManager
import denys.diomaxius.habittracker.domain.repository.UserFirstEntryRepository
import javax.inject.Inject

class UserFirstEntryRepositoryImpl @Inject constructor(
    private val onboardingDataStoreManager: OnboardingDataStoreManager
) : UserFirstEntryRepository {
    override val firstEntry = onboardingDataStoreManager.firstLaunch

    override suspend fun registerFirstEntry() {
        onboardingDataStoreManager.registerFirstEntry()
    }
}