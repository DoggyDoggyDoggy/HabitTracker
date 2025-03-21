package denys.diomaxius.habittracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import denys.diomaxius.habittracker.R

val Ubuntu = FontFamily(
    Font(R.font.ubuntu_medium, FontWeight.Medium),
    Font(R.font.ubuntu_bold, FontWeight.Bold),
    Font(R.font.ubuntu_regular, FontWeight.Normal),
    Font(R.font.ubuntu_light, FontWeight.Light),
    Font(R.font.ubuntu_light_italic, FontWeight.Light, FontStyle.Italic)
)

val DotSans = FontFamily(
    Font(R.font.dotsans_regular, FontWeight.Normal),
    Font(R.font.dotsans_medium, FontWeight.Medium),
    Font(R.font.dotsans_bold, FontWeight.Bold),
    Font(R.font.dotsans_extra_bold, FontWeight.ExtraBold)
)

val Roboto = FontFamily(
    Font(R.font.roboto_condensed_bold, FontWeight.Bold),
    Font(R.font.roboto_condensed_medium, FontWeight.Medium),
    Font(R.font.roboto_condensed_regular, FontWeight.Normal),
    Font(R.font.roboto_condensed_semibold, FontWeight.SemiBold),
    Font(R.font.roboto_condensed_extrabold, FontWeight.ExtraBold)
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
    //Theme description
    labelSmall = TextStyle(
        fontSize = 10.sp,
        fontFamily = DotSans,
        fontWeight = FontWeight.Bold
    ),
    //AppNameTopBar
    titleMedium = TextStyle(
        fontSize = 18.sp,
        fontFamily = Ubuntu,
        fontWeight = FontWeight.Bold
    )
)