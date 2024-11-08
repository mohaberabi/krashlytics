package com.mohaberabi.kmp.krashlytics.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mohaberabi.kmp.krashlytics.domain.model.DeviceInfo


@Composable
fun DeviceInfoCompose(deviceInfo: DeviceInfo) {
    Column {
        Text("Model: ${deviceInfo.deviceModel}", style = MaterialTheme.typography.bodyMedium)
        Text(
            "Manufacturer: ${deviceInfo.manufacturer}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text("OS Version: ${deviceInfo.osVersion}", style = MaterialTheme.typography.bodyMedium)
        Text(
            "Battery Level: ${deviceInfo.batteryLevel}%",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            "Charging: ${if (deviceInfo.isCharging) "Yes" else "No"}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            "WiFi Connected: ${if (deviceInfo.isConnectedToWifi) "Yes" else "No"}",
            style = MaterialTheme.typography.bodyMedium
        )

    }
}
