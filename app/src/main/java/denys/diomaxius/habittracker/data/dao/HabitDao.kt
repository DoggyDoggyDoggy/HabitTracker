package denys.diomaxius.habittracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import denys.diomaxius.habittracker.data.model.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)

    @Query("SELECT * FROM habits WHERE year =:year ORDER BY position ASC")
    fun getAllHabits(year: Int) : Flow<List<Habit>>

    @Query("SELECT * FROM habits WHERE id = :habitId")
    suspend fun getHabitById(habitId: Int) : Habit

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Update
    suspend fun updateHabits(habits: List<Habit>)

    @Update
    suspend fun updateHabit(habits: Habit)
}