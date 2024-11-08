package com.mohaberabi.kmp.krashlytics.domain.source

import com.mohaberabi.kmp.krashlytics.domain.model.UncaughtErrorModel
import kotlinx.coroutines.flow.Flow

interface AppCrashSource {


    suspend fun addAppCrash(error: UncaughtErrorModel)


    fun getAppCrashReport(): Flow<List<UncaughtErrorModel>>
}