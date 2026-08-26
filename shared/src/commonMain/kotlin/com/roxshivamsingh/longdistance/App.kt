package com.roxshivamsingh.longdistance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun App() {
    MaterialTheme {
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
                    Text(
                        text = getHumanReadable,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold
                    )

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
