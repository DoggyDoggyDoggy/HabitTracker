package denys.diomaxius.habittracker.domain.model

data class Habit(
    val id: Int = 0,
    val name: String,
    val description: String,
    val category: String,
    val colorTheme: Int,
    val iconId: Int,
    val position: Int = 99,
    val year: Int
)
