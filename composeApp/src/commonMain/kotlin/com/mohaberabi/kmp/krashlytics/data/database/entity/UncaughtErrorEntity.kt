package com.mohaberabi.kmp.krashlytics.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohaberabi.kmp.krashlytics.domain.model.DeviceInfo
import com.mohaberabi.kmp.krashlytics.platform.DateTime

@Entity("uncaughtError")
data class UncaughtErrorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val cause: String? = null,
    val errorMessage: String? = null,
    val causedAtMillis: Long = DateTime.currentTimeMillis(),
    @Embedded
    val deviceInfo: DeviceInfoEntity,
)
