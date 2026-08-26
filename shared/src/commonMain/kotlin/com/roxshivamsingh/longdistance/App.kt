package com.roxshivamsingh.longdistance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlin.time.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn

@Composable
@Preview
fun App() {
    MaterialTheme {
        val days = getDaysFromPast("2025-12-24")
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
//                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Card() {
                Box() {
                    Text(text = days.toHumanReadableDuration(), fontSize = 30.sp, fontWeight = FontWeight.SemiBold)

                }
            }

        }
    }
}

//fun getMonthsAndDaysFromPast(date: String): String {
//    val pastDate = LocalDate.parse(date)
//    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
//
//    val totalMonths = (today.year - pastDate.year) * 12 +
//            (today.month.ordinal - pastDate.month.ordinal)
//
//    val candidateDate = pastDate.plus(DatePeriod(months = totalMonths))
//
//    val months = if (candidateDate > today) totalMonths - 1 else totalMonths
//
//    val adjustedDate = pastDate.plus(DatePeriod(months = months))
//    val days = adjustedDate.daysUntil(today)
//
//    return "$months months $days days"
//}
fun getDaysFromPast(date: String): Int {
    val pastDate = LocalDate.parse(date)
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())

    return pastDate.daysUntil(today)
}

/**
 * Converts a number of days into a human-readable duration
 * (years → months → days). Uses 365 days/year and 30 days/month
 * as a practical approximation.
 */
fun Int.toHumanReadableDuration(): String {
    require(this >= 0) { "Days must be non-negative" }

    if (this == 0) return "0 days"

    val years = this / 365
    val remainingAfterYears = this % 365
    val months = remainingAfterYears / 30
    val days = remainingAfterYears % 30

    return buildList {
        if (years > 0) add(pluralize(years, "year"))
        if (months > 0) add(pluralize(months, "month"))
        if (days > 0) add(pluralize(days, "day"))
    }.joinToString(separator = " and ", limit = 2, truncated = "")
}

private fun pluralize(count: Int, unit: String): String =
    when (count) {
        1 -> "1 $unit"
        else -> "$count ${unit}s"
    }
