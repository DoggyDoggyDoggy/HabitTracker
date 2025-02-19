package denys.diomaxius.habittracker.ui.screen.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import denys.diomaxius.habittracker.ui.theme.OnBoardingTypography

@Composable
fun OnBoardingScreen(

) {
    FirstSlide()
}

@Composable
fun FirstSlide() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                style = OnBoardingTypography.titleLarge,
                text = "Welcome to HabitTracker!"
            )

            Text(
                style = OnBoardingTypography.labelMedium,
                text = "Begin your journey to becoming the best version of yourself."
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier.padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                style = OnBoardingTypography.bodyMedium,
                text = "Build positive habits effortlessly, track your progress day by day, and reach for your dreams—all in one simple app."
            )

            Text(
                style = OnBoardingTypography.bodyMedium,
                text = buildAnnotatedString {
                    append(
                        "Start your journey "
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = OnBoardingTypography.bodyMedium.fontSize
                        )
                    ) {
                        append("today!")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFirstSlide() {
    FirstSlide()
}