package com.mohaberabi.kmp.krashlytics.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.mohaberabi.kmp.krashlytics.domain.model.BreadCrumbType
import com.mohaberabi.kmp.krashlytics.domain.model.Breadcrumb
import com.mohaberabi.kmp.krashlytics.platform.krashlytics
import kotlinx.coroutines.internal.ThreadSafeHeapNode
import kotlinx.coroutines.launch


@Composable
fun TrackedCompose(
    composedTag: String,
    disposedTag: String,
    composedExtras: Map<String, String?>? = null,
    disposedExtras: Map<String, String?>? = null,
    content: @Composable () -> Unit
) {

    val krash = remember {
        krashlytics()
    }
    val composedBreadCrumb = remember {
        Breadcrumb(
            BreadCrumbType.Log,
            composedTag,
            0,
            composedExtras
        )
    }
    val disposedBreadCrumb = remember {
        Breadcrumb(
            BreadCrumbType.Log,
            disposedTag,
            0,
            disposedExtras
        )
    }
    LaunchedEffect(Unit) {
        krash.log(
            composedBreadCrumb
        )

    }

    DisposableEffect(Unit) {
        onDispose {
            krash.log(
                disposedBreadCrumb
            )
        }
    }
    content()

}


