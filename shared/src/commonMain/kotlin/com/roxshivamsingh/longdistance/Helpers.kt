package com.roxshivamsingh.longdistance

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn
import kotlin.time.Clock

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


val getHumanReadable=getDaysFromPast("2025-12-24").toHumanReadableDuration()
