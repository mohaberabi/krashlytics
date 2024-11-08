package com.mohaberabi.kmp.krashlytics

import androidx.compose.ui.window.ComposeUIViewController
import com.mohaberabi.kmp.krashlytics.domain.repository.KrashlyticsRepository
import com.mohaberabi.kmp.krashlytics.platform.krashlytics
import kotlinx.cinterop.CPointer

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.staticCFunction


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
    val krash = krashlytics()
    setUnhandledExceptionHook { exception ->
        krash.logFatal(exception)
        terminateWithUnhandledException(exception)
    }
    handleNSUncaughtException(krash)
    App()
}


@OptIn(ExperimentalForeignApi::class)
private fun handleNSUncaughtException(
    krashlytics: KrashlyticsRepository,
) {
    val handler: CPointer<NSUncaughtExceptionHandler> = staticCFunction { nsException ->
        val cause = Throwable(nsException?.reason)
        val throwable = Throwable(message = nsException?.name, cause)
        krashlytics.logFatal(throwable)
    }
    NSSetUncaughtExceptionHandler(handler)
}