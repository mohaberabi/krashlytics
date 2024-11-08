package com.mohaberabi.kmp.krashlytics.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.kmp.krashlytics.data.database.entity.DeviceBreadCrumbEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface BreadCrumbDao {
    @Upsert
    suspend fun addBreadCrumb(breadcrumb: DeviceBreadCrumbEntity)

    @Query("SELECT * FROM breadcrumbs")
    fun getAllDeviceBreadCrumbs(): Flow<List<DeviceBreadCrumbEntity>>

}
