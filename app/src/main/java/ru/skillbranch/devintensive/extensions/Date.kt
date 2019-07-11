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
        in -999999999 * DAY .. -361 * DAY -> "более года назад"
        in -361 * DAY .. -48 * HOUR -> "${abs(diff/ DAY)} ${humDays(diff)} назад"
        in -48 * HOUR .. -27 * HOUR -> "1 день назад"
        in -27 * HOUR .. -23 * HOUR -> "день назад"
        in -23 * HOUR .. -120 * MINUTE -> "${abs(diff/ HOUR)} ${humHours(diff)} назад"
        in -120 * MINUTE .. -76 * MINUTE -> "1 час назад"
        in -76 * MINUTE .. -46 * MINUTE -> "час назад"
        in -46 * MINUTE .. -120 * SECOND -> "${abs(diff/ MINUTE)} ${humMinutes(diff)} назад"
        in -120 * SECOND .. -76 * SECOND -> "1 минуту назад"
        in -76 * SECOND .. -46 * SECOND -> "минуту назад"
        in -46 * SECOND .. -2 * SECOND -> "несколько секунд назад"
        in -2 * SECOND .. 1 * SECOND -> "только что"
        in 1 * SECOND .. 45 * SECOND -> "через несколько секунд"
        in 45 * SECOND .. 75 * SECOND -> "через минуту"
        in 75 * SECOND .. 119 * SECOND -> "через 1 минуту"
        in 119 * SECOND .. 45 * MINUTE -> "через ${abs(diff/ MINUTE)} ${humMinutes(diff)}"
        in 45 * MINUTE .. 75 * MINUTE -> "через час"
        in 75 * MINUTE .. 120 * MINUTE -> "через 1 час"
        in 120 * MINUTE .. 22 * HOUR -> "через ${abs(diff/ HOUR)} ${humHours(diff)}"
        in 22 * HOUR .. 26 * HOUR -> "через день"
        in 26 * HOUR .. 48 * HOUR -> "через 1 день"
        in 48 * HOUR .. 360 * DAY -> "через ${abs(diff/ DAY)} ${humDays(diff)}"
        in 360 * DAY .. 999999999 * DAY -> "более чем через год"
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
        21 * HOUR -> "час"
        in 1 * HOUR .. 4 * HOUR -> "часа"
        in 4 * HOUR .. 20 * HOUR -> "часов"
        in 22 * HOUR .. 24 * HOUR -> "часа"
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