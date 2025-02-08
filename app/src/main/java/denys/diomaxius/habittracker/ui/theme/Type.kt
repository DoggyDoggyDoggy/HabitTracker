package denys.diomaxius.habittracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
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
        fontWeight = FontWeight.Bold,
        shadow = Shadow(Color.Black, Offset(1f,1f), blurRadius = 1.5f)
    ),
    //Habit table description
    bodySmall = TextStyle(
        fontSize = 14.sp,
        //shadow = Shadow(Color.Black, Offset(1.5f,1.5f), blurRadius = 1.5f)
    )
)