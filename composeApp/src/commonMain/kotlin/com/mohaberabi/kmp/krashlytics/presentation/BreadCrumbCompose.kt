package com.mohaberabi.kmp.krashlytics.presentation


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohaberabi.kmp.krashlytics.domain.model.DeviceBreadCrumb
import com.mohaberabi.kmp.krashlytics.platform.fromMillis

@Composable
fun DeviceBreadCrumbItem(deviceBreadCrumb: DeviceBreadCrumb) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { expanded = !expanded }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = deviceBreadCrumb.breadcrumb.type.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Text(
                        text = "Message: ${deviceBreadCrumb.breadcrumb.message}",
                    )
                    Text(
                        text = "Timestamp: ${deviceBreadCrumb.breadcrumb.timestamp.fromMillis}",
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Device Info",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    DeviceInfoCompose(deviceBreadCrumb.deviceInfo)
                }
            }
        }
    }
}
