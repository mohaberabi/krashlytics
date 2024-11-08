package com.mohaberabi.kmp.krashlytics.data.database.convertors

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AppDatabaseCnvertors {

    @TypeConverter
    fun fromMap(value: Map<String, String?>?): String? {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toMap(value: String?): Map<String, String?>? {
        return value?.let { Json.decodeFromString(it) }
    }
}
