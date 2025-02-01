package denys.diomaxius.habittracker.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "habit_progress",
    foreignKeys = [ForeignKey(
        entity = Habit::class,
        parentColumns = ["id"],
        childColumns = ["habitId"],
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["habitId", "date"],
    indices = [Index("habitId")]
)
data class HabitProgress(
    val habitId: Int,
    val date: String,
    val isCompleted: Boolean
)