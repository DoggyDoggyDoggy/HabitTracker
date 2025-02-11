package denys.diomaxius.habittracker.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.yearsDataStore by preferencesDataStore(name = "years_store")

class DataStoreManager(private val context: Context) {
    private val yearsKey = stringPreferencesKey("years_json")
    private val gson = Gson()

    val yearsFlow: Flow<List<Int>> = context.yearsDataStore.data
        .map { preferences ->
            val json = preferences[yearsKey] ?: "[]"
            gson.fromJson(json, Array<Int>::class.java)?.toList() ?: emptyList()
        }

    suspend fun addYear(year: Int) {
        context.yearsDataStore.edit { preferences ->
            val json = preferences[yearsKey] ?: "[]"
            val currentList = gson.fromJson(json, Array<Int>::class.java)?.toMutableList() ?: mutableListOf()
            if (!currentList.contains(year)) {
                currentList.add(year)
            }
            preferences[yearsKey] = gson.toJson(currentList)
        }
    }
}