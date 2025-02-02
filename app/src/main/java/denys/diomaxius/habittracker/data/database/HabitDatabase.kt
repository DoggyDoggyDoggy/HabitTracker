package denys.diomaxius.habittracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import denys.diomaxius.habittracker.data.dao.HabitDao
import denys.diomaxius.habittracker.data.dao.HabitProgressDao
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.data.model.HabitProgress

@Database(
    entities = [Habit::class, HabitProgress::class],
    version = 1,
    exportSchema = false
)
abstract class HabitDatabase : RoomDatabase() {
    companion object {
        const val NAME = "HABIT_DB"
    }
    abstract fun habitDao(): HabitDao
    abstract fun habitProgressDao(): HabitProgressDao
}