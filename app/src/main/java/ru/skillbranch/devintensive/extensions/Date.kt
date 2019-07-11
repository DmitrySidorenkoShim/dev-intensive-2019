package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time

    time +=when(units){
        TimeUnits.SECOND-> value * SECOND
        TimeUnits.MINUTE-> value * MINUTE
        TimeUnits.HOUR-> value * HOUR
        TimeUnits.DAY-> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = abs(this.time - date.time)

    return when(diff) {
        in 0 * SECOND .. 60 * SECOND -> "несколько секунд назад"
        in 60 * SECOND .. 60 * SECOND -> "минуту назад"
        in 60 * SECOND .. 120 * SECOND -> "2 ${humMinutes(diff)} назад"
        in 120 * SECOND .. 60 * MINUTE -> "${abs(diff/ MINUTE)} ${humMinutes(diff)} назад"
        in 60 * MINUTE .. 60 * MINUTE -> "час назад"
        in 61 * MINUTE .. 120 * MINUTE -> "2 ${humHours(diff)} назад"
        in 120 * MINUTE .. 24 * HOUR -> "${abs(diff/ HOUR)} ${humHours(diff)} назад"
        in 24 * HOUR .. 25 * HOUR -> "день назад"
        in 25 * HOUR .. 48 * HOUR -> "2 ${humDays(diff)} назад"
        in 48 * HOUR .. 365 * DAY -> "${abs(diff/ DAY)} ${humDays(diff)} назад"
        in 366 * DAY .. 999999999 * DAY -> "более года назад"
        else -> "ошибка ${diff/ SECOND} секунд"

    }
}

private fun humMinutes(diff: Long): String {
    return when(diff) {
        1 * MINUTE -> "минуту"
        in 1 * MINUTE .. 4 * MINUTE -> "минуты"
        in 4 * MINUTE .. 59 * MINUTE -> "минут"
        else -> "ошибка ${diff/ MINUTE} минут"
    }
}

private fun humHours(diff: Long): String {
    return when(diff) {
        1 * HOUR -> "час"
        in 1 * HOUR .. 4 * HOUR -> "часа"
        in 4 * HOUR .. 22 * HOUR -> "часов"
        else -> "ошибка ${diff/ HOUR} часов"
    }
}

private fun humDays(diff: Long): String {
    return when(diff) {
        1 * DAY -> "день"
        in 1 * DAY .. 4 * DAY -> "дня"
        in 4 * DAY .. 366 * DAY -> "дней"
        else -> "ошибка ${diff/ DAY} дней"
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}