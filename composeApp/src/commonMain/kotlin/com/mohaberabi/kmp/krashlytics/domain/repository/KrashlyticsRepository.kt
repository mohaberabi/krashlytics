package com.mohaberabi.kmp.krashlytics.domain.repository

import com.mohaberabi.kmp.krashlytics.domain.model.Breadcrumb
import com.mohaberabi.kmp.krashlytics.domain.model.DeviceBreadCrumb
import com.mohaberabi.kmp.krashlytics.domain.model.UncaughtErrorModel
import kotlinx.coroutines.flow.Flow

interface KrashlyticsRepository {
    fun log(breadcrumb: Breadcrumb)
    fun logFatal(error: Throwable)
    fun getAppReport(): Flow<List<DeviceBreadCrumb>>
    fun getAppCrashReport(): Flow<List<UncaughtErrorModel>>
}