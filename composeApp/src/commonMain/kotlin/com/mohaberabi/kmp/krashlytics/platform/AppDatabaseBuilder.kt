package com.mohaberabi.kmp.krashlytics.platform

import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohaberabi.kmp.krashlytics.data.database.AppDatabase


expect fun createDatabaseBuilder(): AppDatabase