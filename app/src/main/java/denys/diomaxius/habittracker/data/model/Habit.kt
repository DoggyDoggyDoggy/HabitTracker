package denys.diomaxius.habittracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val category: String,
    val colorTheme: Int,
    val iconId: Int,
    val position: Int = 99,
    val year: Int
)