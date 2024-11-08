package com.mohaberabi.kmp.krashlytics.data.database.mapper

import com.mohaberabi.kmp.krashlytics.data.database.entity.BreadcrumbEntity
import com.mohaberabi.kmp.krashlytics.data.database.entity.DeviceBreadCrumbEntity
import com.mohaberabi.kmp.krashlytics.data.database.entity.DeviceInfoEntity
import com.mohaberabi.kmp.krashlytics.domain.model.BreadCrumbType
import com.mohaberabi.kmp.krashlytics.domain.model.Breadcrumb
import com.mohaberabi.kmp.krashlytics.domain.model.DeviceBreadCrumb
import com.mohaberabi.kmp.krashlytics.domain.model.DeviceInfo

fun DeviceInfoEntity.toDomain() = DeviceInfo(
    deviceModel = deviceModel,
    manufacturer = manufacturer,
    osVersion = osVersion,
    sdkVersion = sdkVersion,
    screenResolution = screenResolution,
    locale = locale,
    batteryLevel = batteryLevel,
    isCharging = isCharging,
    isConnectedToWifi = isConnectedToWifi,
    storageAvailable = storageAvailable,
    totalStorage = totalStorage,
    isRooted = isRooted
)

fun DeviceInfo.toEntity() = DeviceInfoEntity(
    deviceModel = deviceModel,
    manufacturer = manufacturer,
    osVersion = osVersion,
    sdkVersion = sdkVersion,
    screenResolution = screenResolution,
    locale = locale,
    batteryLevel = batteryLevel,
    isCharging = isCharging,
    isConnectedToWifi = isConnectedToWifi,
    storageAvailable = storageAvailable,
    totalStorage = totalStorage,
    isRooted = isRooted
)

fun DeviceBreadCrumb.toEntity(): DeviceBreadCrumbEntity {
    return DeviceBreadCrumbEntity(
        breadcrumb = BreadcrumbEntity(
            type = this.breadcrumb.type.name,
            message = this.breadcrumb.message,
            timestamp = this.breadcrumb.timestamp,
            data = this.breadcrumb.data
        ),
        deviceInfo = deviceInfo.toEntity()
    )
}

fun DeviceBreadCrumbEntity.toDomain(): DeviceBreadCrumb {
    return DeviceBreadCrumb(
        breadcrumb = Breadcrumb(
            type = BreadCrumbType.valueOf(this.breadcrumb.type),
            message = this.breadcrumb.message,
            timestamp = this.breadcrumb.timestamp,
            data = this.breadcrumb.data
        ),
        deviceInfo = deviceInfo.toDomain()
    )
}
