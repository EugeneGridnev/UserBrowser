package ru.eugeneprojects.userbrowser.data.room.db

import androidx.room.TypeConverter
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class Converters {

    @TypeConverter
    fun fromData(data: OffsetDateTime): String = data.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)

    @TypeConverter
    fun toData(data: String): OffsetDateTime = OffsetDateTime.parse(data, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
}