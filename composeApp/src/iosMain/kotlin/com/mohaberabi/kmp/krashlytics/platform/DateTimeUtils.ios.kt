package com.mohaberabi.kmp.krashlytics.platform

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone
import platform.Foundation.currentLocale
import platform.Foundation.dateWithTimeIntervalSince1970
import platform.Foundation.localTimeZone


actual val Long.fromMillis: String
    get() {
        val dateFormatter = NSDateFormatter().apply {
            dateFormat = "dd-MM-yyyy hh:mm a"
            locale = NSLocale.currentLocale
            timeZone = NSTimeZone.localTimeZone
        }
        val date = NSDate.dateWithTimeIntervalSince1970(this / 1000.0)
        return dateFormatter.stringFromDate(date)
    }