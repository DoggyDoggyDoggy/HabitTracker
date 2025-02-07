package denys.diomaxius.habittracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import denys.diomaxius.habittracker.data.model.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)

    @Query("SELECT * FROM habits")
    fun getAllHabits() : Flow<List<Habit>>

    @Query("SELECT * FROM habits WHERE id = :habitId")
    suspend fun getHabitById(habitId: Int) : Habit

    @Delete
    suspend fun deleteHabit(habit: Habit)
}