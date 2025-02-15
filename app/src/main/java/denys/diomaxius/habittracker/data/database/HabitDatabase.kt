package denys.diomaxius.habittracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import denys.diomaxius.habittracker.data.dao.HabitDao
import denys.diomaxius.habittracker.data.dao.HabitProgressDao
import denys.diomaxius.habittracker.data.model.HabitEntity
import denys.diomaxius.habittracker.data.model.HabitProgressEntity

@Database(
    entities = [HabitEntity::class, HabitProgressEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class HabitDatabase : RoomDatabase() {
    companion object {
        const val NAME = "HABIT_DB"
    }
    abstract fun habitDao(): HabitDao
    abstract fun habitProgressDao(): HabitProgressDao
}