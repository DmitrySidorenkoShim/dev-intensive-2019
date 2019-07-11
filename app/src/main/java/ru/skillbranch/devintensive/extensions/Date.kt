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
    val diff = this.time - date.time

    return when(diff) {
        in -999999999 * DAY .. -365 * DAY -> "более чем через год"
        in -365 * DAY .. -48 * HOUR -> "${abs(diff/ DAY)} ${humDays(diff)} назад"
        in -48 * HOUR .. -25 * HOUR -> "через 2 ${humDays(diff)}"
        in -25 * HOUR .. -24 * HOUR -> "через день"
        in -24 * HOUR .. -120 * MINUTE -> "через ${abs(diff/ HOUR)} ${humHours(diff)}"
        in -120 * MINUTE .. -89 * MINUTE -> "через 2 ${humHours(diff)}"
        in -89 * MINUTE .. -60 * MINUTE -> "через час"
        in -60 * MINUTE .. -120 * SECOND -> "через ${abs(diff/ MINUTE)} ${humMinutes(diff)}"
        in -120 * SECOND .. -89 * SECOND -> "через 2 ${humMinutes(diff)}"
        in -89 * SECOND .. -60 * SECOND -> "через минуту"
        //in -60 * SECOND .. 0 * SECOND -> "через несколько секунд"

        in -60 * SECOND .. 60 * SECOND -> "несколько секунд назад"
        in 60 * SECOND .. 89 * SECOND -> "минуту назад"
        in 89 * SECOND .. 120 * SECOND -> "2 ${humMinutes(diff)} назад"
        in 120 * SECOND .. 60 * MINUTE -> "${abs(diff/ MINUTE)} ${humMinutes(diff)} назад"
        in 60 * MINUTE .. 89 * MINUTE -> "час назад"
        in 89 * MINUTE .. 120 * MINUTE -> "2 ${humHours(diff)} назад"
        in 120 * MINUTE .. 24 * HOUR -> "${abs(diff/ HOUR)} ${humHours(diff)} назад"
        in 24 * HOUR .. 25 * HOUR -> "день назад"
        in 25 * HOUR .. 48 * HOUR -> "2 ${humDays(diff)} назад"
        in 48 * HOUR .. 365 * DAY -> "${abs(diff/ DAY)} ${humDays(diff)} назад"
        in 366 * DAY .. 999999999 * DAY -> "более года назад"
        else -> "ошибка ${diff/ SECOND} секунд"

    }
}

private fun humMinutes(diff: Long): String {
    return when(abs(diff)) {
        1 * MINUTE -> "минуту"
        in 1 * MINUTE .. 4 * MINUTE -> "минуты"
        in 4 * MINUTE .. 59 * MINUTE -> "минут"
        else -> "ошибка ${diff/ MINUTE} минут"
    }
}

private fun humHours(diff: Long): String {
    return when(abs(diff)) {
        1 * HOUR -> "час"
        in 1 * HOUR .. 4 * HOUR -> "часа"
        in 4 * HOUR .. 22 * HOUR -> "часов"
        else -> "ошибка ${diff/ HOUR} часов"
    }
}

private fun humDays(diff: Long): String {
    return when(abs(diff)) {
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