package com.mohaberabi.kmp.krashlytics.platform

import android.content.Context
import android.net.ConnectivityManager
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import androidx.core.content.getSystemService
import com.mohaberabi.kmp.krashlytics.domain.model.DeviceInfo
import java.io.File
import java.util.Locale

actual class DeviceInfoProvider(
    private val context: Context,
) {
    actual fun getDeviceInfo(): DeviceInfo {
        val (batteryLevel, isCharging) = getBatteryInfo()
        val (storageAvailable, totalStorage) = getStorageInfo()
        return DeviceInfo(
            deviceModel = Build.MODEL,
            manufacturer = Build.MANUFACTURER,
            osVersion = Build.VERSION.RELEASE,
            sdkVersion = "${Build.VERSION.SDK_INT}",
            screenResolution = getScreenResolution(),
            locale = Locale.getDefault().toString(),
            batteryLevel = batteryLevel,
            isCharging = isCharging,
            isConnectedToWifi = isConnectedToWifi(),
            storageAvailable = storageAvailable,
            totalStorage = totalStorage,
            isRooted = isDeviceRooted()
        )
    }


    private fun getScreenResolution(): String {
        val displayMetrics = context.resources.displayMetrics
        return "${displayMetrics.widthPixels}x${displayMetrics.heightPixels}"
    }

    private fun getBatteryInfo(): Pair<Int, Boolean> {
        val batteryManager =
            context.getSystemService<BatteryManager>()
        return batteryManager?.let { manager ->
            val batteryLevel = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
            val isCharging = manager.isCharging
            Pair(batteryLevel, isCharging)
        } ?: Pair(0, false)

    }

    private fun isConnectedToWifi(): Boolean {
        val connectivityManager =
            context.getSystemService<ConnectivityManager>()
        return connectivityManager?.let { manager ->
            val network = manager.activeNetwork
            val capabilities = manager.getNetworkCapabilities(network)
            return capabilities?.hasTransport(android.net.NetworkCapabilities.TRANSPORT_WIFI)
                ?: false
        } ?: false

    }

    private fun getStorageInfo(): Pair<Long, Long> {
        val stat = android.os.StatFs(Environment.getDataDirectory().path)
        val availableBytes = stat.availableBytes
        val totalBytes = stat.totalBytes
        return Pair(availableBytes, totalBytes)
    }

    private fun isDeviceRooted(): Boolean {
        val paths = arrayOf(
            "/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su"
        )
        for (path in paths) {
            if (File(path).exists()) return true
        }
        return false
    }
}