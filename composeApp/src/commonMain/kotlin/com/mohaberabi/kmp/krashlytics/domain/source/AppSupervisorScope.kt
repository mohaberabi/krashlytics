package com.mohaberabi.kmp.krashlytics.domain.source

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob


interface AppSupervisorScope {


    operator fun invoke(): CoroutineScope
}


class DefaultAppSupervisorScope() : AppSupervisorScope {
    override fun invoke(): CoroutineScope = CoroutineScope(
        Dispatchers.IO + SupervisorJob()
    )
}