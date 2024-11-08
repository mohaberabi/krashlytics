package com.mohaberabi.kmp.krashlytics.domain.model

import com.mohaberabi.kmp.krashlytics.platform.DateTime


data class UncaughtErrorModel(
    val id: String? = null,
    val cause: String? = null,
    val errorMessage: String? = null,
    val causedAtMillis: Long = DateTime.currentTimeMillis(),
    val deviceInfo: DeviceInfo,
)