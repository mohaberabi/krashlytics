package com.mohaberabi.kmp.krashlytics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.kmp.krashlytics.domain.model.BreadCrumbType
import com.mohaberabi.kmp.krashlytics.domain.model.Breadcrumb
import com.mohaberabi.kmp.krashlytics.platform.krashlytics
import com.mohaberabi.kmp.krashlytics.presentation.DeviceBreadCrumbItem
import com.mohaberabi.kmp.krashlytics.presentation.HomeScreen
import com.mohaberabi.kmp.krashlytics.presentation.TrackedCompose
import com.mohaberabi.kmp.krashlytics.presentation.UncaughtErrorItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val krashlytics = remember { krashlytics() }

    val breadcrumbs by krashlytics.getAppReport().collectAsStateWithLifecycle(listOf())
    val crashes by krashlytics.getAppCrashReport().collectAsStateWithLifecycle(listOf())

    var showHomeScreen by remember {
        mutableStateOf(false)
    }
    MaterialTheme {
        Scaffold { padding ->

            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {

                item {
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                        onClick = {
                            krashlytics.log(
                                Breadcrumb(BreadCrumbType.Log, "some message"),
                            )
                        },
                    ) {
                        Text("Log Test")
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                        onClick = {
                            showHomeScreen = true
                        },
                    ) {
                        Text("Open trackable screen")
                    }
                }
                item {
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                        onClick = {
                            throw RuntimeException("Thrown by me ")
                        },
                    ) {
                        Text("Throw Test")
                    }
                }
                item {
                    Text("Your app breadcrumbs")
                }
                items(
                    breadcrumbs,
                ) { bread ->
                    DeviceBreadCrumbItem(bread)
                }
                item {
                    Text("Your app crash report")
                }
                items(
                    crashes,
                ) { crash ->
                    UncaughtErrorItem(crash)
                }
            }
        }
        if (showHomeScreen) {
            TrackedCompose(
                composedTag = "Home Screen opened ",
                disposedTag = "Home Screen closed"
            ) {
                HomeScreen(
                    onClose = { showHomeScreen = false },
                )
            }
        }
    }
}


