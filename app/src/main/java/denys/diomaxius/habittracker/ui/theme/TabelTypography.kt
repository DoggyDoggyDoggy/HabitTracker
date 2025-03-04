package denys.diomaxius.habittracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val TableTypography = Typography(
    //Habit table name
    titleSmall = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        shadow = Shadow(Color.Black, Offset(1f, 1f), blurRadius = 1.5f),
        fontFamily = Ubuntu
    ),
    //Number for Streak
    titleMedium = TextStyle(
        fontSize = 20.sp,
        fontFamily = Ubuntu,
        fontWeight = FontWeight.Bold
    ),

    //Habit table description
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = Ubuntu
    ),
)