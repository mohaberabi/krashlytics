package com.mohaberabi.kmp.krashlytics.platform

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

actual object DateTime {
    actual fun currentTimeMillis(): Long = (NSDate().timeIntervalSince1970 * 1000).toLong()


}