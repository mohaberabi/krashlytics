package com.mohaberabi.kmp.krashlytics

import androidx.compose.ui.window.ComposeUIViewController
import com.mohaberabi.kmp.krashlytics.platform.krashlytics

import kotlinx.cinterop.ExperimentalForeignApi


import platform.Foundation.*
import platform.BackgroundTasks.BGProcessingTaskRequest
import platform.BackgroundTasks.BGTask
import platform.BackgroundTasks.BGTaskScheduler
import platform.darwin.dispatch_queue_global_t
import platform.darwin.dispatch_queue_main_t
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)

fun MainViewController() = ComposeUIViewController(
) {

    setUnhandledExceptionHook { exception ->
        krashlytics().logFatal(exception)
        println("WE MADE IT LOOSER ${exception.message} ,${exception.cause}")
        terminateWithUnhandledException(exception)
    }
    App()
}


