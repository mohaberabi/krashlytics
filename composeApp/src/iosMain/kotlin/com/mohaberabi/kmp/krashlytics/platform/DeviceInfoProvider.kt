package com.mohaberabi.kmp.krashlytics.platform

import com.mohaberabi.kmp.krashlytics.domain.model.DeviceInfo
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.UIKit.UIDevice
import platform.UIKit.UIDeviceBatteryState
import platform.UIKit.UIScreen

actual class DeviceInfoProvider {
    actual fun getDeviceInfo(): DeviceInfo {
        return DeviceInfo(
            deviceModel = UIDevice.currentDevice.model,
            manufacturer = "Apple",
            osVersion = UIDevice.currentDevice.systemVersion,
            sdkVersion = "16",
            screenResolution = getScreenResolution(),
            locale = NSLocale.currentLocale.languageCode,
            batteryLevel = getBatteryLevel(),
            isCharging = isDeviceCharging(),
            isConnectedToWifi = true,
            storageAvailable = 0,
            totalStorage = 0,
            isRooted = false
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun getScreenResolution(): String {
        val screen = UIScreen.mainScreen
        return "${screen.bounds.size}x${screen.bounds.size}"
    }

    private fun getBatteryLevel(): Int {
        return (UIDevice.currentDevice.batteryLevel * 100).toInt()
    }

    private fun isDeviceCharging(): Boolean {
        return UIDevice.currentDevice.batteryState == UIDeviceBatteryState.UIDeviceBatteryStateCharging
    }


}