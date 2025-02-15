package denys.diomaxius.habittracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import denys.diomaxius.habittracker.domain.model.Habit

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val category: String,
    val colorTheme: Int,
    val iconId: Int,
    val position: Int = 99,
    val year: Int
) {
    fun toDomain(): Habit {
        return Habit(id, name, description, category, colorTheme, iconId, position, year)
    }

    companion object {
        fun fromDomain(habit: Habit): HabitEntity {
            return HabitEntity(
                habit.id, habit.name, habit.description, habit.category,
                habit.colorTheme, habit.iconId, habit.position, habit.year
            )
        }
    }
}