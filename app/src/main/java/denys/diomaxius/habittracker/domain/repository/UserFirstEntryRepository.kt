package denys.diomaxius.habittracker.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserFirstEntryRepository {
    val firstEntry: Flow<Boolean>
    suspend fun registerFirstEntry()
}