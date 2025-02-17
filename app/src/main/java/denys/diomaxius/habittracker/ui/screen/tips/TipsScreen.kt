package denys.diomaxius.habittracker.ui.screen.tips

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import denys.diomaxius.habittracker.ui.components.TopBar
import denys.diomaxius.habittracker.ui.components.ViewSwitcher

@Composable
fun TipsScreen(
    navHostController : NavHostController
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
                navHostController = navHostController,
                habitListIsNotEmpty = false,
                showArchiveIcon = false
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
    navHostController : NavHostController
) {
    Column (
        modifier = modifier.fillMaxSize()
    ){
        ViewSwitcher(navHostController = navHostController)

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) {
            slides[it]()
        }
    }
}


@Composable
fun FirstSlide() {
    Column {
        Text(text = "Title")
        Row {
            Text(text = "Text")
        }
        Row {
            Text(text = "Text")
        }
        Row {
            Text(text = "Text")
        }
    }
}

@Composable
fun SecondSlide() {
    Column {
        Text(text = "Title")

        Text(text = "SubTitle")
        Row {
            Text(text = "Text")
        }
        Row {
            Text(text = "Text")
        }

        Text(text = "SubTitle")
        Row {
            Text(text = "Text")
        }
        Row {
            Text(text = "Text")
        }

        Text(text = "SubTitle")
        Row {
            Text(text = "Text")
        }
        Row {
            Text(text = "Text")
        }

        Text(text = "SubTitle")
        Row {
            Text(text = "Text")
        }
        Row {
            Text(text = "Text")
        }
    }
}

@Composable
fun ThirdSlide() {
    Column {
        Text(text = "Title")
        Row {
            Text(text = "Text")
        }
        Row {
            Text(text = "Text")
        }
        Row {
            Text(text = "Text")
        }
        Row {
            Text(text = "Text")
        }
        Row {
            Text(text = "Text")
        }
    }
}

@Composable
fun FourthSlide() {
    Column {
        Text(text = "Title")
        Row {
            Text(text = "Text")
        }
        Row {
            Text(text = "Text")
        }
        Row {
            Text(text = "Text")
        }
    }
}
