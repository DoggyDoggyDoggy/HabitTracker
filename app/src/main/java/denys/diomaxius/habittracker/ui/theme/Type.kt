package denys.diomaxius.habittracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import denys.diomaxius.habittracker.R

val Ubuntu = FontFamily(
    Font(R.font.ubuntu_medium, FontWeight.Medium),
    Font(R.font.ubuntu_bold, FontWeight.Bold),
    Font(R.font.ubuntu_regular, FontWeight.Normal),
    Font(R.font.ubuntu_light, FontWeight.Light)
)

val DotSans = FontFamily(
    Font(R.font.dotsans_regular, FontWeight.Normal),
    Font(R.font.dotsans_medium, FontWeight.Medium),
    Font(R.font.dotsans_bold, FontWeight.Bold),
    Font(R.font.dotsans_extra_bold, FontWeight.ExtraBold)
)

val Roboto = FontFamily(
    Font(R.font.roboto_condensed_bold, FontWeight.Bold)
)

val Typography = Typography(
    //Loading text
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = Roboto,
        fontSize = 56.sp
    ),
    //DeleteDialog title
    displayMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = DotSans,
        fontSize = 32.sp
    ),
    //DeleteDialog text
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = DotSans,
        fontSize = 16.sp
    ),
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
        shadow = Shadow(Color.Black, Offset(1f, 1f), blurRadius = 1.5f),
        fontFamily = Ubuntu
    ),
    //Habit table description
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = Ubuntu
    ),
    //Theme description
    labelSmall = TextStyle(
        fontSize = 10.sp,
        fontFamily = DotSans,
        fontWeight = FontWeight.Bold
    )
)