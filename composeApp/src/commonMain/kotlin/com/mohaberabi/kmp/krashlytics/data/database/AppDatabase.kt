package com.mohaberabi.kmp.krashlytics.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.mohaberabi.kmp.krashlytics.data.database.convertors.AppDatabaseCnvertors
import com.mohaberabi.kmp.krashlytics.data.database.dao.BreadCrumbDao
import com.mohaberabi.kmp.krashlytics.data.database.dao.UnCaughtErrorDao
import com.mohaberabi.kmp.krashlytics.data.database.entity.DeviceBreadCrumbEntity
import com.mohaberabi.kmp.krashlytics.data.database.entity.UncaughtErrorEntity

@Database(
    exportSchema = false,
    entities = [
        DeviceBreadCrumbEntity::class,
        UncaughtErrorEntity::class,

    ],
    version = 2,
)
@TypeConverters(AppDatabaseCnvertors::class)

@ConstructedBy(AppDatabaseCreator::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breadCrumbDao(): BreadCrumbDao
    abstract fun unCaughtErrorDao(): UnCaughtErrorDao

}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

expect object AppDatabaseCreator : RoomDatabaseConstructor<AppDatabase>