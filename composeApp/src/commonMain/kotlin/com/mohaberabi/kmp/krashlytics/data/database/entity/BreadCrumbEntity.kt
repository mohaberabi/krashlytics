package com.mohaberabi.kmp.krashlytics.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


import androidx.room.Embedded


@Entity(tableName = "breadcrumbs")
data class DeviceBreadCrumbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @Embedded val breadcrumb: BreadcrumbEntity,
    @Embedded val deviceInfo: DeviceInfoEntity,
)

data class BreadcrumbEntity(
    val type: String,
    val message: String,
    val timestamp: Long,
    val data: Map<String, String?>?
)

data class DeviceInfoEntity(
    val deviceModel: String,
    val manufacturer: String,
    val osVersion: String,
    val sdkVersion: String,
    val screenResolution: String,
    val locale: String,
    val batteryLevel: Int,
    val isCharging: Boolean,
    val isConnectedToWifi: Boolean,
    val storageAvailable: Long,
    val totalStorage: Long,
    val isRooted: Boolean
)
