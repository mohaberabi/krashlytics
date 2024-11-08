package com.mohaberabi.kmp.krashlytics.domain.model

data class DeviceBreadCrumb(
    val id: String? = null,
    val breadcrumb: Breadcrumb,
    val deviceInfo: DeviceInfo,
)
