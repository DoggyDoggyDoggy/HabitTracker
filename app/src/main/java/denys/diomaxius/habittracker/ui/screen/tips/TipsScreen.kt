package denys.diomaxius.habittracker.ui.screen.tips

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import denys.diomaxius.habittracker.data.constants.Tips
import denys.diomaxius.habittracker.ui.components.topbar.TopBar
import denys.diomaxius.habittracker.ui.components.ViewSwitcher
import denys.diomaxius.habittracker.ui.theme.TipsTypography

@Composable
fun TipsScreen(
    navHostController: NavHostController
) {
    val slides = listOf<@Composable () -> Unit>(
        { FirstSlide() },
        { SecondSlide() },
        { ThirdSlide() },
        { FourthSlide() }
    )
    val pagerState = rememberPagerState { slides.size }

    Scaffold(
        topBar = {
            TopBar(
                navHostController = navHostController
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            slides = slides,
            pagerState = pagerState,
            navHostController = navHostController
        )
    }

}

@Composable
fun Content(
    modifier: Modifier,
    slides: List<@Composable () -> Unit>,
    pagerState: PagerState,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ViewSwitcher(navHostController = navHostController)

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.Top
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
        modifier = Modifier
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = Tips.title[0],
                style = TipsTypography.titleLarge
            )
        }
        repeat(3) {
            Row {
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    style = TipsTypography.headlineMedium,
                    text = Tips.firstSlidePoint[it]
                )
            }
            Row {
                Text(
                    style = TipsTypography.bodyMedium,
                    text = Tips.firstSlideText[it]
                )
            }
        }
    }
}

@Composable
fun SecondSlide() {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = Tips.title[1],
                style = TipsTypography.titleLarge
            )
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            repeat(4) { subTitleScope ->
                Row {
                    Text(
                        modifier = Modifier.padding(top = 12.dp, bottom = 6.dp),
                        text = Tips.subTitle[subTitleScope],
                        style = TipsTypography.headlineLarge
                    )
                }
                repeat(2) {
                    Row {
                        Text(
                            style = TipsTypography.headlineMedium,
                            text = Tips.secondSlidePoint[subTitleScope * 2 + it]
                        )
                    }
                    Row {
                        Text(
                            style = TipsTypography.bodyMedium,
                            text = Tips.secondSlideText[subTitleScope * 2 + it]
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ThirdSlide() {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = Tips.title[2],
                style = TipsTypography.titleLarge
            )
        }
        repeat(5) {
            Row {
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    style = TipsTypography.headlineMedium,
                    text = Tips.thirdSlidePoint[it]
                )
            }
            Row {
                Text(
                    style = TipsTypography.bodyMedium,
                    text = Tips.thirdSlideText[it]
                )
            }
        }
    }
}

@Composable
fun FourthSlide() {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = Tips.title[3],
                style = TipsTypography.titleLarge
            )
        }
        repeat(3) {
            Row {
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    style = TipsTypography.headlineMedium,
                    text = Tips.fourthSlidePoint[it]
                )
            }
            Row {
                Text(
                    style = TipsTypography.bodyMedium,
                    text = Tips.fourthSlideText[it]
                )
            }
        }
    }
}

@Composable
fun CustomPagerIndicator(
    index: Int,
    size: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(size){
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(
                        if (index == it) Color(0xFFB2E9AC) else Color.Unspecified
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = CircleShape
                    )
                    .size(16.dp)
            )
        }
    }
}