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
    val diff = (this.time - date.time)
    return when(diff) {
        in 0 .. 1 * SECOND -> "только что"
        in 1 * SECOND .. 59 * SECOND -> "несколько секунд назад"
        in 59 * SECOND .. 90 * SECOND -> "минуту назад"
        in 90 * SECOND .. 59 * MINUTE -> "${abs(diff/ MINUTE)} ${humMinutes(diff)} назад"
        in 59 * MINUTE .. 90 * MINUTE -> "час назад"
        in 90 * MINUTE .. 23 * HOUR -> "${abs(diff/ HOUR)} ${humHours(diff)} назад"
        in 23 * HOUR .. 25 * HOUR -> "день назад"
        in 25 * HOUR .. 365 * DAY -> "${abs(diff/ DAY)} дней назад"
        in 366 * DAY .. 99999 * DAY -> "более года назад"
        else -> "несколько секунд назад"

    }
}

private fun humMinutes(diff: Long): String {
    return when(diff) {
        1 * SECOND -> "минуту"
        in 2 .. 4 * SECOND -> "минуты"
        in 5 .. 59 * SECOND -> "минут"
        else -> "минут"
    }
}

private fun humHours(diff: Long): String {
    return when(diff) {
        1 * HOUR -> "час"
        in 2 .. 4 * HOUR -> "часа"
        in 5 .. 22 * HOUR -> "часов"
        else -> "часов"
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}