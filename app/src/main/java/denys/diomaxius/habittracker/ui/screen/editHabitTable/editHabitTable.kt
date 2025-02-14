package denys.diomaxius.habittracker.ui.screen.editHabitTable

import android.view.View
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.HapticFeedbackConstantsCompat
import androidx.core.view.ViewCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.habittracker.data.model.Habit
import denys.diomaxius.habittracker.navigation.Screen
import denys.diomaxius.habittracker.data.constants.IconData
import denys.diomaxius.habittracker.data.constants.TableThemes
import sh.calvin.reorderable.ReorderableCollectionItemScope
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState

@Composable
fun EditHabitTable(
    viewModel: EditHabitTableViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val view = LocalView.current
    val habitList by viewModel.habitList.collectAsState()
    val lazyListState = rememberLazyListState()

    val reorderableLazyListState = rememberReorderableLazyListState(lazyListState) { from, to ->
        viewModel.reorderHabits(from.index, to.index)
        // Tactile feedback when moving
        ViewCompat.performHapticFeedback(
            view,
            HapticFeedbackConstantsCompat.SEGMENT_FREQUENT_TICK
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
    ) {
        items(habitList, key = { it.id }) { habit ->
            ReorderableItem(state = reorderableLazyListState, key = habit.id) {
                HabitTable(
                    habit = habit,
                    habitList = habitList,
                    onDeleteTable = { viewModel.deleteHabit(it) },
                    navHostController = navHostController,
                    view = view
                )
            }
        }
    }
}

@Composable
fun DeleteDialog(
    onDeleteTable: () -> Unit,
    toggleDeleteDialog: () -> Unit,
    lastTable: Boolean,
    prevPage: () -> Unit
) {
    AlertDialog(
        onDismissRequest = toggleDeleteDialog,
        title = {
            Text(
                text = "Delete the habit?",
                style = MaterialTheme.typography.displayMedium
            )
        },
        text = {
            Text(
                text = "This action is permanent and cannot be reverted.",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        dismissButton = {
            TextButton(onClick = toggleDeleteDialog) {
                Text(text = "Dismiss")
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDeleteTable()
                    if (lastTable) {
                        prevPage()
                    }
                }
            ) {
                Text(
                    text = "Delete",
                    color = Color.Red
                )
            }
        }
    )
}

@Composable
fun ReorderableCollectionItemScope.HabitTable(
    modifier: Modifier = Modifier,
    habit: Habit,
    onDeleteTable: (Habit) -> Unit,
    navHostController: NavHostController,
    habitList: List<Habit>,
    view: View,
) {
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    val toggleDeleteDialog = { showDeleteDialog = !showDeleteDialog }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = TableThemes.tableThemes[habit.colorTheme].tableColor
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(8.dp)
                    .size(42.dp),
                painter = painterResource(id = IconData.icons[habit.iconId]),
                contentDescription = "Icon",
                tint = Color.Unspecified
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = habit.name,
                    style = if (habit.colorTheme < 12) {
                        MaterialTheme.typography.titleSmall
                    } else {
                        MaterialTheme.typography.titleSmall.copy(shadow = null)
                    },
                    color = TableThemes.tableThemes[habit.colorTheme].fontColor
                )

                if (habit.description.isNotEmpty()) {
                    Text(
                        text = habit.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = TableThemes.tableThemes[habit.colorTheme].fontColor,
                        softWrap = true
                    )
                }
            }

            Row(
                modifier = Modifier.padding(end = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier.draggableHandle(
                        onDragStarted = {
                            ViewCompat.performHapticFeedback(
                                view,
                                HapticFeedbackConstantsCompat.GESTURE_START
                            )
                        },
                        onDragStopped = {
                            ViewCompat.performHapticFeedback(
                                view,
                                HapticFeedbackConstantsCompat.GESTURE_END
                            )
                        }
                    ),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Move icon"
                    )
                }

                IconButton(
                    onClick = {
                        navHostController
                            .navigate("${Screen.AddHabitTable.route}?habitId=${habit.id}") {
                                launchSingleTop = true
                            }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit"
                    )
                }

                IconButton(
                    onClick = toggleDeleteDialog
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }

                if (showDeleteDialog) {
                    DeleteDialog(
                        onDeleteTable = { onDeleteTable(habit) },
                        toggleDeleteDialog = toggleDeleteDialog,
                        lastTable = habitList.size == 1,
                        prevPage = { navHostController.popBackStack() }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewHabitTable() {
    val dummyScope = object : ReorderableCollectionItemScope {
        override fun Modifier.draggableHandle(
            enabled: Boolean,
            interactionSource: MutableInteractionSource?,
            onDragStarted: (startedPosition: Offset) -> Unit,
            onDragStopped: () -> Unit
        ): Modifier {
            return this
        }

        override fun Modifier.longPressDraggableHandle(
            enabled: Boolean,
            interactionSource: MutableInteractionSource?,
            onDragStarted: (startedPosition: Offset) -> Unit,
            onDragStopped: () -> Unit
        ): Modifier {
            return this
        }
    }

    with(dummyScope) {
        HabitTable(
            habit = Habit(
                name = "wwwwwwwwwwwww",
                description = "wwwwwwwwwwwwwwwwwwwwwww",
                colorTheme = 0,
                iconId = 0,
                category = "",
                year = 2024
            ),
            onDeleteTable = {},
            navHostController = rememberNavController(),
            habitList = listOf(),
            view = LocalView.current
        )
    }
}

@Preview
@Composable
fun PreviewDeleteDialog() {
    DeleteDialog(
        onDeleteTable = { },
        toggleDeleteDialog = { },
        lastTable = false,
        prevPage = {}
    )
}