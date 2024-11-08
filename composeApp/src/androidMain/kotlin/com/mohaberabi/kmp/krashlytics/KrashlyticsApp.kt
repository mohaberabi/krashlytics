package com.mohaberabi.kmp.krashlytics

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohaberabi.kmp.krashlytics.data.DefaultKrashlyticsRepository
import com.mohaberabi.kmp.krashlytics.data.RoomAppCrashSource
import com.mohaberabi.kmp.krashlytics.data.RoomBreadcrumbDataSource
import com.mohaberabi.kmp.krashlytics.data.database.AppDatabase
import com.mohaberabi.kmp.krashlytics.domain.repository.KrashlyticsRepository
import com.mohaberabi.kmp.krashlytics.domain.source.AppCrashSource
import com.mohaberabi.kmp.krashlytics.domain.source.BreadcrumbDataSource
import com.mohaberabi.kmp.krashlytics.domain.source.DefaultAppSupervisorScope
import com.mohaberabi.kmp.krashlytics.platform.AndroidUnCaughtExceptionHandler
import com.mohaberabi.kmp.krashlytics.platform.DeviceInfoProvider

class KrashlyticsApp : Application() {


    companion object {
        lateinit var krashlyticsRepository: KrashlyticsRepository
        lateinit var appDatabase: AppDatabase

    }

    private lateinit var breadcrumbDataSource: BreadcrumbDataSource
    private lateinit var appCrashSource: AppCrashSource


    override fun onCreate() {

        super.onCreate()
        appDatabase = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "krash.db"
        ).build()
        val scope = DefaultAppSupervisorScope()
        val deviceInfoProvider = DeviceInfoProvider(this)
        breadcrumbDataSource = RoomBreadcrumbDataSource(appDatabase.breadCrumbDao())
        appCrashSource = RoomAppCrashSource(appDatabase.unCaughtErrorDao())
        krashlyticsRepository = DefaultKrashlyticsRepository(
            breadcrumbDataSource = breadcrumbDataSource,
            appSupervisorScope = scope,
            deviceInfoProvider = deviceInfoProvider,
            appCrashSource = appCrashSource
        )
        Thread.setDefaultUncaughtExceptionHandler(
            AndroidUnCaughtExceptionHandler(
                krashlytics = krashlyticsRepository
            )
        )
    }
}