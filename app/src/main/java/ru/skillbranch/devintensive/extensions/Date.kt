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
        in 0 * SECOND .. 59 * SECOND -> "несколько секунд назад"
        in 59 * SECOND .. 61 * SECOND -> "минуту назад"
        in 61 * SECOND .. 59 * MINUTE -> "${abs(diff/ MINUTE)} ${humMinutes(diff)} назад"
        in 59 * MINUTE .. 61 * MINUTE -> "час назад"
        in 61 * MINUTE .. 120 * MINUTE -> "2 ${humHours(diff)} назад"
        in 120 * MINUTE .. 23 * HOUR -> "${abs(diff/ HOUR)} ${humHours(diff)} назад"
        in 23 * HOUR .. 25 * HOUR -> "день назад"
        in 25 * HOUR .. 365 * DAY -> "${abs(diff/ DAY)} дней назад"
        in 366 * DAY .. 999999999 * DAY -> "более года назад"
        else -> "ошибка ${diff/ SECOND} секунд"

    }
}

private fun humMinutes(diff: Long): String {
    return when(diff) {
        in 1 * MINUTE .. 2 * MINUTE -> "минуту"
        in 2 * MINUTE .. 5 * MINUTE -> "минуты"
        in 5 * MINUTE .. 59 * MINUTE -> "минут"
        else -> "ошибка ${diff/ MINUTE} минут"
    }
}

private fun humHours(diff: Long): String {
    return when(diff) {
        in 1 * HOUR .. 2 * HOUR -> "час"
        in 2 * HOUR .. 5 * HOUR -> "часа"
        in 5 * HOUR .. 22 * HOUR -> "часов"
        else -> "ошибка ${diff/ HOUR} часов"
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}