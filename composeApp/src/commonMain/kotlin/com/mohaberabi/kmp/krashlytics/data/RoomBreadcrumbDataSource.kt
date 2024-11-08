package com.mohaberabi.kmp.krashlytics.data

import com.mohaberabi.kmp.krashlytics.data.database.dao.BreadCrumbDao
import com.mohaberabi.kmp.krashlytics.data.database.mapper.toDomain
import com.mohaberabi.kmp.krashlytics.data.database.mapper.toEntity
import com.mohaberabi.kmp.krashlytics.domain.model.Breadcrumb
import com.mohaberabi.kmp.krashlytics.domain.model.DeviceBreadCrumb
import com.mohaberabi.kmp.krashlytics.domain.model.UncaughtErrorModel
import com.mohaberabi.kmp.krashlytics.domain.source.BreadcrumbDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

class RoomBreadcrumbDataSource(
    private val dao: BreadCrumbDao,
) : BreadcrumbDataSource {

    override suspend fun addBreadcrumb(
        breadcrumb: DeviceBreadCrumb
    ) {

        try {
            withContext(Dispatchers.IO) {
                dao.addBreadCrumb(breadcrumb.toEntity())
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
        }

    }

    override fun getBreadcrumbs(): Flow<List<DeviceBreadCrumb>> =
        dao.getAllDeviceBreadCrumbs().map { list -> list.map { it.toDomain() } }
            .flowOn(Dispatchers.IO)

   
}