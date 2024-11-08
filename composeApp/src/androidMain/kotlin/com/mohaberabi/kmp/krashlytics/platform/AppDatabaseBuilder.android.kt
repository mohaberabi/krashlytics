package com.mohaberabi.kmp.krashlytics.platform

import androidx.room.RoomDatabase
import com.mohaberabi.kmp.krashlytics.KrashlyticsApp
import com.mohaberabi.kmp.krashlytics.data.database.AppDatabase

actual fun createDatabaseBuilder(): AppDatabase = KrashlyticsApp.appDatabase