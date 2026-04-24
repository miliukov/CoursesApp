package dev.dmil.coursesapp.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dmil.coursesapp.core.domain.model.Course
import dev.dmil.coursesapp.core.ui.CourseCard
import dev.dmil.coursesapp.core.ui.theme.DarkGrey
import dev.dmil.coursesapp.core.ui.theme.Green
import dev.dmil.coursesapp.core.ui.theme.White
import dev.dmil.coursesapp.feature.home.R

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeContent(
        uiState = uiState,
        onSortClick = viewModel::toggleSort,
        toggleFavourite = viewModel::toggleFavourite
    )

}

@Composable
private fun HomeContent(
    uiState: HomeUiState,
    onSortClick: () -> Unit,
    toggleFavourite: (Course) -> Unit
) {
    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Green)
        }
    } else {
        LazyColumn(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {  },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(50.dp),
                        enabled = false,
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledBorderColor = Color.Transparent,
                            disabledContainerColor = DarkGrey
                        ),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.search),
                                contentDescription = "Search Icon",
                                modifier = Modifier.padding(start = 5.dp),
                                tint = White
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Search courses...",
                                fontWeight = FontWeight(400),
                                color = White.copy(alpha = 0.5f)
                            )
                        }
                    )
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(56.dp)
                            .background(MaterialTheme.colorScheme.surface, CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.filter),
                            contentDescription = "Filter Icon"
                        )
                    }
                }

            }
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onSortClick() }
                            .align(Alignment.CenterEnd),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "По дате добавления",
                            fontSize = 14.sp,
                            color = Green,
                            fontWeight = FontWeight(500)
                        )
                        Icon(
                            painter = painterResource(R.drawable.sort_arrows),
                            contentDescription = "Sort Icon",
                            tint = Green
                        )
                    }
                }
            }
            items(uiState.courses) { course ->
                CourseCard(
                    course,
                    onFavouriteClick = { toggleFavourite(course) }
                )
            }
        }
    }
}