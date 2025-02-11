package denys.diomaxius.habittracker.data.repository

import denys.diomaxius.habittracker.data.datastore.DataStoreManager
import javax.inject.Inject

class YearStorageRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    val yearsFlow = dataStoreManager.yearsFlow

    suspend fun addYear(year: Int) {
        dataStoreManager.addYear(year)
    }
}