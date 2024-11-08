package com.mohaberabi.kmp.krashlytics.domain.model


data class DeviceInfo(
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
