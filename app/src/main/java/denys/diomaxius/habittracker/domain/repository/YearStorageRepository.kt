package denys.diomaxius.habittracker.domain.repository

import kotlinx.coroutines.flow.Flow

interface YearStorageRepository {
    val yearsFlow: Flow<List<Int>>
    suspend fun addYear(year: Int)
}