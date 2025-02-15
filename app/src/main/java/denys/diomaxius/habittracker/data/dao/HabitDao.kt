package denys.diomaxius.habittracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import denys.diomaxius.habittracker.data.model.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity)

    @Query("SELECT * FROM habits WHERE year =:year ORDER BY position ASC")
    fun getAllHabits(year: Int) : Flow<List<HabitEntity>>

    @Query("SELECT * FROM habits WHERE id = :habitId")
    suspend fun getHabitById(habitId: Int) : HabitEntity

    @Delete
    suspend fun deleteHabit(habit: HabitEntity)

    @Update
    suspend fun updateHabits(habits: List<HabitEntity>)

    @Update
    suspend fun updateHabit(habits: HabitEntity)
}