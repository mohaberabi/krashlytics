package com.mohaberabi.kmp.krashlytics.domain.source

import com.mohaberabi.kmp.krashlytics.domain.model.Breadcrumb
import com.mohaberabi.kmp.krashlytics.domain.model.DeviceBreadCrumb
import com.mohaberabi.kmp.krashlytics.domain.model.UncaughtErrorModel
import kotlinx.coroutines.flow.Flow

interface BreadcrumbDataSource {


    suspend fun addBreadcrumb(
        breadcrumb: DeviceBreadCrumb
    )

    fun getBreadcrumbs(): Flow<List<DeviceBreadCrumb>>
}