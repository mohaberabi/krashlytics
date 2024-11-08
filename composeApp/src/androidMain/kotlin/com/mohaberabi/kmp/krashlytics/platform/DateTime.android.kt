package com.mohaberabi.kmp.krashlytics.platform

actual object DateTime {
    actual fun currentTimeMillis(): Long = System.currentTimeMillis()
}