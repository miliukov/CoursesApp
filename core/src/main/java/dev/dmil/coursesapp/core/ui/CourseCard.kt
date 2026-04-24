package dev.dmil.coursesapp.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.dmil.coursesapp.core.R
import dev.dmil.coursesapp.core.domain.model.Course
import dev.dmil.coursesapp.core.ui.theme.DarkGrey
import dev.dmil.coursesapp.core.ui.theme.Green
import dev.dmil.coursesapp.core.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CourseCard(
    course: Course,
    onFavouriteClick: () -> Unit
) {

    val images = listOf(R.drawable.course_image_3, R.drawable.course_image_1, R.drawable.course_image_2)
    val imageRes = images[course.id % images.size]

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Box {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    painter = painterResource(imageRes),
                    contentDescription = "Course Image"
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = DarkGrey.copy(alpha = 0.6f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.star),
                                contentDescription = "Star Icon",
                                tint = Green
                            )
                            Text(
                                text = course.rate,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400)
                            )
                        }
                    }
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = DarkGrey.copy(alpha = 0.6f)
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            text = formatDate(course.startDate),
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400)
                        )
                    }
                }
                Surface(
                    shape = CircleShape,
                    color = DarkGrey.copy(alpha = 0.6f),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onFavouriteClick() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.favourites),
                        contentDescription = "Bookmark",
                        tint = if (course.hasLike) Green else White,
                        modifier = Modifier.padding(6.dp).size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = course.title,
                fontSize = 20.sp,
                fontWeight = FontWeight(500)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = course.text,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = White.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${course.price} ₽",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500)
                )
                Text(
                    text = "Подробнее →",
                    fontSize = 14.sp,
                    color = Green,
                    fontWeight = FontWeight(600),
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { }
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

fun formatDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("ru"))
    val date = inputFormat.parse(dateString)
    return outputFormat.format(date ?: return dateString)
}