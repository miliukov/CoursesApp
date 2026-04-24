package dev.dmil.coursesapp.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("ru"))
    val date = inputFormat.parse(dateString)
    return outputFormat.format(date ?: return dateString)
}