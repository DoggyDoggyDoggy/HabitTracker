package denys.diomaxius.habittracker.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import denys.diomaxius.habittracker.domain.model.HabitProgress
import java.time.LocalDate

@Entity(
    tableName = "habit_progress",
    foreignKeys = [ForeignKey(
        entity = HabitEntity::class,
        parentColumns = ["id"],
        childColumns = ["habitId"],
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["habitId", "date"],
    indices = [Index("habitId")]
)
data class HabitProgressEntity(
    val habitId: Int,
    val date: LocalDate,
    val isCompleted: Boolean
) {
    fun toDomain(): HabitProgress {
        return HabitProgress(habitId, date, isCompleted)
    }

    companion object {
        fun fromDomain(progress: HabitProgress): HabitProgressEntity {
            return HabitProgressEntity(progress.habitId, progress.date, progress.isCompleted)
        }
    }
}