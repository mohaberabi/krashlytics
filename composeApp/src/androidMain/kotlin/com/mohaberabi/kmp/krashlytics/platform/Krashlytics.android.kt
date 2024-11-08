package com.mohaberabi.kmp.krashlytics.platform

import com.mohaberabi.kmp.krashlytics.KrashlyticsApp
import com.mohaberabi.kmp.krashlytics.domain.repository.KrashlyticsRepository

actual fun krashlytics(): KrashlyticsRepository = KrashlyticsApp.krashlyticsRepository