package denys.diomaxius.habittracker.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import denys.diomaxius.habittracker.R
import denys.diomaxius.habittracker.navigation.Screen
import denys.diomaxius.habittracker.ui.components.CustomPagerIndicator
import denys.diomaxius.habittracker.ui.theme.OnBoardingTypography

@Composable
fun OnBoardingScreen(
    navHostController: NavHostController
) {
    val slides = listOf<@Composable () -> Unit>(
        { FirstSlide() },
        { SecondSlide() },
        { ThirdSlide() },
        { FourthSlide() },
        { FinalSlide(navHostController = navHostController) }
    )

    val pagerState = rememberPagerState { slides.size }

    Content(
        pagerState = pagerState,
        slides = slides
    )
}

@Composable
fun Content(
    pagerState: PagerState,
    slides: List<@Composable () -> Unit>,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState
        ) {
            slides[it]()
        }

        CustomPagerIndicator(
            index = pagerState.currentPage,
            size = slides.size
        )
    }
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
        ) {
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
        ) {
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

@Composable
fun SecondSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3D5CA)
            )
        ) {

            Image(
                modifier = Modifier.padding(12.dp),
                painter = painterResource(id = R.drawable.topbar_example),
                contentDescription = "Image",
                contentScale = ContentScale.FillWidth
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        Icon(
            modifier = Modifier
                .scale(2f)
                .padding(bottom = 6.dp),
            imageVector = Icons.Default.Add,
            contentDescription = "Add icon"
        )

        Text(
            style = OnBoardingTypography.bodyMedium,
            text = "Here, you can add a new habit to your list and start tracking it right away."
        )

        Spacer(modifier = Modifier.height(60.dp))

        Icon(
            modifier = Modifier
                .scale(1.5f)
                .padding(bottom = 6.dp),
            imageVector = Icons.Default.Edit,
            contentDescription = "Add icon"
        )

        Text(
            style = OnBoardingTypography.bodyMedium,
            text = "In this tab, you can edit your existing habits, delete the ones you no longer need, and rearrange their order."
        )
    }
}

@Composable
fun ThirdSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(45.dp))

        Text(
            modifier = Modifier,
            style = OnBoardingTypography.bodyMedium,
            text = "On this tab, you can track your habit progress throughout the current year. When the new year begins, your progress will be archived, giving you a fresh start."
        )

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = R.drawable.annual_example),
            contentDescription = "Image",
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun FourthSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(110.dp))

        Text(
            modifier = Modifier,
            style = OnBoardingTypography.bodyMedium,
            text = "Use this tab to follow your weekly habit progress. Each day shows your completed tasks, while today’s view keeps you updated on what’s still in progress."
        )

        Spacer(modifier = Modifier.height(45.dp))

        Image(
            painter = painterResource(id = R.drawable.weekly_example),
            contentDescription = "Image",
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun FinalSlide(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            style = OnBoardingTypography.titleMedium,
            text = "Ready to take control of your habits?"
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            navHostController.navigate(Screen.MainScreen.route) {
                navHostController.graph.startDestinationRoute?.let { route ->
                    popUpTo(route) { inclusive = true }
                }
                launchSingleTop = true
            }
        }
        ) {
            Text(
                text = "Let's Go!"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFirstSlide() {
    FirstSlide()
}

@Preview(showBackground = true)
@Composable
fun PreviewSecondSlide() {
    SecondSlide()
}

@Preview(showBackground = true)
@Composable
fun PreviewFifthSlide() {
    ThirdSlide()
}

@Preview(showBackground = true)
@Composable
fun PreviewFourthSlide() {
    FourthSlide()
}

