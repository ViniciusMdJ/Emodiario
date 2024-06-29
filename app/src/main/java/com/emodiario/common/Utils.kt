package com.emodiario.common

import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}

fun Long.toHistoryRatingDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val instant = Instant.ofEpochMilli(this)
    val zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    val day = if(zonedDateTime.dayOfWeek == DayOfWeek.SUNDAY) "Domingo"
    else if(zonedDateTime.dayOfWeek == DayOfWeek.MONDAY) "Segunda-feira"
    else if(zonedDateTime.dayOfWeek == DayOfWeek.TUESDAY) "Terça-feira"
    else if(zonedDateTime.dayOfWeek == DayOfWeek.WEDNESDAY) "Quarta-feira"
    else if(zonedDateTime.dayOfWeek == DayOfWeek.THURSDAY) "Quinta-feira"
    else if(zonedDateTime.dayOfWeek == DayOfWeek.FRIDAY) "Sexta-feira"
    else "Sábado"

    return "$day, ${formatter.format(date)}"
}