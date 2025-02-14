package denys.diomaxius.habittracker.data.repository

import denys.diomaxius.habittracker.data.datastore.DataStoreManager
import denys.diomaxius.habittracker.domain.repository.YearStorageRepository
import javax.inject.Inject

class YearStorageRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : YearStorageRepository {
    override val yearsFlow = dataStoreManager.yearsFlow

    override suspend fun addYear(year: Int) {
        dataStoreManager.addYear(year)
    }
}