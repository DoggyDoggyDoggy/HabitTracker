package denys.diomaxius.habittracker.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.onboardingDataStore by preferencesDataStore(name = "onboarding_store")

class OnboardingDataStoreManager(private val context: Context) {
    private val firstLaunchKey = booleanPreferencesKey("first_launch")

    val firstLaunch: Flow<Boolean> = context.onboardingDataStore.data
        .map { preferences ->
            preferences[firstLaunchKey] ?: false
        }

    suspend fun registerFirstEntry() {
        context.onboardingDataStore.edit { preferences ->
            preferences[firstLaunchKey] = true
        }
    }
}