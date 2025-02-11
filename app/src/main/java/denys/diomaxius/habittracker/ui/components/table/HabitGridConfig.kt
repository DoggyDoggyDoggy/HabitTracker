package denys.diomaxius.habittracker.ui.components.table

data class HabitGridConfig(
    val spacing: Int = 4,
    val boxSize: Int = 16,
    val fixHeight: Int = 1,
    val days: Int = 365,
    val rows: Int = 7,
    val density: Float
) {
    private fun boxSizePx(): Int {
        return (density * boxSize).toInt()
    }

    fun getInitialScrollPosition(month: Int): Int {
        return when (month) {
            4 -> (boxSizePx() + spacing) * 4
            5 -> (boxSizePx() + spacing) * 10
            6 -> (boxSizePx() + spacing) * 14
            7 -> (boxSizePx() + spacing) * 18
            8 -> (boxSizePx() + spacing) * 22
            9 -> (boxSizePx() + spacing) * 27
            10 -> (boxSizePx() + spacing) * 31
            11 -> (boxSizePx() + spacing) * 33
            12 -> (boxSizePx() + spacing) * 33
            else -> 0
        }
    }

    fun getLayoutWidth(): Int {
        return (((boxSize * density) + spacing) * days / rows + ((boxSize * density) + spacing)).toInt()
    }

    fun getLayoutHeight(): Int {
        return (((boxSize * density) + spacing - fixHeight) * rows).toInt()
    }
}