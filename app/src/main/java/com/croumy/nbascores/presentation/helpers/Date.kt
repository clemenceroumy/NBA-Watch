package com.croumy.nbascores.presentation.helpers

import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Date
import java.util.Locale

const val DATE = "dd/MM/yyyy"
const val TIME = "HH:mm"
const val ISO = "yyyy-MM-dd'T'HH:mm:ss'Z'"

fun Date.asString(format: String = DATE): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(this)
}

fun String.toDate(format: String = ISO): Date {
    val firstZdt = ZonedDateTime.parse(this)
    val europeZdt = firstZdt.withZoneSameInstant(ZoneId.of("Europe/Paris"))

    return europeZdt.toInstant().let { Date.from(it) }
}