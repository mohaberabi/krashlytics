package com.mohaberabi.kmp.krashlytics.platform

import android.content.Context
import android.util.Log
import com.mohaberabi.kmp.krashlytics.domain.repository.KrashlyticsRepository
import com.mohaberabi.kmp.krashlytics.domain.source.AppSupervisorScope
import com.mohaberabi.kmp.krashlytics.domain.source.BreadcrumbDataSource
import com.mohaberabi.kmp.krashlytics.domain.source.DefaultAppSupervisorScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.UncaughtExceptionHandler

class AndroidUnCaughtExceptionHandler(
    private val krashlytics: KrashlyticsRepository,
) : UncaughtExceptionHandler {
    private val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(thread: Thread, e: Throwable) {
        krashlytics.logFatal(e)
        Log.e("GlobalExceptionHandler", "Uncaught exception: ${e.message}", e)
        defaultHandler?.uncaughtException(thread, e)
//        scope().launch {
//            krashlytics.logFatal(e)
//            breadcrumbDataSource.sendAllBreadCrumbs(e)
//            Log.e("GlobalExceptionHandler", "Uncaught exception: ${e.message}", e)
//            val deviceInfo = deviceInfoProvider.getDeviceInfo()
//            Log.e("DeviceInfo", "Device info: $deviceInfo")
//            defaultHandler?.uncaughtException(thread, e)
//        }
    }
}