package com.mohaberabi.kmp.krashlytics.platform

import com.mohaberabi.kmp.krashlytics.domain.model.DeviceInfo

expect class DeviceInfoProvider {
    fun getDeviceInfo(
    ): DeviceInfo
}