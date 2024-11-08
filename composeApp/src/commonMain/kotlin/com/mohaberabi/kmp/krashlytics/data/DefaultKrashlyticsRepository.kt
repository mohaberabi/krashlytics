package com.mohaberabi.kmp.krashlytics.data

import com.mohaberabi.kmp.krashlytics.domain.model.Breadcrumb
import com.mohaberabi.kmp.krashlytics.domain.model.DeviceBreadCrumb
import com.mohaberabi.kmp.krashlytics.domain.model.UncaughtErrorModel
import com.mohaberabi.kmp.krashlytics.domain.repository.KrashlyticsRepository
import com.mohaberabi.kmp.krashlytics.domain.source.AppCrashSource
import com.mohaberabi.kmp.krashlytics.domain.source.AppSupervisorScope
import com.mohaberabi.kmp.krashlytics.domain.source.BreadcrumbDataSource
import com.mohaberabi.kmp.krashlytics.platform.DeviceInfoProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class DefaultKrashlyticsRepository(
    private val breadcrumbDataSource: BreadcrumbDataSource,
    private val appSupervisorScope: AppSupervisorScope,
    private val deviceInfoProvider: DeviceInfoProvider,
    private val appCrashSource: AppCrashSource
) : KrashlyticsRepository {
    override fun log(breadcrumb: Breadcrumb) {

        appSupervisorScope().launch {
            val info = deviceInfoProvider.getDeviceInfo()
            val deviceBreadcrumb = DeviceBreadCrumb(deviceInfo = info, breadcrumb = breadcrumb)
            breadcrumbDataSource.addBreadcrumb(deviceBreadcrumb)
        }
    }

    override fun logFatal(error: Throwable) {
        appSupervisorScope().launch {
            val info = deviceInfoProvider.getDeviceInfo()
            appCrashSource.addAppCrash(
                UncaughtErrorModel(
                    errorMessage = error.message ?: "Unknown Error",
                    cause = error.cause.toString(),
                    deviceInfo = info
                )
            )
        }
    }

    override fun getAppReport(): Flow<List<DeviceBreadCrumb>> =
        breadcrumbDataSource.getBreadcrumbs()

    override fun getAppCrashReport(): Flow<List<UncaughtErrorModel>> =
        appCrashSource.getAppCrashReport()
}