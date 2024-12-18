package dev.kuhuk.moodboarddaily.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.kuhuk.moodboarddaily.Screen
import dev.kuhuk.moodboarddaily.screens.boards.FitnessTrackerBoard
import dev.kuhuk.moodboarddaily.screens.boards.GratitudeJournalBoard
import dev.kuhuk.moodboarddaily.screens.boards.MorningRoutineBoard
import dev.kuhuk.moodboarddaily.screens.boards.ReadingCornerBoard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val boardColors = listOf(
        Color(0xFFFFF8E1), // Pastel Amber
        Color(0xFFE1F5FE), // Pastel Light Blue
        Color(0xFFE8F5E9), // Pastel Green
        Color(0xFFFCE4EC)  // Pastel Pink
    )

    val boards: List<@Composable (Color) -> Unit> = listOf(
        { color -> MorningRoutineBoard(color) },
        { color -> FitnessTrackerBoard(color) },
        { color -> ReadingCornerBoard(color) },
        { color -> GratitudeJournalBoard(color) }
    )

    val pagerState = rememberPagerState(pageCount = { boards.size })

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("MoodBoard Daily") }, actions = {
                IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                    Icon(Icons.Default.Settings, contentDescription = "Settings")
                }
            })
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            beyondViewportPageCount = 1,
            contentPadding = PaddingValues(0.dp)
        ) { page ->
            boards[page](boardColors[page % boardColors.size])
        }
    }
}