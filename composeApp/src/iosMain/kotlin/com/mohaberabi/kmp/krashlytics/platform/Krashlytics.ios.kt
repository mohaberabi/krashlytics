package com.mohaberabi.kmp.krashlytics.platform

import com.mohaberabi.kmp.krashlytics.data.DefaultKrashlyticsRepository
import com.mohaberabi.kmp.krashlytics.data.RoomAppCrashSource
import com.mohaberabi.kmp.krashlytics.data.RoomBreadcrumbDataSource
import com.mohaberabi.kmp.krashlytics.domain.repository.KrashlyticsRepository
import com.mohaberabi.kmp.krashlytics.domain.source.DefaultAppSupervisorScope

actual fun krashlytics(): KrashlyticsRepository {
    val database = createDatabaseBuilder()

    val breadCrumbDataSource = RoomBreadcrumbDataSource(database.breadCrumbDao())
    val appCrashDataSource = RoomAppCrashSource(database.unCaughtErrorDao())
    val scope = DefaultAppSupervisorScope()
    val deviceInfo = DeviceInfoProvider()
    val krash = DefaultKrashlyticsRepository(
        breadcrumbDataSource = breadCrumbDataSource,
        appCrashSource = appCrashDataSource,
        appSupervisorScope = scope,
        deviceInfoProvider = deviceInfo
    )
    return krash
}