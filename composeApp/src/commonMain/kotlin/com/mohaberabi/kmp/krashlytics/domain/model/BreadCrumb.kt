package com.mohaberabi.kmp.krashlytics.domain.model

import com.mohaberabi.kmp.krashlytics.platform.DateTime


data class Breadcrumb(
    val type: BreadCrumbType = BreadCrumbType.Log,
    val message: String,
    val timestamp: Long = DateTime.currentTimeMillis(),
    val data: Map<String, String?>? = null
)