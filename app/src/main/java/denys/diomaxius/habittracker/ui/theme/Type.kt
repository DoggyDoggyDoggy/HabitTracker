package denys.diomaxius.habittracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val Typography = Typography(
    //Main screen "No habits"
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    //Main screen "No habit description"
    bodyLarge = TextStyle(
        fontSize = 14.sp
    ),
    //Main screen button
    labelMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    //Habit table name
    titleSmall = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),
    //Habit table description
    bodySmall = TextStyle(
        fontSize = 14.sp
    )
)