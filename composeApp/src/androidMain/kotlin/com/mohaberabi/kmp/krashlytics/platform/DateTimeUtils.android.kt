package com.mohaberabi.kmp.krashlytics.platform

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

actual val Long.fromMillis: String
    get() {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault())
        return dateFormat.format(Date(this))
    }