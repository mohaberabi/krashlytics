package com.mohaberabi.kmp.krashlytics.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohaberabi.kmp.krashlytics.domain.model.UncaughtErrorModel
import com.mohaberabi.kmp.krashlytics.platform.fromMillis

@Composable
fun UncaughtErrorItem(uncaughtErrorModel: UncaughtErrorModel) {
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
                text = "Error at ${uncaughtErrorModel.causedAtMillis.fromMillis}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    uncaughtErrorModel.cause?.let {
                        Text(
                            text = "Cause: $it",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    uncaughtErrorModel.errorMessage?.let {
                        Text(
                            text = "Error Message: $it",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    DeviceInfoCompose(uncaughtErrorModel.deviceInfo)
                }
            }
        }
    }
}
