package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
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
    return "только что"
//    return when(diff){
//        {diff < TimeUnits.SECOND} -> "только что"
//        diff < 45 * SECOND && diff >= SECOND -> "несколько секунд"
//        diff < 75 * SECOND && diff >= 45 * SECOND -> "минуту"
//        else "больше года назад"
//    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}